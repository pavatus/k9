package com.mdt.k9mod.screen;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.container.K9InventoryContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ShulkerBoxContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class K9InventoryScreen extends ContainerScreen<K9InventoryContainer> {
    private static final ResourceLocation GUI = new ResourceLocation(K9Mod.MOD_ID,"textures/gui/k9_gui.png");

    public int battery;

    public K9InventoryScreen(K9InventoryContainer p_i51078_1_, PlayerInventory p_i51078_2_, ITextComponent p_i51078_3_) {
        super(p_i51078_1_, p_i51078_2_, p_i51078_3_);
        ++this.imageHeight;
        this.battery = p_i51078_1_.getBattery();
    }

    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
        String labeling = this.battery + "%";
        createText(p_230430_1_, labeling, (this.width - getStringWidth(labeling) + 130) / 2, (this.height - 155) / 2, 0x73e858);
    }

    private int getStringWidth(String label) {
        FontRenderer font = Minecraft.getInstance().font;
        return font.width(label);
    }

    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    public static void createText(MatrixStack matrixStack, String label, int xPos, int yPos, int colour) {
        FontRenderer font = Minecraft.getInstance().font;
        font.draw(matrixStack,label,xPos,yPos,colour);
    }
}