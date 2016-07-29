package com.github.alexthe666.archipelago.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class EntityArchipelagoAnimal extends EntityTameable implements IAnimatedEntity {
    @SideOnly(Side.CLIENT)
    public ChainBuffer tail_buffer;
    private int animationTick;
    private Animation currentAnimation;
    protected int adultAge;
    protected float minimumSize;
    protected float maximumSize;
    protected double minimumDamage;
    protected double maximumDamage;
    protected double minimumHealth;
    protected double maximumHealth;
    protected double minimumSpeed;
    protected double maximumSpeed;
    private static final DataParameter<Integer> AGE_TICKS = EntityDataManager.<Integer>createKey(EntityArchipelagoAnimal.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityArchipelagoAnimal.class, DataSerializers.VARINT);

    public EntityArchipelagoAnimal(World world, int adultAge, float minimumSize, float maximumSize, double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed) {
        super(world);
        this.adultAge = adultAge;
        this.minimumSize = minimumSize;
        this.maximumSize = maximumSize;
        this.minimumDamage = minimumDamage;
        this.maximumDamage = maximumDamage;
        this.minimumHealth = minimumHealth;
        this.maximumHealth = maximumHealth;
        this.minimumSpeed = minimumSpeed;
        this.maximumSpeed = maximumSpeed;
        if (FMLCommonHandler.instance().getSide().isClient()) {
            tail_buffer = new ChainBuffer();
        }
        this.updateAttributes();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(AGE_TICKS, 0);
        this.dataManager.register(VARIANT, 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("AgeTicks", this.getAgeInTicks());
        compound.setInteger("Variant", this.getVariant());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAgeInTicks(compound.getInteger("AgeTicks"));
        this.setVariant(compound.getInteger("Variant"));
    }

    public int getAgeInTicks() {
        return this.dataManager.get(AGE_TICKS);
    }

    public void setAgeInTicks(int age) {
        this.dataManager.set(AGE_TICKS, age);
    }

    public int getAgeInDays() {
        return this.dataManager.get(AGE_TICKS) / 24000;
    }

    public void setAgeInDays(int age) {
        this.dataManager.set(AGE_TICKS, age * 24000);
    }

    public int getVariant() {
        return this.dataManager.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.dataManager.set(VARIANT, variant);
    }

    public void onUpdtate(){
        if(worldObj.isRemote){
            calculateBuffer();
        }
        AnimationHandler.INSTANCE.updateAnimations(this);
        this.setAgeInTicks(this.getAgeInTicks() + 1);
        if (this.getAgeInTicks() % 24000 == 0) {
            this.updateAttributes();
            this.setScale(this.getRenderSize());
        }
    }

    @Override
    public void setScaleForAge(boolean par1) {
        this.setScale(Math.min(this.getRenderSize(), maximumSize));
    }

    public boolean isAdult() {
        return this.getAgeInTicks() >= adultAge * 24000;
    }

    public boolean isChild() {
        return this.getAgeInTicks() < adultAge * 24000;
    }

    public float getRenderSize() {
        float step = (this.maximumSize - this.minimumSize) / ((adultAge * 24000));

        if (this.getAgeInTicks() > adultAge * 24000) {
            return this.minimumSize + ((step) * adultAge * 24000);
        }
        return this.minimumSize + ((step * this.getAgeInTicks()));
    }

    protected boolean canDropLoot() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void calculateBuffer(){
        tail_buffer.calculateChainSwingBuffer(50, 10, 4, this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);

    }

    public boolean attackEntityAsMob(Entity entityIn) {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() );
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    private void updateAttributes() {
        double healthStep = (maximumHealth - minimumHealth) / (adultAge);
        double attackStep = (maximumDamage - minimumDamage) / (adultAge);
        double speedStep = (maximumSpeed - minimumSpeed) / (adultAge);
        if (!this.isAdult()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.round(minimumHealth + (healthStep * this.getAgeInDays())));
            this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Math.round(minimumDamage + (attackStep * this.getAgeInDays())));
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(minimumSpeed + (speedStep * this.getAgeInDays()));

        }
    }

    public void grow(int ageInDays) {
        this.setAgeInDays(this.getAgeInDays() + ageInDays);
        this.setScaleForAge(false);
        this.updateAttributes();
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int age = new Random().nextInt(this.adultAge);
        this.grow(age);
        this.onSpawn();
        return livingdata;
    }

    public void onSpawn(){

    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return currentAnimation;
    }

    @Override
    public void setAnimation(Animation animation) {
        currentAnimation = animation;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{};
    }

    public abstract String getTexture();
}
