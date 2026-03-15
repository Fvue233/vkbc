package com.fvue233.vkbc.init;

import com.fvue233.vkbc.VineryKaleidoscopeTavernbottlecompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, VineryKaleidoscopeTavernbottlecompat.MODID);

    public static final Supplier<FluidType> WHITE_GRAPE_JUICE_TYPE = registerJuice("white_grape_juice", 0);
    public static final Supplier<FluidType> SAVANNA_RED_GRAPE_JUICE_TYPE = registerJuice("savanna_red_grape_juice", 0);
    public static final Supplier<FluidType> SAVANNA_WHITE_GRAPE_JUICE_TYPE = registerJuice("savanna_white_grape_juice", 0);
    public static final Supplier<FluidType> TAIGA_RED_GRAPE_JUICE_TYPE = registerJuice("taiga_red_grape_juice", 0);
    public static final Supplier<FluidType> TAIGA_WHITE_GRAPE_JUICE_TYPE = registerJuice("taiga_white_grape_juice", 0);
    public static final Supplier<FluidType> JUNGLE_RED_GRAPE_JUICE_TYPE = registerJuice("jungle_red_grape_juice", 0);
    public static final Supplier<FluidType> JUNGLE_WHITE_GRAPE_JUICE_TYPE = registerJuice("jungle_white_grape_juice", 0);
    public static final Supplier<FluidType> APPLE_JUICE_TYPE = registerJuice("apple_juice", 0);

    private static Supplier<FluidType> registerJuice(String name, int lightLevel) {
        return FLUID_TYPES.register(name, () -> new FluidType(FluidType.Properties.create()
                .descriptionId("block.vkbc." + name)
                .fallDistanceModifier(0)
                .canExtinguish(true)
                .canConvertToSource(false)
                .supportsBoating(true)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .canHydrate(true)
                .lightLevel(lightLevel)));
    }
}
