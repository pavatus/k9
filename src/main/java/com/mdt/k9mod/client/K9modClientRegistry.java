package com.mdt.k9mod.client;

import com.google.common.collect.Maps;
import com.mdt.k9mod.K9Mod;

import com.mdt.k9mod.client.renderers.entity.K9EntityRenderer;
import com.mdt.k9mod.container.K9InventoryContainer;
import com.mdt.k9mod.core.init.K9ContainerType;
import com.mdt.k9mod.core.init.K9modBlocks;
import com.mdt.k9mod.core.init.K9modEntities;
import com.mdt.k9mod.screen.K9InventoryScreen;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.IScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = K9Mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class K9modClientRegistry {

        @SubscribeEvent
        public static void register(FMLClientSetupEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(K9modEntities.K9.get(), K9EntityRenderer::new);
            event.enqueueWork(() ->
                    RenderTypeLookup.setRenderLayer(K9modBlocks.K9_CRATE.get(), RenderType.cutoutMipped()));
            event.enqueueWork(() -> ScreenManager.register(K9ContainerType.K9_GUI, K9InventoryScreen::new));
        }
}
