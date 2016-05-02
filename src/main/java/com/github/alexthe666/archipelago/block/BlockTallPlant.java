package com.github.alexthe666.archipelago.block;

import java.util.List;
import java.util.Random;

import com.github.alexthe666.archipelago.Archipelago;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTallPlant extends BlockBush implements IGrowable
{
    public static final PropertyEnum<BlockTallPlant.EnumBlockHalf> HALF = PropertyEnum.<BlockTallPlant.EnumBlockHalf>create("half", BlockTallPlant.EnumBlockHalf.class);

    public BlockTallPlant(String name){
        super(Material.vine);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.LOWER));
        this.setHardness(0.0F);
        this.setStepSound(SoundType.PLANT);
        this.setUnlocalizedName("archipelago.plant." + name);
		this.setCreativeTab(Archipelago.tab);
		GameRegistry.registerBlock(this, name);
		Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), name);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state){
        if (state.getBlock() == this && !this.canBlockStay(worldIn, pos, state))
        {
            boolean flag = state.getValue(HALF) == BlockTallPlant.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = (Block)(flag ? this : worldIn.getBlockState(blockpos).getBlock());
            Block block1 = (Block)(flag ? worldIn.getBlockState(blockpos1).getBlock() : this);

            if (!flag) this.dropBlockAsItem(worldIn, pos, state, 0);

            if (block == this)
            {
                worldIn.setBlockState(blockpos, Blocks.air.getDefaultState(), 2);
            }

            if (block1 == this)
            {
                worldIn.setBlockState(blockpos1, Blocks.air.getDefaultState(), 3);
            }
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state){
        if (state.getValue(HALF) == BlockTallPlant.EnumBlockHalf.UPPER)
        {
            worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.LOWER), 2);
            return worldIn.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
        	
            IBlockState iblockstate = worldIn.getBlockState(pos.up());
            return iblockstate.getBlock() == this && super.canBlockStay(worldIn, pos, iblockstate);
        }
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.LOWER), 2);
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.UPPER), 2);
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player){
        if (state.getValue(HALF) == BlockTallPlant.EnumBlockHalf.UPPER)
        {
            if (worldIn.getBlockState(pos.down()).getBlock() == this)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    IBlockState iblockstate = worldIn.getBlockState(pos.down());
                        worldIn.destroyBlock(pos.down(), true);
                }
                else
                {
                    worldIn.setBlockToAir(pos.down());
                }
            }
        }
        else if (worldIn.getBlockState(pos.up()).getBlock() == this)
        {
            worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState(), 2);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient){
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state){
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state){
        spawnAsEntity(worldIn, pos, new ItemStack(this));
    }

    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(HALF, BlockTallPlant.EnumBlockHalf.UPPER);
    }

    public int getMetaFromState(IBlockState state){
        return state.getValue(HALF) == BlockTallPlant.EnumBlockHalf.UPPER ? 1 : 0;
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {HALF});
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
        if (state.getBlock() ==  this && state.getValue(HALF) == EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockToAir(pos.up());
        return world.setBlockToAir(pos);
    }

    public static enum EnumBlockHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }
}