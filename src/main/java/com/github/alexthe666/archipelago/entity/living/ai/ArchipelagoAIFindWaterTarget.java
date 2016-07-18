package com.github.alexthe666.archipelago.entity.living.ai;

import java.util.Random;

import com.github.alexthe666.archipelago.entity.base.EntityAquaticAnimal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ArchipelagoAIFindWaterTarget extends EntityAIBase {
    private EntityAquaticAnimal mob;
    private Vec3d target;
    private World theWorld;

    public ArchipelagoAIFindWaterTarget(EntityAquaticAnimal mob) {
        this.mob = mob;
        this.theWorld = mob.worldObj;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (mob.currentTarget != null && !mob.isDirectPathBetweenPoints(mob.getPositionVector(), new Vec3d(mob.currentTarget))) {
            mob.currentTarget = null;
        }
        if (mob.currentTarget != null && mob.getDistance(mob.currentTarget.getX(), mob.currentTarget.getY(), mob.currentTarget.getZ()) < 10F) {
            return false;
        } else {
            Vec3d vec3 = this.findWaterTarget();

            if (vec3 == null) {
                return false;
            } else {
                mob.currentTarget = new BlockPos(vec3);
                return true;
            }
        }
    }

    public boolean continueExecuting() {
        return mob.currentTarget != null;
    }

    public Vec3d findWaterTarget() {
        if (mob.getAttackTarget() == null) {
            Random random = this.mob.getRNG();
            mob.setAttackTarget(null);
            BlockPos blockpos = new BlockPos(this.mob.posX, this.mob.getEntityBoundingBox().minY, this.mob.posZ);

            for (int i = 0; i < 10; ++i) {
                BlockPos blockpos1;
                if (mob.isFreeSwimmer()) {
                    blockpos1 = blockpos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
                } else {
                    blockpos1 = blockpos.add(random.nextInt(20) - 10, 0, random.nextInt(20) - 10);
                }
                if (mob.worldObj.getBlockState(blockpos1).getMaterial() == Material.WATER) {
                    return new Vec3d((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
                }
            }
        } else {
            Random random = this.mob.getRNG();
            BlockPos blockpos1;
            if (mob.isFreeSwimmer()) {
                blockpos1 = new BlockPos(mob.getAttackTarget());
            } else {
                blockpos1 = new BlockPos(mob.getAttackTarget().posX, mob.getAttackTarget().posY, mob.getAttackTarget().posZ);
            }
            if (mob.worldObj.getBlockState(blockpos1).getMaterial() == Material.WATER) {
                return new Vec3d((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
            }
        }
        return null;
    }



}