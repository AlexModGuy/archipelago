package com.github.alexthe666.archipelago.client;

import com.github.alexthe666.archipelago.block.entity.TileEntityCampfire;
import com.github.alexthe666.archipelago.inventory.ContainerCampfire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.github.backtolifemod.backtolife.client.gui.GuiFertilizationMachine;
import com.github.backtolifemod.backtolife.client.gui.GuiFossilSlicer;
import com.github.backtolifemod.backtolife.client.gui.GuiTissueAnalyzer;
import com.github.backtolifemod.backtolife.client.inventory.ContainerFertilizationMachine;
import com.github.backtolifemod.backtolife.client.inventory.ContainerFossilSlicer;
import com.github.backtolifemod.backtolife.client.inventory.ContainerTissueAnalyzer;
import com.github.backtolifemod.backtolife.entity.tile.TileEntityFertilizationMachine;
import com.github.backtolifemod.backtolife.entity.tile.TileEntityFossilSlicer;
import com.github.backtolifemod.backtolife.entity.tile.TileEntityTissueAnalyzer;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileentity = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0 :
                return new ContainerCampfire(player.inventory, (TileEntityCampfire) tileentity);
        }
        return null;

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileentity = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0 :
                return new GuiCampfire(player.inventory, (TileEntityCampfire) tileentity);
        }
        return null;
    }

}
