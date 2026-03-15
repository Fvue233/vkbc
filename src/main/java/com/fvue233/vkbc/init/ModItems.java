package com.fvue233.vkbc.init;

import com.fvue233.vkbc.VineryKaleidoscopeTavernbottlecompat;
import com.fvue233.vkbc.item.JuiceBucketItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, VineryKaleidoscopeTavernbottlecompat.MODID);

    public static final Supplier<Item> RED_GRAPE_JUICE_BUCKET = ITEMS.register("red_grape_juice_bucket", () -> new JuiceBucketItem(ModItems::kaleidoscopeGrapeJuice));
    public static final Supplier<Item> WHITE_GRAPE_JUICE_BUCKET = registerBucket("white_grape_juice_bucket", ModFluids.WHITE_GRAPE_JUICE);
    public static final Supplier<Item> SAVANNA_RED_GRAPE_JUICE_BUCKET = registerBucket("savanna_red_grape_juice_bucket", ModFluids.SAVANNA_RED_GRAPE_JUICE);
    public static final Supplier<Item> SAVANNA_WHITE_GRAPE_JUICE_BUCKET = registerBucket("savanna_white_grape_juice_bucket", ModFluids.SAVANNA_WHITE_GRAPE_JUICE);
    public static final Supplier<Item> TAIGA_RED_GRAPE_JUICE_BUCKET = registerBucket("taiga_red_grape_juice_bucket", ModFluids.TAIGA_RED_GRAPE_JUICE);
    public static final Supplier<Item> TAIGA_WHITE_GRAPE_JUICE_BUCKET = registerBucket("taiga_white_grape_juice_bucket", ModFluids.TAIGA_WHITE_GRAPE_JUICE);
    public static final Supplier<Item> JUNGLE_RED_GRAPE_JUICE_BUCKET = registerBucket("jungle_red_grape_juice_bucket", ModFluids.JUNGLE_RED_GRAPE_JUICE);
    public static final Supplier<Item> JUNGLE_WHITE_GRAPE_JUICE_BUCKET = registerBucket("jungle_white_grape_juice_bucket", ModFluids.JUNGLE_WHITE_GRAPE_JUICE);
    public static final Supplier<Item> APPLE_JUICE_BUCKET = registerBucket("apple_juice_bucket", ModFluids.APPLE_JUICE);

    private static Supplier<Item> registerBucket(String name, Supplier<? extends net.minecraft.world.level.material.Fluid> fluid) {
        return ITEMS.register(name, () -> new JuiceBucketItem(fluid::get));
    }

    private static Fluid kaleidoscopeGrapeJuice() {
        return BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "grape_juice"));
    }
}
