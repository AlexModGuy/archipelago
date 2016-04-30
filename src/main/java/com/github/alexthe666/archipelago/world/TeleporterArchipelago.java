package com.github.alexthe666.archipelago.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw){
    	placeInPortal(entityIn, entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.rotationYaw);
		return false;
    }

	public void placeInPortal(Entity entity, double x, double y, double z, float yaw)
	{
		BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z)));
		entity.setLocationAndAngles(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, yaw, 0);
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
	}

}
