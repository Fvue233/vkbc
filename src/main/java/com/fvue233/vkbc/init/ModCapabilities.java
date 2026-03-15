package com.fvue233.vkbc.init;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

public class ModCapabilities {
    public static void register(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, ctx) -> new FluidBucketWrapper(stack),
                ModItems.RED_GRAPE_JUICE_BUCKET.get(),
                ModItems.WHITE_GRAPE_JUICE_BUCKET.get(),
                ModItems.SAVANNA_RED_GRAPE_JUICE_BUCKET.get(),
                ModItems.SAVANNA_WHITE_GRAPE_JUICE_BUCKET.get(),
                ModItems.TAIGA_RED_GRAPE_JUICE_BUCKET.get(),
                ModItems.TAIGA_WHITE_GRAPE_JUICE_BUCKET.get(),
                ModItems.JUNGLE_RED_GRAPE_JUICE_BUCKET.get(),
                ModItems.JUNGLE_WHITE_GRAPE_JUICE_BUCKET.get(),
                ModItems.APPLE_JUICE_BUCKET.get()
        );
    }
}

