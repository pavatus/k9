package com.mdt.k9mod.client.screens;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.container.K9InventoryContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;

import java.awt.*;

public class K9InventoryScreen extends AbstractContainerScreen<K9InventoryContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/gui/example_chest.png");

    public K9InventoryScreen(K9InventoryContainer container, Inventory playerInv, Component title) {
            super(container, playerInv, title);
            this.leftPos = 0;
            this.topPos = 0;
            }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
            super.render(stack, mouseX, mouseY, partialTicks);
            this.font.draw(stack, this.title, this.leftPos + 20, this.topPos + 5, 0x404040);
            this.font.draw(stack, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 75, 0x404040);
            }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float mouseX, int mouseY, int partialTicks) {
        renderBackground(stack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {}
}

