package com.github.alexthe666.archipelago.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ContainerCampfire extends Container {
    private final IInventory tile;
    private int cookTime;
    private int burnTime;

    public ContainerCampfire(InventoryPlayer playerInventory, IInventory tile) {
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 56, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, tile, 1, 116, 35));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tile);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        int tileCookTime = this.tile.getField(1);
        int tileBurnTime = this.tile.getField(0);

        for (IContainerListener listener : this.listeners) {
            if (this.cookTime != tileCookTime) {
                listener.sendProgressBarUpdate(this, 1, tileCookTime);
            }
            if (this.burnTime != tileBurnTime) {
                listener.sendProgressBarUpdate(this, 0, tileBurnTime);
            }
        }

        this.burnTime = tileBurnTime;
        this.cookTime = tileCookTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tile.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }

    @Override
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = null;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            stack = itemstack1.copy();
            if (index == 1) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, stack);
            } else if (index != 0) {
                if (FurnaceRecipes.instance().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (index >= 2 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 38, false)) {
                        return null;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == stack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return stack;
    }
}