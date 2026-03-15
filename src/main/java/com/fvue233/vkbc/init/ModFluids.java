package com.fvue233.vkbc.init;

import com.fvue233.vkbc.VineryKaleidoscopeTavernbottlecompat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, VineryKaleidoscopeTavernbottlecompat.MODID);

    public static final Supplier<Fluid> WHITE_GRAPE_JUICE = FLUIDS.register("white_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("white_grape_juice", ModFluidTypes.WHITE_GRAPE_JUICE_TYPE, ModFluids.WHITE_GRAPE_JUICE, ModFluids.FLOWING_WHITE_GRAPE_JUICE, ModItems.WHITE_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_WHITE_GRAPE_JUICE = FLUIDS.register("flowing_white_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("white_grape_juice", ModFluidTypes.WHITE_GRAPE_JUICE_TYPE, ModFluids.WHITE_GRAPE_JUICE, ModFluids.FLOWING_WHITE_GRAPE_JUICE, ModItems.WHITE_GRAPE_JUICE_BUCKET)));

    public static final Supplier<Fluid> SAVANNA_RED_GRAPE_JUICE = FLUIDS.register("savanna_red_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("savanna_red_grape_juice", ModFluidTypes.SAVANNA_RED_GRAPE_JUICE_TYPE, ModFluids.SAVANNA_RED_GRAPE_JUICE, ModFluids.FLOWING_SAVANNA_RED_GRAPE_JUICE, ModItems.SAVANNA_RED_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_SAVANNA_RED_GRAPE_JUICE = FLUIDS.register("flowing_savanna_red_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("savanna_red_grape_juice", ModFluidTypes.SAVANNA_RED_GRAPE_JUICE_TYPE, ModFluids.SAVANNA_RED_GRAPE_JUICE, ModFluids.FLOWING_SAVANNA_RED_GRAPE_JUICE, ModItems.SAVANNA_RED_GRAPE_JUICE_BUCKET)));

    public static final Supplier<Fluid> SAVANNA_WHITE_GRAPE_JUICE = FLUIDS.register("savanna_white_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("savanna_white_grape_juice", ModFluidTypes.SAVANNA_WHITE_GRAPE_JUICE_TYPE, ModFluids.SAVANNA_WHITE_GRAPE_JUICE, ModFluids.FLOWING_SAVANNA_WHITE_GRAPE_JUICE, ModItems.SAVANNA_WHITE_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_SAVANNA_WHITE_GRAPE_JUICE = FLUIDS.register("flowing_savanna_white_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("savanna_white_grape_juice", ModFluidTypes.SAVANNA_WHITE_GRAPE_JUICE_TYPE, ModFluids.SAVANNA_WHITE_GRAPE_JUICE, ModFluids.FLOWING_SAVANNA_WHITE_GRAPE_JUICE, ModItems.SAVANNA_WHITE_GRAPE_JUICE_BUCKET)));

    public static final Supplier<Fluid> TAIGA_RED_GRAPE_JUICE = FLUIDS.register("taiga_red_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("taiga_red_grape_juice", ModFluidTypes.TAIGA_RED_GRAPE_JUICE_TYPE, ModFluids.TAIGA_RED_GRAPE_JUICE, ModFluids.FLOWING_TAIGA_RED_GRAPE_JUICE, ModItems.TAIGA_RED_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_TAIGA_RED_GRAPE_JUICE = FLUIDS.register("flowing_taiga_red_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("taiga_red_grape_juice", ModFluidTypes.TAIGA_RED_GRAPE_JUICE_TYPE, ModFluids.TAIGA_RED_GRAPE_JUICE, ModFluids.FLOWING_TAIGA_RED_GRAPE_JUICE, ModItems.TAIGA_RED_GRAPE_JUICE_BUCKET)));

    public static final Supplier<Fluid> TAIGA_WHITE_GRAPE_JUICE = FLUIDS.register("taiga_white_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("taiga_white_grape_juice", ModFluidTypes.TAIGA_WHITE_GRAPE_JUICE_TYPE, ModFluids.TAIGA_WHITE_GRAPE_JUICE, ModFluids.FLOWING_TAIGA_WHITE_GRAPE_JUICE, ModItems.TAIGA_WHITE_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_TAIGA_WHITE_GRAPE_JUICE = FLUIDS.register("flowing_taiga_white_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("taiga_white_grape_juice", ModFluidTypes.TAIGA_WHITE_GRAPE_JUICE_TYPE, ModFluids.TAIGA_WHITE_GRAPE_JUICE, ModFluids.FLOWING_TAIGA_WHITE_GRAPE_JUICE, ModItems.TAIGA_WHITE_GRAPE_JUICE_BUCKET)));

    public static final Supplier<Fluid> JUNGLE_RED_GRAPE_JUICE = FLUIDS.register("jungle_red_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("jungle_red_grape_juice", ModFluidTypes.JUNGLE_RED_GRAPE_JUICE_TYPE, ModFluids.JUNGLE_RED_GRAPE_JUICE, ModFluids.FLOWING_JUNGLE_RED_GRAPE_JUICE, ModItems.JUNGLE_RED_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_JUNGLE_RED_GRAPE_JUICE = FLUIDS.register("flowing_jungle_red_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("jungle_red_grape_juice", ModFluidTypes.JUNGLE_RED_GRAPE_JUICE_TYPE, ModFluids.JUNGLE_RED_GRAPE_JUICE, ModFluids.FLOWING_JUNGLE_RED_GRAPE_JUICE, ModItems.JUNGLE_RED_GRAPE_JUICE_BUCKET)));

    public static final Supplier<Fluid> JUNGLE_WHITE_GRAPE_JUICE = FLUIDS.register("jungle_white_grape_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("jungle_white_grape_juice", ModFluidTypes.JUNGLE_WHITE_GRAPE_JUICE_TYPE, ModFluids.JUNGLE_WHITE_GRAPE_JUICE, ModFluids.FLOWING_JUNGLE_WHITE_GRAPE_JUICE, ModItems.JUNGLE_WHITE_GRAPE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_JUNGLE_WHITE_GRAPE_JUICE = FLUIDS.register("flowing_jungle_white_grape_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("jungle_white_grape_juice", ModFluidTypes.JUNGLE_WHITE_GRAPE_JUICE_TYPE, ModFluids.JUNGLE_WHITE_GRAPE_JUICE, ModFluids.FLOWING_JUNGLE_WHITE_GRAPE_JUICE, ModItems.JUNGLE_WHITE_GRAPE_JUICE_BUCKET)));

         public static final Supplier<Fluid> APPLE_JUICE = FLUIDS.register("apple_juice", 
        () -> new BaseFlowingFluid.Source(createProperties("apple_juice", ModFluidTypes.APPLE_JUICE_TYPE, ModFluids.APPLE_JUICE, ModFluids.FLOWING_APPLE_JUICE, ModItems.APPLE_JUICE_BUCKET)));
    public static final Supplier<Fluid> FLOWING_APPLE_JUICE = FLUIDS.register("flowing_apple_juice", 
        () -> new BaseFlowingFluid.Flowing(createProperties("apple_juice", ModFluidTypes.APPLE_JUICE_TYPE, ModFluids.APPLE_JUICE, ModFluids.FLOWING_APPLE_JUICE, ModItems.APPLE_JUICE_BUCKET)));

    private static BaseFlowingFluid.Properties createProperties(String name, Supplier<net.neoforged.neoforge.fluids.FluidType> type, Supplier<Fluid> source, Supplier<Fluid> flowing, Supplier<Item> bucket) {
        return new BaseFlowingFluid.Properties(type, source, flowing).bucket(bucket);
    }
}
