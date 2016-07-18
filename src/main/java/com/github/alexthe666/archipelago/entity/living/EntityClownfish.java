package com.github.alexthe666.archipelago.entity.living;

import com.github.alexthe666.archipelago.entity.base.EntityAquaticAnimal;
import com.github.alexthe666.archipelago.entity.living.ai.ArchipelagoAIFindWaterTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.world.World;

import java.util.Random;

public class EntityClownfish extends EntityAquaticAnimal {

    public EntityClownfish(World world) {
        super(world, 3, 0.3F, 0.6F, 1, 1, 2, 4, 0, 0);
        this.setSize(0.7F, 0.5F);
        this.suffocates = true;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAILookIdle(this));
        this.tasks.addTask(1, new ArchipelagoAIFindWaterTarget(this));
    }

    @Override
    public boolean isFreeSwimmer() {
        return true;
    }

    @Override
    public double swimSpeed() {
        return 0.025;
    }

    public void onSpawn(){
        this.setVariant(new Random().nextInt(3));
    }

    @Override
    public String getTexture() {
        return "archipelago:textures/models/clownfish/clownfish_" + this.getVariant();
    }
}
