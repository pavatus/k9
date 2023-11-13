package mdt.k9mod.client.renderers;

import mdt.k9mod.K9mod;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.client.models.K9EntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class K9GlowFeatureRenderer<T extends K9Entity, M extends K9EntityModel<T>> extends EyesFeatureRenderer<T, M> {
    private static final RenderLayer GLOW = RenderLayer.getEyes(new Identifier(K9mod.MOD_ID, "textures/entity/k9/k9_emission.png"));

    public K9GlowFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    public RenderLayer getEyesTexture() {
        return GLOW;
    }
}
