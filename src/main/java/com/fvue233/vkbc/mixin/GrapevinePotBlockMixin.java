package com.fvue233.vkbc.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.satisfy.vinery.core.block.GrapevinePotBlock;
import net.satisfy.vinery.core.block.state.properties.GrapeProperty;
import net.satisfy.vinery.core.item.GrapeItem;
import net.satisfy.vinery.core.registry.GrapeTypeRegistry;
import net.satisfy.vinery.core.registry.ObjectRegistry;
import net.satisfy.vinery.core.util.GrapeType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GrapevinePotBlock.class, remap = false)
public abstract class GrapevinePotBlockMixin {

    @Shadow @Final private static IntegerProperty STAGE;
    @Shadow @Final private static IntegerProperty STORAGE;
    @Shadow @Final private static GrapeProperty GRAPEVINE_TYPE;

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/properties/IntegerProperty;create(Ljava/lang/String;II)Lnet/minecraft/world/level/block/state/properties/IntegerProperty;",
                    ordinal = 1
            ),
            index = 2
    )
    private static int vkbc$expandStorageMax(int originalMax) {
        return 8;
    }

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void vkbc$useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<ItemInteractionResult> cir) {
        int stage = state.getValue(STAGE);
        int storage = state.getValue(STORAGE);

        if (stage > 3 || storage >= 8) {
            if (stack.getItem() instanceof GrapeItem) {
                cir.setReturnValue(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
                return;
            }
        }

        if (stack.getItem() instanceof GrapeItem grape) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }

            boolean playSound = false;
            BlockState newState;
            if (stage == 0) {
                newState = world.getBlockState(pos)
                        .setValue(STAGE, 1)
                        .setValue(STORAGE, 1)
                        .setValue(GRAPEVINE_TYPE, grape.getType());
                world.setBlock(pos, newState, Block.UPDATE_ALL);
                playSound = true;
            } else {
                int nextStorage = Math.min(storage + 1, 8);
                newState = world.getBlockState(pos).setValue(STORAGE, nextStorage);
                world.setBlock(pos, newState, Block.UPDATE_ALL);
                playSound = true;
            }

            newState = world.getBlockState(pos);
            int newStorage = newState.getValue(STORAGE);
            int newStage = newState.getValue(STAGE);
            if ((newStorage == 4 || newStorage == 8) && newStage < 3) {
                world.setBlock(pos, newState.setValue(STAGE, newStage + 1), Block.UPDATE_ALL);
            }

            if (playSound) {
                world.playSound(player, pos, SoundEvents.CORAL_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
            return;
        }

        if (stack.is(ObjectRegistry.WINE_BOTTLE.get().asItem())) {
            if (stage == 6 && storage >= 2 && storage % 2 == 0) {
                ItemStack output = ((GrapeType) state.getValue(GRAPEVINE_TYPE)).getBottle().getDefaultInstance();

                int newStorage = storage - 2;
                BlockState updated = world.getBlockState(pos).setValue(STORAGE, newStorage);
                if (newStorage == 0) {
                    updated = updated.setValue(STAGE, 0).setValue(GRAPEVINE_TYPE, GrapeTypeRegistry.NONE);
                }
                world.setBlock(pos, updated, Block.UPDATE_ALL);

                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                if (!player.getInventory().add(output)) {
                    player.drop(output, false, false);
                }
                cir.setReturnValue(ItemInteractionResult.SUCCESS);
                return;
            }
        }

        if (stack.is(Items.BUCKET)) {
            if (stage == 6 && storage == 8) {
                GrapeType grapeType = (GrapeType) state.getValue(GRAPEVINE_TYPE);
                String id = grapeType.getId();
                ResourceLocation bucketId = switch (id) {
                    case "red" -> ResourceLocation.fromNamespaceAndPath("vkbc", "red_grape_juice_bucket");
                    case "white" -> ResourceLocation.fromNamespaceAndPath("vkbc", "white_grape_juice_bucket");
                    case "savanna_red" -> ResourceLocation.fromNamespaceAndPath("vkbc", "savanna_red_grape_juice_bucket");
                    case "savanna_white" -> ResourceLocation.fromNamespaceAndPath("vkbc", "savanna_white_grape_juice_bucket");
                    case "taiga_red" -> ResourceLocation.fromNamespaceAndPath("vkbc", "taiga_red_grape_juice_bucket");
                    case "taiga_white" -> ResourceLocation.fromNamespaceAndPath("vkbc", "taiga_white_grape_juice_bucket");
                    case "jungle_red" -> ResourceLocation.fromNamespaceAndPath("vkbc", "jungle_red_grape_juice_bucket");
                    case "jungle_white" -> ResourceLocation.fromNamespaceAndPath("vkbc", "jungle_white_grape_juice_bucket");
                    default -> null;
                };
                if (bucketId == null) {
                    cir.setReturnValue(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
                    return;
                }

                Item bucketItem = BuiltInRegistries.ITEM.get(bucketId);
                if (bucketItem != BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getDefaultKey())) {
                    ItemStack outputBucket = new ItemStack(bucketItem);

                    BlockState reset = world.getBlockState(pos)
                            .setValue(STORAGE, 0)
                            .setValue(STAGE, 0)
                            .setValue(GRAPEVINE_TYPE, GrapeTypeRegistry.NONE);
                    world.setBlock(pos, reset, Block.UPDATE_ALL);

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }

                    if (!player.getInventory().add(outputBucket)) {
                        player.drop(outputBucket, false, false);
                    }

                    cir.setReturnValue(ItemInteractionResult.SUCCESS);
                    return;
                }
            }
        }

        cir.setReturnValue(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
    }
}
