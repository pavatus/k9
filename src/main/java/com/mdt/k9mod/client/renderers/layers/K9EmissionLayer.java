package com.mdt.k9mod.client.renderers.layers;

import com.mdt.k9mod.client.renderers.entity.AITAbstractEyesLayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class K9EmissionLayer<T extends Entity, M extends SpiderModel<T>> extends AITAbstractEyesLayer<T, M> {
    private static final RenderType K9_EMISSION = RenderType.eyes(new ResourceLocation(com.mdt.K9mod.K9MOD.MOD_ID, "textures/entity/k9_emission.png"));

    public K9EmissionLayer(IEntityRenderer<T, M> p_116981_) {
       super(p_116981_);
    }
    @Override
    public RenderType renderType() {
        return K9_EMISSION;
    }
}
