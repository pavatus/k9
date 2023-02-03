package com.mdt.k9mod.client.renderers.entity;

import com.mdt.k9mod.client.models.entities.K9EntityModel;
import com.mdt.k9mod.client.renderers.layers.K9EmissionLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class K9EntityRenderer extends MobRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(com.mdt.K9mod.K9MOD.MOD_ID, "textures/entity/k9.png");

    public K9EntityRenderer(EntityRendererManager rendererManagerIn) {
        super(rendererManagerIn, new K9EntityModel(), 0.35f);
        this.addLayer(new K9EmissionLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_110775_1_) {
        return TEXTURE;
    }
}