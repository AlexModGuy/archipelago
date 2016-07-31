package com.github.alexthe666.archipelago.block.entity;

import com.github.alexthe666.archipelago.block.BlockCampfire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class TileEntityCampfire extends TileEntity implements ITickable, ISidedInventory {
    private static final int[] SLOTS_TOP = new int[] { 0 };
    private static final int[] SLOTS_BOTTOM = new int[] { 1 };
    private ItemStack[] stacks = new ItemStack[3];
    private int burnTime;
    private int cookTime;

    @Override
    public int getSizeInventory() {
        return this.stacks.length;
    }

    @Override
    @Nullable
    public ItemStack getStackInSlot(int index) {
        return this.stacks[index];
    }

    @Override
    @Nullable
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.stacks, index, count);
    }

    @Override
    @Nullable
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        boolean flag = stack != null && stack.isItemEqual(this.stacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.stacks[index]);
        this.stacks[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        if (index == 0 && !flag) {
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    private boolean canCook() {
        if (this.stacks[0] == null) {
            return false;
        } else {
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(this.stacks[0]);
            if (result == null) return false;
            if (result.getItem() == null) return false;
            if (!(result.getItem() instanceof ItemFood)) return false;
            if (this.stacks[1] == null) return true;
            if (!this.stacks[1].isItemEqual(result)) return false;
            int resultSize = this.stacks[1].stackSize + result.stackSize;
            return resultSize <= this.getInventoryStackLimit() && resultSize <= this.stacks[1].getMaxStackSize();
        }
    }

    public void cookItem() {
        if (this.canCook()) {
            ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(this.stacks[0]);
            if (this.stacks[1] == null) {
                this.stacks[1] = stack.copy();
            } else if (this.stacks[1].getItem() == stack.getItem()) {
                this.stacks[1].stackSize += stack.stackSize;
            }
            --this.stacks[0].stackSize;
            if (this.stacks[0].stackSize <= 0) {
                this.stacks[0] = null;
            }
            this.cookTime = 0;
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index != 1;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : SLOTS_TOP;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return this.burnTime;
            case 1:
                return this.cookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.cookTime = value;
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 2;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.stacks.length; ++i) {
            this.stacks[i] = null;
        }
    }

    private net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    private net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    private net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) this.handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) this.handlerTop;
            else
                return (T) this.handlerSide;
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        boolean dirty = false;
        if (!this.worldObj.isRemote) {
            if (this.burnTime > 0) {
                --this.burnTime;
                if (this.stacks[0] != null) {
                    if (this.canCook()) {
                        if (++this.cookTime >= 200) {
                            this.cookTime = 0;
                            this.cookItem();
                            dirty = true;
                        }
                    } else {
                        this.cookTime = 0;
                    }
                } else if (this.cookTime > 0) {
                    this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, 200);
                }
            } else if (this.burnTime != -1) {
                BlockCampfire.setState(false, this.worldObj, this.pos);
                this.burnTime = -1;
            }
        }

        if (dirty) {
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

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList itemList = compound.getTagList("Items", 10);
        ItemStack[] stacks = new ItemStack[this.stacks.length];

        for (int i = 0; i < itemList.tagCount(); ++i) {
            NBTTagCompound item = itemList.getCompoundTagAt(i);

            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < stacks.length) {
                stacks[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        this.cookTime = compound.getShort("CookTime");
        this.burnTime = compound.getShort("BurnTime");

        this.stacks = stacks;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);

        compound.setShort("CookTime", (short) this.cookTime);
        compound.setShort("BurnTime", (short) this.burnTime);

        ItemStack[] slots = this.stacks;

        NBTTagList itemList = new NBTTagList();

        for (int slot = 0; slot < this.getSizeInventory(); ++slot) {
            if (slots[slot] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) slot);

                slots[slot].writeToNBT(itemTag);
                itemList.appendTag(itemTag);
            }
        }

        compound.setTag("Items", itemList);

        return compound;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }
}
