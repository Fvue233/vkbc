package com.fvue233.vkbc.mixin;

import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.PressingTubBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = PressingTubBlockEntity.class, remap = false)
public abstract class PressingTubBlockEntityMixin {

    @Shadow @Final private FluidTank fluid;

    @Unique
    private static final Map<ResourceLocation, ResourceLocation> VKBC$FLUID_TO_JUICE = new HashMap<>();

    static {
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "red_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "white_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "white_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "savanna_red_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "red_savanna_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "savanna_white_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "white_savanna_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "taiga_red_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "red_taiga_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "taiga_white_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "white_taiga_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "jungle_red_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "red_jungle_grapejuice"));
        VKBC$FLUID_TO_JUICE.put(ResourceLocation.fromNamespaceAndPath("vkbc", "jungle_white_grape_juice"), ResourceLocation.fromNamespaceAndPath("vinery", "white_jungle_grapejuice"));
    }

    @Inject(method = "getResult", at = @At("HEAD"), cancellable = true)
    private void vkbc$handleVineryBottle(LivingEntity target, ItemStack carriedStack, CallbackInfoReturnable<Boolean> cir) {
        if (carriedStack.isEmpty()) return;

        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(carriedStack.getItem());
        if (!"vinery".equals(itemKey.getNamespace()) || !"wine_bottle".equals(itemKey.getPath())) {
            return;
        }

        if (this.fluid.getFluidAmount() < 250) {
            return;
        }

        Fluid currentFluid = this.fluid.getFluid().getFluid();
        ResourceLocation fluidKey = BuiltInRegistries.FLUID.getKey(currentFluid);
        ResourceLocation juiceItemKey = VKBC$FLUID_TO_JUICE.get(fluidKey);

        if (juiceItemKey != null) {
            Item juiceItem = BuiltInRegistries.ITEM.get(juiceItemKey);
            if (juiceItem != BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getDefaultKey())) {
                // 成功识别
                if (!target.level().isClientSide) {
                    this.fluid.drain(250, FluidTank.FluidAction.EXECUTE);
                    ItemStack resultStack = new ItemStack(juiceItem);
                    
                    if (target instanceof Player player) {
                        if (!player.getAbilities().instabuild) {
                            carriedStack.shrink(1);
                        }
                        
                        if (!player.getInventory().add(resultStack)) {
                            player.drop(resultStack, false);
                        }
                    } else {
                        carriedStack.shrink(1);
                    }
                    
                    target.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                cir.setReturnValue(true);
            }
        }
    }
}
