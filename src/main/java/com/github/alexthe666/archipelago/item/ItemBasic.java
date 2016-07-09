package com.github.alexthe666.archipelago.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.alexthe666.archipelago.Archipelago;

public class ItemBasic extends Item {

    public ItemBasic(String name) {
        this.setUnlocalizedName("archipelago." + name);
        Archipelago.PROXY.addItemRender(this, name);
        this.setCreativeTab(Archipelago.tab);
        GameRegistry.registerItem(this, name);
    }
}
