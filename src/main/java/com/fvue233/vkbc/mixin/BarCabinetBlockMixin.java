package com.fvue233.vkbc.mixin;

import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.BarCabinetBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.BottleBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BarCabinetBlock.class)
public abstract class BarCabinetBlockMixin {

    @Unique
    private static final TagKey<Item> VKBC$BOTTLES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "bottles"));

    @Inject(method = "getBottleBlock", at = @At("HEAD"), cancellable = true, remap = false)
    private void vkbc$getVineryBottleBlock(ItemStack stack, CallbackInfoReturnable<BottleBlock> cir) {
        // 增加更通用的命名空间检查，防止 instanceof 失败
        net.minecraft.resources.ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        if ("vinery".equals(key.getNamespace())) {
            // 1. 首先尝试检测是否是 Vinery 的 DrinkBlockItem
            boolean isDrinkItem = stack.getItem() instanceof DrinkBlockItem;
            if (!isDrinkItem) {
                String className = stack.getItem().getClass().getName();
                if (className.contains("DrinkBlockItem") || className.contains("WineBottleItem")) {
                    isDrinkItem = true;
                }
            }

            // 2. 只有是 DrinkBlockItem 或者在我们的酒瓶标签中的才允许放入
            boolean shouldAllow = isDrinkItem || stack.is(VKBC$BOTTLES);
            
            if (!shouldAllow) {
                // 不是酒瓶，不设置返回值，由原版逻辑决定（通常会拒绝）
                return;
            }

            // 3. 确定是酒瓶后，再决定它是大瓶还是小瓶
            try {
                DrinkBlockItem.BottleSize size = null;
                if (stack.getItem() instanceof DrinkBlockItem drinkItem) {
                    size = ((DrinkBlockItemAccessor) drinkItem).getBottleSize();
                } else {
                    // 反射兜底
                    try {
                        java.lang.reflect.Field field = stack.getItem().getClass().getDeclaredField("bottleSize");
                        field.setAccessible(true);
                        size = (DrinkBlockItem.BottleSize) field.get(stack.getItem());
                    } catch (Exception e) {
                        // 如果连反射也拿不到 size，默认当做普通瓶子
                    }
                }

                if (size == DrinkBlockItem.BottleSize.BIG) {
                    cir.setReturnValue((BottleBlock) ModBlocks.BRANDY.get());
                    return;
                }
            } catch (Exception ignored) {
            }

            // 默认当做普通瓶子（小瓶）
            cir.setReturnValue((BottleBlock) ModBlocks.EMPTY_BOTTLE.get());
        }
    }
}
