package com.github.alexthe666.archipelago.entity.base;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityAquaticAnimal extends EntityArchipelagoAnimal {

    public BlockPos currentTarget;
    protected boolean suffocates;

    public EntityAquaticAnimal(World world, int adultAge, float minimumSize, float maximumSize, double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed) {
        super(world, adultAge, minimumSize, maximumSize, minimumDamage, maximumDamage, minimumHealth, maximumHealth, minimumSpeed, maximumSpeed);
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    public abstract boolean isFreeSwimmer();

    public abstract double swimSpeed();

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
                this.setAir(300);
            }
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.renderYawOffset = this.rotationYaw;
        this.swimAround();
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec1, new Vec3d(vec2.xCoord, vec2.yCoord + (double) this.height * 0.5D, vec2.zCoord), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    private void swimAround() {

        if (this.currentTarget != null) {
            if (!this.isDirectPathBetweenPoints(this.getPositionVector(), new Vec3d(this.currentTarget.getX(), this.currentTarget.getY(), this.currentTarget.getZ()))) {
                this.currentTarget = null;
            }
            if (!this.isTargetInWater() || this.getDistance(this.currentTarget.getX(), this.currentTarget.getY(), this.currentTarget.getZ()) < 1.78F) {
                this.currentTarget = null;
            }
            this.swimTowardsTarget();
        }
    }

    @Override
    public boolean isInWater() {
        return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
    }

    protected boolean isTargetInWater() {
        return this.currentTarget != null && (this.worldObj.getBlockState(this.currentTarget).getMaterial() == Material.WATER && this.worldObj.getBlockState(this.currentTarget.up()).getMaterial() == Material.WATER);
    }

    public void swimTowardsTarget() {
        if (this.currentTarget != null && this.isTargetInWater() && this.inWater) {
            double targetX = this.currentTarget.getX() + 0.5D - this.posX;
            double targetY = this.currentTarget.getY() + 1D - this.posY;
            double targetZ = this.currentTarget.getZ() + 0.5D - this.posZ;
            this.motionX += (Math.signum(targetX) * 0.5D - this.motionX) * this.swimSpeed();
            if (this.isFreeSwimmer()) {
                this.motionY += (Math.signum(targetY) * 0.5D - this.motionY) * this.swimSpeed();
            }
            this.motionZ += (Math.signum(targetZ) * 0.5D - this.motionZ) * this.swimSpeed();
            float angle = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
            float rotation = MathHelper.wrapDegrees(angle - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += rotation;
        }
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    public void moveEntityWithHeading(float x, float z) {
        double d0;
        float f6;

        if (this.isServerWorld()) {
            float f4;
            float f5;

            if (this.isInWater()) {
                d0 = this.posY;
                f4 = 0.8F;
                f5 = 0.02F;
                f6 = (float) EnchantmentHelper.getDepthStriderModifier(this);

                if (f6 > 3.0F) {
                    f6 = 3.0F;
                }

                if (!this.onGround) {
                    f6 *= 0.5F;
                }

                if (f6 > 0.0F) {
                    f4 += (0.54600006F - f4) * f6 / 3.0F;
                    f5 += (this.getAIMoveSpeed() * 1.0F - f5) * f6 / 3.0F;
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
                    this.moveEntity(x, z, f4);
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
                    super.moveEntityWithHeading(x, z);
                }
            }
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        d0 = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }
}
