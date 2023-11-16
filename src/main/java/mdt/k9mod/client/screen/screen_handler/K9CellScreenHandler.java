package mdt.k9mod.client.screen.screen_handler;

import mdt.k9mod.item.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;

public class K9CellScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public K9CellScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        this(type, syncId, playerInventory, new SimpleInventory(27), new ArrayPropertyDelegate(1));
    }

    public static K9CellScreenHandler createDefault(int syncId, PlayerInventory playerInventory) {
        return new K9CellScreenHandler(ScreenHandlerInit.K9_CELL_HANDLER, syncId, playerInventory);
    }

    public K9CellScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        int l;
        int k;
        GenericContainerScreenHandler.checkSize(inventory, 27);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        inventory.onOpen(playerInventory.player);
        for (k = 0; k < 2; ++k) {
            for (l = 0; l < 4; ++l) {
                this.addSlot(new ShulkerBoxSlot(inventory, l + k * 4, 26 + l * 36, 24 + k * 23));
            }
        }
        for (k = 0; k < 3; ++k) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + k * 9 + 9, 8 + l * 18, 84 + k * 18));
            }
        }
        for (k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            if(itemStack2.getItem() != ItemInit.K9_LITHIUM_CELL) return itemStack;
            itemStack = itemStack2.copy();
            if (slot < 8 ? !this.insertItem(itemStack2, 27, this.slots.size(), true) : !this.insertItem(itemStack2, 0, 27, false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        if(stack.getItem() == ItemInit.K9_LITHIUM_CELL) {
            return super.canInsertIntoSlot(stack, slot);
        } else return super.canInsertIntoSlot(ItemStack.EMPTY, slot);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getBattery() {
        return propertyDelegate.get(0);
    }
}
