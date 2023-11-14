package mdt.k9mod.client.screen;

import mdt.k9mod.K9mod;
import mdt.k9mod.client.screen.screen_handler.K9ScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class K9InventoryScreen extends HandledScreen<K9ScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(K9mod.MOD_ID, "textures/gui/container/k9_inventory.png");

    public K9InventoryScreen(K9ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth("K9")) / 2;
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        String batteryLevel = this.getScreenHandler().getBattery() + "%";
        context.drawCenteredTextWithShadow(this.textRenderer, Text.of(batteryLevel), (this.backgroundWidth - this.textRenderer.getWidth(batteryLevel) + 150) / 2, (this.backgroundHeight - 153) / 2, 0x00ff00);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        //context.getMatrices().push();
        //context.getMatrices().translate(0.0f, 0.0f, 100.0f);
        //context.drawItem(new ItemStack(Items.NETHERITE_INGOT), i + 20, j + 109);
        //context.drawItem(new ItemStack(Items.EMERALD), i + 41, j + 109);
        //context.drawItem(new ItemStack(Items.DIAMOND), i + 41 + 22, j + 109);
        //context.drawItem(new ItemStack(Items.GOLD_INGOT), i + 42 + 44, j + 109);
        //context.drawItem(new ItemStack(Items.IRON_INGOT), i + 42 + 66, j + 109);
        //context.getMatrices().pop();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}