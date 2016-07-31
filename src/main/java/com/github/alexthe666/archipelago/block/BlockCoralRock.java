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

    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 9);

    public BlockCoralRock() {
        super(Material.SAND);
        this.setHardness(2.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
        this.setUnlocalizedName("archipelago.coralrock");
        this.setCreativeTab(Archipelago.tab);
        GameRegistry.registerBlock(this, "coralrock");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), "coralrock");
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(TYPE, new Random().nextInt(9)), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }
}
