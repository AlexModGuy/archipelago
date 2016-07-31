package com.github.alexthe666.archipelago.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterArchipelago extends Teleporter {
    public World world;

    public TeleporterArchipelago(WorldServer world) {
        super(world);
        this.world = world;
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
        this.placeInPortal(entity, entity.posX, entity.posY, entity.posZ, entity.rotationYaw);
        return false;
    }

    public void placeInPortal(Entity entity, double x, double y, double z, float yaw) {
        BlockPos pos = this.world.getTopSolidOrLiquidBlock(new BlockPos(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z)));
        entity.setLocationAndAngles(0.5F, 90.5F, 0.5F, yaw, 0);
        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return false;
    }
}
