package com.github.alexthe666.archipelago;

import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.github.alexthe666.archipelago.enums.EnumParticle;

public class CommonProxy {

    public void init() {
    }

    public void addItemRender(Item item, String name) {
    }

    public void addItemRenderWithMeta(Item item, String name, int meta) {
    }

    public void spawnParticle(EnumParticle particle, World world, float x, float y, float z, double motionX, double motionY, double motionZ) {
    }

    public boolean areLeavesFancy() {
        return false;
    }

    public void renderFluids() {
    }

}
