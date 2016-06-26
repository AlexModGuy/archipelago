package com.github.alexthe666.archipelago.item;

import com.github.alexthe666.archipelago.Archipelago;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBasic extends Item {

    public ItemBasic(String name) {
        this.setUnlocalizedName("archipelago." + name);
        Archipelago.PROXY.addItemRender(this, name);
        this.setCreativeTab(Archipelago.tab);
        GameRegistry.registerItem(this, name);
    }
}
