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
import org.jetbrains.annotations.NotNull;

@Mod(value = VineryKaleidoscopeTavernbottlecompat.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = VineryKaleidoscopeTavernbottlecompat.MODID, value = Dist.CLIENT)
public class VineryKaleidoscopeTavernbottlecompatClient {

    public static final IClientFluidTypeExtensions PURPLE_GRAPE_JUICE_EX = new IClientFluidTypeExtensions() {
        private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "block/grape_juice_still");
        private static final ResourceLocation FLOW = ResourceLocation.fromNamespaceAndPath("kaleidoscope_tavern", "block/grape_juice_flow");

        @Override
        public @NotNull ResourceLocation getStillTexture() {
            return STILL;
        }

        @Override
        public @NotNull ResourceLocation getFlowingTexture() {
            return FLOW;
        }
    };

    public static final IClientFluidTypeExtensions WHITE_GRAPE_JUICE_EX = new IClientFluidTypeExtensions() {
        private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("vkbc", "block/white_grape_juice_still");
        private static final ResourceLocation FLOW = ResourceLocation.fromNamespaceAndPath("vkbc", "block/white_grape_juice_flow");

        @Override
        public @NotNull ResourceLocation getStillTexture() {
            return STILL;
        }

        @Override
        public @NotNull ResourceLocation getFlowingTexture() {
            return FLOW;
        }
    };


    public VineryKaleidoscopeTavernbottlecompatClient(ModContainer container) {
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
        event.registerFluidType(WHITE_GRAPE_JUICE_EX, ModFluidTypes.WHITE_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(PURPLE_GRAPE_JUICE_EX, ModFluidTypes.SAVANNA_RED_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(WHITE_GRAPE_JUICE_EX, ModFluidTypes.SAVANNA_WHITE_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(PURPLE_GRAPE_JUICE_EX, ModFluidTypes.TAIGA_RED_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(WHITE_GRAPE_JUICE_EX, ModFluidTypes.TAIGA_WHITE_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(PURPLE_GRAPE_JUICE_EX, ModFluidTypes.JUNGLE_RED_GRAPE_JUICE_TYPE.get());
        event.registerFluidType(WHITE_GRAPE_JUICE_EX, ModFluidTypes.JUNGLE_WHITE_GRAPE_JUICE_TYPE.get());
    }
}
