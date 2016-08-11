package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class BlockCoralRock extends Block {
    public BlockCoralRock() {
        super(Material.SAND);
        this.setHardness(2.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName("archipelago.coralrock");
        this.setCreativeTab(Archipelago.tab);
        GameRegistry.registerBlock(this, "coralrock");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), "coralrock");
    }
}
