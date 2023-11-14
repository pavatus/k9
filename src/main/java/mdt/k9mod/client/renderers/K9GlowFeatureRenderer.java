package mdt.k9mod.client.renderers;

import mdt.k9mod.K9mod;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.client.models.K9EntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class K9GlowFeatureRenderer<T extends K9Entity, M extends K9EntityModel<T>> extends EyesFeatureRenderer<T, M> {
    public RenderLayer GLOW;

    public K9GlowFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext, Identifier GlowTexture) {
        super(featureRendererContext);
        this.GLOW = RenderLayer.getEyes(GlowTexture);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(!entity.isAiDisabled()) super.render(matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return this.GLOW;
    }
}
