package com.github.alexthe666.archipelago.block.entity;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class TileEntityCampfire extends TileEntity implements ITickable, ISidedInventory {
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {1};
    private ItemStack[] stacks = new ItemStack[2];
    private int burnTime;
    private int cookTime;
    private int totalCookTime;

    public int getSizeInventory() {
        return this.stacks.length;
    }

    @Nullable
    public ItemStack getStackInSlot(int index) {
        return this.stacks[index];
    }

    @Nullable
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.stacks, index, count);
    }

    @Nullable
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        boolean flag = stack != null && stack.isItemEqual(this.stacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.stacks[index]);
        this.stacks[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag) {
            this.totalCookTime = 200;
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    private boolean canSmelt() {
        if (this.stacks[0] == null) {
            return false;
        }
        else {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.stacks[0]);
            if (itemstack == null) return false;
            if (itemstack.getItem() == null) return false;
            if (!(itemstack.getItem() instanceof ItemFood)) return false;
            if (this.stacks[2] == null) return true;
            if (!this.stacks[2].isItemEqual(itemstack)) return false;
            int result = stacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.stacks[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.stacks[0]);
            if (this.stacks[2] == null) {
                this.stacks[2] = itemstack.copy();
            }
            else if (this.stacks[2].getItem() == itemstack.getItem()) {
                this.stacks[2].stackSize += itemstack.stackSize;
            }
            --this.stacks[0].stackSize;
            if (this.stacks[0].stackSize <= 0) {
                this.stacks[0] = null;
            }
        }
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index != 1) {
            return true;
        }
        return false;
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : SLOTS_TOP;
    }

    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    public String getGuiID() {
        return "archipelago:campfire";
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.cookTime;
            case 2:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.cookTime = value;
                break;
            case 2:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 3;
    }

    public void clear() {
        for (int i = 0; i < this.stacks.length; ++i) {
            this.stacks[i] = null;
        }
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        boolean flag = this.canSmelt();
        boolean flag1 = false;
        if (this.canSmelt()) {
            --this.burnTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.stacks[0] != null) {
                if (this.canSmelt()) {
                    this.burnTime = 200;
                    flag1 = true;
                }

                if (this.canSmelt()) {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = 200;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else {
                    this.cookTime = 0;
                }
            }
            else if (this.cookTime > 0) {
                this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.canSmelt()) {
                flag1 = true;
                BlockFurnace.setState(this.canSmelt(), this.worldObj, this.pos);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    @Override
    public String getName() {
        return "archipelago.campfire";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
