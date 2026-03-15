package com.fvue233.vkbc.mixin;

import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = DrinkBlockItem.class, remap = false)
public interface DrinkBlockItemAccessor {
    @Accessor("bottleSize")
    DrinkBlockItem.BottleSize getBottleSize();
}
