package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.item.block.ItemBlockBlackSandstone;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBlackSand extends BlockFalling {

    public BlockBlackSand(){
        super();
        this.setCreativeTab(Archipelago.tab);
        this.setHardness(2.0F);
        this.setHarvestLevel("shovel", 0);
        this.setSoundType(SoundType.SAND);
        this.setUnlocalizedName("archipelago.black_sand");
        GameRegistry.registerBlock(this, "black_sand");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), "black_sand");
    }

}
