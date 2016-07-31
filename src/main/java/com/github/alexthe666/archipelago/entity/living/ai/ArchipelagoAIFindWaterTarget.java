package com.github.alexthe666.archipelago.entity.living.ai;

import com.github.alexthe666.archipelago.entity.base.EntityAquaticAnimal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArchipelagoAIFindWaterTarget extends EntityAIBase {
    private EntityAquaticAnimal mob;
    private Vec3d target;
    private World theWorld;

    public ArchipelagoAIFindWaterTarget(EntityAquaticAnimal mob) {
        this.mob = mob;
        this.theWorld = mob.worldObj;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (!this.mob.isInWater()) {
            return false;
        }
        if (this.mob.currentTarget != null && !this.mob.isDirectPathBetweenPoints(this.mob.getPositionVector(), new Vec3d(this.mob.currentTarget))) {
            this.mob.currentTarget = null;
        }
        if (this.mob.currentTarget != null && this.mob.getDistance(this.mob.currentTarget.getX(), this.mob.currentTarget.getY(), this.mob.currentTarget.getZ()) < 10F) {
            return false;
        } else {
            Vec3d vec3 = this.findWaterTarget();

            if (vec3 == null) {
                return false;
            } else {
                this.mob.currentTarget = new BlockPos(vec3);
                return true;
            }
        }
    }

    @Override
    public boolean continueExecuting() {
        return this.mob.currentTarget != null;
    }

    public Vec3d findWaterTarget() {
        if (this.mob.getAttackTarget() == null) {
            List<Vec3d> water = new ArrayList<>();
            for (int x = (int) this.mob.posX - 5; x < (int) this.mob.posX + 5; x++) {
                for (int y = this.mob.isFreeSwimmer() ? (int) this.mob.posY - 5 : (int) this.mob.posY; y < (this.mob.isFreeSwimmer() ? (int) this.mob.posY + 5 : (int) this.mob.posY); y++) {
                    for (int z = (int) this.mob.posZ - 5; z < (int) this.mob.posZ + 5; z++) {
                        if (this.mob.isDirectPathBetweenPoints(this.mob.getPositionVector(), new Vec3d(x, y, z))) {
                            water.add(new Vec3d(x, y, z));
                        }
                    }
                }
            }
            if (!water.isEmpty()) {
                return water.get(this.mob.getRNG().nextInt(water.size()));
            }
        } else {
            Random random = this.mob.getRNG();
            BlockPos blockpos1;
            if (this.mob.isFreeSwimmer()) {
                blockpos1 = new BlockPos(this.mob.getAttackTarget());
            } else {
                blockpos1 = new BlockPos(this.mob.getAttackTarget().posX, this.mob.getAttackTarget().posY, this.mob.getAttackTarget().posZ);
            }
            if (this.mob.worldObj.getBlockState(blockpos1).getMaterial() == Material.WATER) {
                return new Vec3d((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
            }
        }
        return null;
    }
}