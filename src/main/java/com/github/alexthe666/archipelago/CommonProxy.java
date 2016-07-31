package com.github.alexthe666.archipelago;

import com.github.alexthe666.archipelago.enums.TropicParticle;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class CommonProxy {

    public void init() {
    }

    public void addItemRender(Item item, String name) {
    }

    public void addItemRenderWithMeta(Item item, String name, int meta) {
    }

    public void spawnParticle(TropicParticle particle, World world, float x, float y, float z, double motionX, double motionY, double motionZ) {
    }

    public boolean areLeavesFancy() {
        return false;
    }

    public void renderFluids() {
    }
}
