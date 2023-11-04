package mdt.falloutmod.core.screens;

import mdt.falloutmod.FalloutMod;
import mdt.falloutmod.core.items.PipColor;
import mdt.falloutmod.core.items.PipboyItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PipboyMainScreen extends Screen {

    public static final Identifier PIPFRAME = new Identifier(FalloutMod.MOD_ID, "textures/gui/pipboy/pipboy_frame.png");
    public static final Identifier DEFAULTPBSCRN = new Identifier(FalloutMod.MOD_ID, "textures/gui/pipboy/pipboy_default.png");
    //new Identifier(FalloutMod.MOD_ID, "textures/gui/pipboy/pipboy_whitetest.png");
    private ClientPlayerEntity clientPlayer;

    public PipboyMainScreen(ClientPlayerEntity client) {
        super(Text.empty());
        this.clientPlayer = client;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext ctx, int mX, int mY, float delta) {
        this.renderPipboyBackground(ctx);
        super.render(ctx, mX, mY, delta);
    }
    public void renderPipboyBackground(DrawContext ctx) {
        Item pipInMainHand = this.clientPlayer.getMainHandStack().getItem();
        if(pipInMainHand instanceof PipboyItem pipboy) {
            int i = (this.width - 253) / 2;
            int j = (this.height - 131) / 2;
            float[] shaderColor = whichPipColor(pipboy.getPipColor());
            ctx.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], 1);
            //ctx.drawTexture(DEFAULTPBSCRN, i, j, 0.0f, 0.0f, 253, 131, 256, 256);
            ctx.drawTexture(new Identifier(FalloutMod.MOD_ID, "textures/gui/pipboy/whitetesting/pipboy_stats_white.png"), i, j, 0.0f, 0.0f, 253, 131, 256, 256);
            ctx.setShaderColor(1, 1, 1, 1);
            ctx.drawTexture(PIPFRAME, i, j, 0.0f, 0.0f, 253, 131, 256, 256);
        }
    }

    public float[] whichPipColor(PipColor color) {
        switch (color) {
            case GREEN:
                return new float[]{0, 1, 0};
            case AMBER:
                return new float[]{1.25F, 0.75F, 0};
            case RED:
                return new float[]{1, 0, 0};
            case ICE_BLUE:
                return new float[]{0, 0.99F, 1};
            case INDIGO:
                return new float[]{0.75F, 0, 1};
            default:
                return new float[]{1, 1, 1};
        }
    }
}
