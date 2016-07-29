package com.github.alexthe666.archipelago.entity.living.ai;

import java.util.ArrayList;
import java.util.List;
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
        if(!mob.isInWater()){
            return false;
        }
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
                    List<Vec3d> water = new ArrayList<Vec3d>();
                    for(int x = (int)mob.posX - 5; x < (int)mob.posX + 5; x++){
                        for(int y = mob.isFreeSwimmer() ? (int)mob.posY - 5 : (int)mob.posY; y < (mob.isFreeSwimmer() ? (int)mob.posY + 5 : (int)mob.posY); y++){
                            for(int z = (int)mob.posZ - 5; z < (int)mob.posZ + 5; z++){
                                if(mob.isDirectPathBetweenPoints(mob.getPositionVector(), new Vec3d(x, y, z))) {
                                    water.add(new Vec3d(x, y, z));
                                }
                            }
                        }
                    }
            if(!water.isEmpty()){
                return water.get(mob.getRNG().nextInt(water.size()));
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