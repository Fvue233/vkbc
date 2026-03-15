package com.fvue233.vkbc;

import com.fvue233.vkbc.init.ModFluidTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.util.function.Supplier;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = VineryKaleidoscopeTavernbottlecompat.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = VineryKaleidoscopeTavernbottlecompat.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class VineryKaleidoscopeTavernbottlecompatClient {
    public VineryKaleidoscopeTavernbottlecompatClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        VineryKaleidoscopeTavernbottlecompat.LOGGER.info("HELLO FROM CLIENT SETUP");
        VineryKaleidoscopeTavernbottlecompat.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        IClientFluidTypeExtensions extensions = new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "block/grape_juice_still");
            private static final ResourceLocation FLOW = ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "block/grape_juice_flow");

            @Override
            public ResourceLocation getStillTexture() {
                return STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return FLOW;
            }
        };

        event.registerFluidType(extensions, ModFluidTypes.WHITE_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(extensions, ModFluidTypes.SAVANNA_RED_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(extensions, ModFluidTypes.SAVANNA_WHITE_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(extensions, ModFluidTypes.TAIGA_RED_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(extensions, ModFluidTypes.TAIGA_WHITE_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(extensions, ModFluidTypes.JUNGLE_RED_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(extensions, ModFluidTypes.JUNGLE_WHITE_GRAPE_JUICE_TYPE.get());
    }
}
