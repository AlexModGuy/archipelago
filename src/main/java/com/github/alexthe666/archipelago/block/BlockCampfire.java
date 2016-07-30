package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCampfire extends Block {

    public boolean isActive;
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.25D, 1D);
    public BlockCampfire(boolean isActive) {
        super(Material.PLANTS);
        this.isActive = isActive;
        this.setLightLevel(isActive ? 0.7F : 0);
        this.setHardness(2.0F);
        this.setHarvestLevel("axe", 0);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName("archipelago.campfire");
        this.setCreativeTab(isActive ? Archipelago.tab : null);
        GameRegistry.registerBlock(this, isActive ? "campfire_on" : "campfire_off");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), isActive ? "campfire_on" : "campfire_off");
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if(!isActive && heldItem != null && heldItem.getItem() != null && heldItem.getItem() == Items.FLINT){
            worldIn.setBlockState(pos, ModBlocks.campfire_on.getDefaultState());
            worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F);
        }
        return true;
    }



    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return AABB;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if(this.isActive) {
            if (rand.nextInt(24) == 0) {
                worldIn.playSound((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 0.6F + rand.nextFloat(), rand.nextFloat() * 1.3F + 0.3F, false);
            }
            for (int i = 0; i < 3; ++i) {
                double d0 = (double) pos.getX() + 0.25D + (rand.nextDouble() * 0.5D);
                double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.25D;
                double d2 = (double) pos.getZ() + 0.25D + (rand.nextDouble() * 0.5D);
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }
}
