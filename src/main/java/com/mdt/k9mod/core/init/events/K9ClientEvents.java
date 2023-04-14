package com.mdt.k9mod.core.init.events;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.client.models.entities.K9Model;
import com.mdt.k9mod.client.renderers.entity.K9Renderer;
import com.mdt.k9mod.core.init.K9modEntities;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = K9Mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class K9ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
        renderers.registerEntityRenderer(K9modEntities.K9.get(), K9Renderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(K9Model.LAYER_LOCATION, K9Model::createBodyLayer);
    }
}
