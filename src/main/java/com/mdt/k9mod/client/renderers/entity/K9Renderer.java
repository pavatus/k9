package com.mdt.k9mod.client.renderers.entity;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.client.models.entities.K9Model;
import com.mdt.k9mod.common.entities.K9Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class K9Renderer extends MobRenderer<K9Entity, K9Model<K9Entity>> {
    private ResourceLocation texture;
    public static final ResourceLocation SCARF_TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/entity/k9_skins/k9_scarf.png");

    public K9Renderer(EntityRendererProvider.Context context) {
        super(context, new K9Model<>(context.bakeLayer(K9Model.LAYER_LOCATION)), 0.35f);
        this.model = new K9Model<>(context.bakeLayer(K9Model.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(K9Entity p_114482_) {
        this.texture = dyeColourToTexture(p_114482_.getCollarColor());

        if (needsScarf(p_114482_)) {
            this.texture = SCARF_TEXTURE;
        }

        return this.texture;
    }

    private ResourceLocation dyeColourToTexture(DyeColor color) {
        String name = color.getName();
        String baseLocation = "textures/entity/k9_skins/k9_";

        ResourceLocation location = new ResourceLocation(K9Mod.MOD_ID, baseLocation + name + ".png");

        return location;
    }

    private boolean needsScarf(K9Entity k9) {
        return (k9.hasCustomName()) && (k9.getCustomName().getString().equals("Tom"));
    }
}
