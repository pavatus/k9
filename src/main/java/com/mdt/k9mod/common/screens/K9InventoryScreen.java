package com.mdt.k9mod.common.screens;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.container.K9InventoryContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class K9InventoryScreen extends AbstractContainerScreen<K9InventoryContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(K9Mod.MOD_ID, "textures/gui/k9_inventory_gui.png");
    private int battery;

    public K9InventoryScreen(K9InventoryContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageHeight++;
        this.battery = container.getBattery();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font, this.title, this.leftPos + 8, this.topPos + 7, 0xf5f5f5); // "screen.k9mod.k9_gui" text
        graphics.drawString(this.font, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 73, 0xf5f5f5); // "Inventory" text
        graphics.drawString(this.font, this.battery + "%", (this.width - font.width(this.battery + "%") + 130) / 2, (this.height - 155) / 2, 0x73e858); // "100%" text
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float mouseX, int mouseY, int partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE,this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {}
}

