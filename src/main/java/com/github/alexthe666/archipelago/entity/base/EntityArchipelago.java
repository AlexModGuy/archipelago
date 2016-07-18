package com.github.alexthe666.archipelago.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityArchipelago extends EntityTameable implements IAnimatedEntity {
    @SideOnly(Side.CLIENT)
    public ChainBuffer tail_buffer;
    private int animationTick;
    private Animation currentAnimation;

    public EntityArchipelago(World world, float minimumSize, float maximumSize, double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed) {
        super(world);
        if (FMLCommonHandler.instance().getSide().isClient()) {
            tail_buffer = new ChainBuffer();
        }
    }

    public void onUpdtate(){
        if(worldObj.isRemote){
            calculateBuffer();
        }
        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    @SideOnly(Side.CLIENT)
    public void calculateBuffer(){
        tail_buffer.calculateChainSwingBuffer(50, 10, 4, this);
    }

    @Override
    public boolean isAIDisabled() {
        return false;
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
}
