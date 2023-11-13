package mdt.k9mod.renderers;

import mdt.k9mod.K9mod;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.models.K9EntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class K9EntityRenderer extends MobEntityRenderer<K9Entity, K9EntityModel<K9Entity>> {
    private static final Identifier WILD_TEXTURE = new Identifier(K9mod.MOD_ID, "textures/entity/k9/k9.png");
    private static final Identifier TAMED_TEXTURE = new Identifier(K9mod.MOD_ID, "textures/entity/k9/k9_red.png");
    private static final Identifier ANGRY_TEXTURE = new Identifier(K9mod.MOD_ID, "textures/entity/k9/k9_scarf.png");

    //K9EntityModel<K9Entity> model;

    public K9EntityRenderer(EntityRendererFactory.Context context) {
        super(context, new K9EntityModel<>(K9EntityModel.getTexturedModelData().createModel()), 0.5F);
        //this.model = new K9EntityModel<>(K9EntityModel.getTexturedModelData().createModel());
        this.addFeature(new K9GlowFeatureRenderer<>(this));
    }

    protected float getAnimationProgress(K9Entity K9Entity, float f) {
        return K9Entity.getTailAngle();
    }

    public void render(K9Entity k9Entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        //if (K9Entity.isFurWet()) {
        //    float h = K9Entity.getFurWetBrightnessMultiplier(g);
        //    ((K9EntityModel)this.model).setColorMultiplier(h, h, h);
        //}

        super.render(k9Entity, f, g, matrixStack, vertexConsumerProvider, i);
        //if (K9Entity.isFurWet()) {
        //    ((K9EntityModel)this.model).setColorMultiplier(1.0F, 1.0F, 1.0F);
        //}

    }

    public Identifier getTexture(K9Entity K9Entity) {
        if (K9Entity.isTamed()) {
            return TAMED_TEXTURE;
        } else {
            return K9Entity.hasAngerTime() ? ANGRY_TEXTURE : WILD_TEXTURE;
        }
    }
}
