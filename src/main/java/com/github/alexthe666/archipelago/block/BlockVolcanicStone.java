package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockVolcanicStone extends Block{

    public BlockVolcanicStone(){
        super(Material.ROCK);
        this.setCreativeTab(Archipelago.tab);
        this.setHardness(2.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName("archipelago.volcanic_stone");
        GameRegistry.registerBlock(this, "volcanic_stone");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), "volcanic_stone");
    }
}
