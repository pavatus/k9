package mdt.k9mod.client.renderers;

import mdt.k9mod.K9mod;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.client.models.K9EntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class K9EntityRenderer extends MobEntityRenderer<K9Entity, K9EntityModel<K9Entity>> {
    private static final Identifier DEFAULT_TEXTURE = new Identifier(K9mod.MOD_ID, "textures/entity/k9/k9_red.png");
    private static final Identifier GLOW_TEXTURE = new Identifier(K9mod.MOD_ID, "textures/entity/k9/k9_emission.png");

    public K9EntityRenderer(EntityRendererFactory.Context context) {
        super(context, new K9EntityModel<>(K9EntityModel.getTexturedModelData().createModel()), 0.5F);
        this.addFeature(new K9GlowFeatureRenderer<>(this, GLOW_TEXTURE));
    }

    protected float getAnimationProgress(K9Entity K9Entity, float f) {
        return K9Entity.getTailAngle();
    }

    @Override
    public void render(K9Entity k9Entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(k9Entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(K9Entity k9Entity) {
        if(!k9Entity.isTamed()) return DEFAULT_TEXTURE;
        if(k9Entity.hasCustomName() && "bugger".equals(k9Entity.getName().getString())) return new Identifier(K9mod.MOD_ID,
                "/textures/entity/k9/k9_scarf.png");
        else return new Identifier(K9mod.MOD_ID,
                "/textures/entity/k9/k9_" + k9Entity.getCollarColor().getName() + ".png");
    }

}
