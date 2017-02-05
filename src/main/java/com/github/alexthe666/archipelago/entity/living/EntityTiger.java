package com.github.alexthe666.archipelago.entity.living;

import com.github.alexthe666.archipelago.entity.base.EntityArchipelagoAnimal;
import com.github.alexthe666.archipelago.entity.living.ai.ArchipelagoAIFindWaterTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Codyr on 04/02/2017.
 */
public class EntityTiger extends EntityArchipelagoAnimal {
    public EntityTiger(World world) {
        super(world, 3, 1.1F, 1.4F, 1, 1, 2, 4, 0, 0);
        this.setSize(0.7F, 0.5F);
    }

    @Override
    protected void initEntityAI() {

        this.tasks.addTask(1, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    }


    @Override
    public void onSpawn() {
        this.setVariant(new Random().nextInt(2));
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {

    }

    @Override
    public String getTexture() {
        return "archipelago:textures/models/tiger/tiger_" + this.getVariant();
    }
}

