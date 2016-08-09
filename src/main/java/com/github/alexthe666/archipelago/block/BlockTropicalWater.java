package com.github.alexthe666.archipelago.block;

import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class BlockTropicalWater extends BlockFluidClassic {
    public BlockTropicalWater(Fluid fluid, Material material) {
        super(fluid, material);
        this.setLightOpacity(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState neighbor = blockAccess.getBlockState(pos.offset(side));
        return !(neighbor.getMaterial() == blockState.getMaterial() || (!neighbor.isOpaqueCube() && neighbor.getBlock() != Blocks.AIR)) && (side == EnumFacing.UP || neighbor.getBlock() instanceof BlockChest || super.shouldSideBeRendered(blockState, blockAccess, pos, side));
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if ((world.getBlockState(new BlockPos(entity).down()).getMaterial() == Material.WATER || world.getBlockState(new BlockPos(entity).down()).getMaterial() == Material.CORAL) && world.getBlockState(pos.down()).getMaterial() == Material.WATER && entity.getRidingEntity() == null) {
            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
                EntityLivingBase living = (EntityLivingBase) entity;
                try {
                    BlockShortCoral.SET_FLAG.invoke(living, 7, true);
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (!player.capabilities.isFlying) {
                    try {
                        BlockShortCoral.SET_FLAG.invoke(player, 7, true);
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    entity.motionX *= 1.02;
                    entity.motionZ *= 1.02;
                }
            }
        }
    }
}
