package com.mdt.k9mod.screen;

import com.mdt.k9mod.container.K9InventoryContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class K9InventoryScreen extends ContainerScreen<K9InventoryContainer> {
    private static final ResourceLocation GUI = new ResourceLocation("textures/gui/container/k9_gui.png");
    private float xMouse;
    private float yMouse;

    public K9InventoryScreen(K9InventoryContainer p_i51084_1_, PlayerInventory p_i51084_2_, ITextComponent textComponent) {
        super(p_i51084_1_, p_i51084_2_,textComponent);
        this.passEvents = false;
    }

    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        this.xMouse = (float)p_230430_2_;
        this.yMouse = (float)p_230430_3_;
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
    }
}