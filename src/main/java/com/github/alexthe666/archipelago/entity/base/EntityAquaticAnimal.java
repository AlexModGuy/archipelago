package com.github.alexthe666.archipelago.entity.base;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityAquaticAnimal extends EntityArchipelagoAnimal {

    protected boolean suffocates;

    public EntityAquaticAnimal(World world, int adultAge, float minimumSize, float maximumSize, double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed) {
        super(world, adultAge, minimumSize, maximumSize, minimumDamage, maximumDamage, minimumHealth, maximumHealth, minimumSpeed, maximumSpeed);
        this.moveHelper = new EntityAquaticAnimal.SwimmingMoveHelper();
        this.navigator = new PathNavigateSwimmer(this, world);
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    public abstract boolean isFreeSwimmer();

    public abstract double swimSpeed();

    public abstract int getMaximumAir();

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void onEntityUpdate() {
        int i = this.getAir();
        super.onEntityUpdate();
        if (this.suffocates) {
            if (this.isEntityAlive() && !this.isInWater()) {
                --i;
                this.setAir(i);

                if (this.getAir() == -20) {
                    this.setAir(0);
                    this.attackEntityFrom(DamageSource.drown, 2.0F);
                }
            } else {
                this.setAir(this.getMaximumAir());
            }
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.renderYawOffset = this.rotationYaw;
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec1, new Vec3d(vec2.xCoord, vec2.yCoord + (double) this.height * 0.5D, vec2.zCoord), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public boolean isInWater() {
        return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    public void moveEntityWithHeading(float strafe, float forward) {
        if (this.isServerWorld()) {
            float f4;
            float f5;
            if (this.isInWater()) {
                this.moveRelative(strafe, forward, 0.1F);
                f4 = 0.8F;
                float d0 = (float) EnchantmentHelper.getDepthStriderModifier(this);
                if (d0 > 3.0F) {
                    d0 = 3.0F;
                }
                if (!this.onGround) {
                    d0 *= 0.5F;
                }
                if (d0 > 0.0F) {
                    f4 += (0.54600006F - f4) * d0 / 3.0F;
                }
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double) f4;
                if (this.isFreeSwimmer()) {
                    this.motionX *= 0.900000011920929D;
                    this.motionY *= 0.900000011920929D;
                    this.motionZ *= 0.900000011920929D;
                } else {
                    this.motionX *= 0.900000011920929D;
                    this.motionZ *= 0.900000011920929D;
                    this.motionY = -0.1D;
                }
                this.motionZ *= (double) f4;
            } else {
                if (this.suffocates) {
                    float f2 = 0.91F;
                    if (this.onGround && this.isInWater()) {
                        this.onGround = false;
                    }
                    if (this.onGround) {
                        f2 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
                    }
                    float f3 = 0.16277136F / (f2 * f2 * f2);
                    if (this.onGround) {
                        f4 = this.getAIMoveSpeed() * f3;
                    } else {
                        f4 = this.jumpMovementFactor;
                    }
                    this.moveEntity(strafe, forward, f4);
                    f2 = 0.91F;
                    if (this.onGround) {
                        f2 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
                    }
                    if (this.isOnLadder()) {
                        f5 = 0.15F;
                        this.motionX = MathHelper.clamp_double(this.motionX, (double) (-f5), (double) f5);
                        this.motionZ = MathHelper.clamp_double(this.motionZ, (double) (-f5), (double) f5);
                        this.fallDistance = 0.0F;
                        if (this.motionY < -0.15D) {
                            this.motionY = -0.15D;
                        }
                    }
                    this.moveEntity(this.motionX, this.motionY, this.motionZ);
                    if (this.isCollidedHorizontally && this.isOnLadder()) {
                        this.motionY = 0.2D;
                    }
                    if (this.worldObj.isRemote && (!this.worldObj.isBlockLoaded(new BlockPos((int) this.posX, 0, (int) this.posZ)) || !this.worldObj.getChunkFromBlockCoords(new BlockPos((int) this.posX, 0, (int) this.posZ)).isLoaded())) {
                        if (this.posY > 0.0D) {
                            this.motionY = -0.1D;
                        } else {
                            this.motionY = 0.0D;
                        }
                    } else {
                        this.motionY -= 0.08D;
                    }
                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= (double) f2;
                    this.motionZ *= (double) f2;
                } else {
                    super.moveEntityWithHeading(strafe, forward);
                }
            }
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double deltaX = this.posX - this.prevPosX;
        double deltaZ = this.posZ - this.prevPosZ;
        float delta = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ) * 4.0F;
        if (delta > 1.0F) {
            delta = 1.0F;
        }
        this.limbSwingAmount += (delta - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    class SwimmingMoveHelper extends EntityMoveHelper {
        private EntityAquaticAnimal swimmingEntity = EntityAquaticAnimal.this;

        public SwimmingMoveHelper() {
            super(EntityAquaticAnimal.this);
        }

        @Override
        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.swimmingEntity.getNavigator().noPath()) {
                double distanceX = this.posX - this.swimmingEntity.posX;
                double distanceY = this.posY - this.swimmingEntity.posY;
                double distanceZ = this.posZ - this.swimmingEntity.posZ;
                double distance = Math.abs(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
                distance = (double) MathHelper.sqrt_double(distance);
                distanceY /= distance;
                float angle = (float) (Math.atan2(distanceZ, distanceX) * 180.0D / Math.PI) - 90.0F;
                this.swimmingEntity.rotationYaw = this.limitAngle(this.swimmingEntity.rotationYaw, angle, 30.0F);
                this.swimmingEntity.setAIMoveSpeed((float) this.swimmingEntity.swimSpeed() * 7.0F);
                if (this.swimmingEntity.isFreeSwimmer()) {
                    this.swimmingEntity.motionY += (double) this.swimmingEntity.getAIMoveSpeed() * distanceY * 0.1D;
                } else if (distanceY + 1.0F > this.entity.stepHeight && distanceX * distanceX + distanceZ * distanceZ < Math.max(1.0F, this.entity.width)) {
                    this.entity.getJumpHelper().setJumping();
                }
            } else {
                this.swimmingEntity.setAIMoveSpeed(0.0F);
            }
        }
    }
}
