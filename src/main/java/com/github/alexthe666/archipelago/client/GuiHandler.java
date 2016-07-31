package com.github.alexthe666.archipelago.client;

import com.github.alexthe666.archipelago.block.entity.TileEntityCampfire;
import com.github.alexthe666.archipelago.client.gui.GuiCampfire;
import com.github.alexthe666.archipelago.inventory.ContainerCampfire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileentity = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0:
                return new ContainerCampfire(player.inventory, (TileEntityCampfire) tileentity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileentity = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0:
                return new GuiCampfire(player.inventory, (TileEntityCampfire) tileentity);
        }
        return null;
    }
}
