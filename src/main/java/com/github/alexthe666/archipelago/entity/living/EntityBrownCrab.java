package com.github.alexthe666.archipelago.entity.living;

import com.github.alexthe666.archipelago.entity.base.EntityAquaticAnimal;
import com.github.alexthe666.archipelago.entity.living.ai.ArchipelagoAIFindWaterTarget;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

public class EntityBrownCrab extends EntityAquaticAnimal {

    public EntityBrownCrab(World world) {
        super(world, 4, 0.3F, 1, 1, 1, 4, 16, 0.1, 0.1);
        this.setSize(0.8F, 0.5F);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new ArchipelagoAIFindWaterTarget(this));
        this.tasks.addTask(0, new EntityAIWander(this, 1D));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1D, false));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public boolean isFreeSwimmer() {
        return false;
    }

    @Override
    public double swimSpeed() {
        return 0.015;
    }

    @Override
    public void onSpawn() {
        this.setVariant(new Random().nextInt(2));
    }

    @Override
    public String getTexture() {
        return "archipelago:textures/models/brown_crab/brown_crab_" + this.getVariant();
    }
}
