package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.client.render.world.ArchipelagoCloudRenderer;
import com.github.alexthe666.archipelago.client.render.world.ArchipelagoSkyRenderer;
import com.github.alexthe666.archipelago.core.ModConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderArchipelago extends WorldProvider {
    @SideOnly(Side.CLIENT)
    IRenderHandler newSkyRenderer = new ArchipelagoSkyRenderer();
    @SideOnly(Side.CLIENT)
    IRenderHandler newCloudRenderer = new ArchipelagoCloudRenderer();

    @Override
    public void createBiomeProvider() {
        this.biomeProvider = new ArchipelagoBiomeProvider(this.worldObj.getSeed(), this.worldObj.getWorldType());
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering the Archipelago";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorArchipelago(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return ModConfig.ARCHIPELAGO_DIMENSION_ID;
    }

    @Override
    public double getMovementFactor() {
        return 1.0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public net.minecraftforge.client.IRenderHandler getSkyRenderer() {
        return this.newSkyRenderer;
    }

    @Override
    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return false;
    }

    @Override
    public DimensionType getDimensionType() {
        return Archipelago.dimType;
    }
}
