package com.fvue233.vkbc.mixin;

import com.github.ysbbbbbb.kaleidoscopetavern.client.render.block.BarCabinetBlockEntityRender;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.satisfy.vinery.core.block.WineBottleBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BarCabinetBlockEntityRender.class)
public abstract class BarCabinetBlockEntityRenderMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"), remap = false)
    private BlockState vkbc$modifyVineryBlockState(Block instance) {
        BlockState state = instance.defaultBlockState();
        // Check if the block belongs to Vinery
        net.minecraft.resources.ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(instance);
        if ("vinery".equals(key.getNamespace())) {
            // Set fake_model to false if it exists to show the actual model
            for (Property<?> property : state.getProperties()) {
                if (property.getName().equals("fake_model")) {
                    state = vkbc$setFakeModel(state, property);
                    break;
                }
            }
        }
        return state;
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> BlockState vkbc$setFakeModel(BlockState state, Property<T> property) {
        if (property.getValueClass() == Boolean.class) {
            return state.setValue((Property<Boolean>) property, false);
        }
        return state;
    }
}
