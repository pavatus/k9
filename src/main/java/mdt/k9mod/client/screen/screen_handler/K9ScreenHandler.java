package mdt.k9mod.client.screen.screen_handler;

import com.google.common.base.Suppliers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;

public class K9ScreenHandler extends ScreenHandler {
    
    private final Inventory inventory;

    private final int rows;

    private int battery;

    private K9ScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, int rows) {
        this(type, syncId, playerInventory, new SimpleInventory(9 * rows), rows);
    }

    public static K9ScreenHandler create9x3(int syncId, PlayerInventory playerInventory) {
        return new K9ScreenHandler(ScreenHandlerInit.K9_INVENTORY_HANDLER, syncId, playerInventory, 3);
    }

    @Override
    public void setProperty(int id, int value) {
        super.setProperty(id, value);
        //this.updateBattery();
    }

    public K9ScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows) {
        super(type, syncId);
        int k;
        int j;
        GenericContainerScreenHandler.checkSize(inventory, rows * 9);
        this.inventory = inventory;
        this.rows = rows;
        this.setProperty(0, this.battery);
        inventory.onOpen(playerInventory.player);
        int i = (this.rows - 4) * 18;
        for (j = 0; j < this.rows; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }
        for (j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
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
            itemStack = itemStack2.copy();
            if (slot < this.rows * 9 ? !this.insertItem(itemStack2, this.rows * 9, this.slots.size(), true) : !this.insertItem(itemStack2, 0, this.rows * 9, false)) {
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
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getRows() {
        return this.rows;
    }

    //public void updateBattery() {
    //    int i;
    //    for (i = 0; i < this.slots.size(); ++i) {
    //        ItemStack itemStack = this.slots.get(i).getStack();
    //        com.google.common.base.Supplier<ItemStack> supplier = Suppliers.memoize(itemStack::copy);
    //        this.updateTrackedSlot(i, itemStack, supplier);
    //        this.checkSlotUpdates(i, itemStack, supplier);
    //    }
    //    this.checkCursorStackUpdates();
    //    for (i = 0; i < this.properties.size(); ++i) {
    //        Property property = this.properties.get(i);
    //        int j = property.get();
    //        if (property.hasChanged()) {
    //            this.notifyPropertyUpdate(i, j);
    //        }
    //        this.checkPropertyUpdates(i, j);
    //    }
    //}
//
    //private void checkPropertyUpdates(int id, int value) {
    //    if (this.disableSync) {
    //        return;
    //    }
    //    int i = this.trackedPropertyValues.getInt(id);
    //    if (i != value) {
    //        this.trackedPropertyValues.set(id, value);
    //        if (this.syncHandler != null) {
    //            this.syncHandler.updateProperty(this, id, value);
    //        }
    //    }
    //}
//
    //private void notifyPropertyUpdate(int index, int value) {
    //    for (ScreenHandlerListener screenHandlerListener : this.listeners) {
    //        screenHandlerListener.onPropertyUpdate(this, index, value);
    //    }
    //}

    public int getBattery() {
        return this.battery;
    }
}
