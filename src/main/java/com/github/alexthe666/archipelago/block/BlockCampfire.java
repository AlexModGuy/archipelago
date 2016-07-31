package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.block.entity.TileEntityCampfire;
import com.github.alexthe666.archipelago.core.ModBlocks;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCampfire extends BlockContainer {

    public boolean isActive;
    private static boolean keepInventory;

    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.25D, 1D);

    public BlockCampfire(boolean isActive) {
        super(Material.PLANTS);
        this.isActive = isActive;
        this.setLightLevel(isActive ? 0.7F : 0);
        this.setHardness(2.0F);
        this.setHarvestLevel("axe", 0);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName("archipelago.campfire");
        this.setCreativeTab(!isActive ? Archipelago.tab : null);
        GameRegistry.registerBlock(this, isActive ? "campfire_on" : "campfire_off");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), isActive ? "campfire_on" : "campfire_off");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!this.isActive && heldItem != null && heldItem.getItem() != null && heldItem.getItem() == Items.FLINT) {
            BlockCampfire.setState(true, world, pos);
            world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F);
        } else if (this.isActive) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityCampfire) {
                player.openGui(Archipelago.INSTANCE, 0, world, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public static void setState(boolean active, World world, BlockPos pos) {
        if (!world.isRemote) {
            world.setBlockState(pos, (active ? ModBlocks.campfire_on : ModBlocks.campfire_off).getDefaultState());
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null) {
                if (tile instanceof TileEntityCampfire) {
                    ((TileEntityCampfire) tile).setField(0, active ? 1000 : 0);
                }
                tile.validate();
                world.setTileEntity(pos, tile);
            }
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityCampfire) {
                InventoryHelper.dropInventoryItems(world, pos, (TileEntityCampfire) tile);
                world.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.campfire_off);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.campfire_off);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (this.isActive) {
            if (rand.nextInt(24) == 0) {
                worldIn.playSound((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 0.6F + rand.nextFloat(), rand.nextFloat() * 1.3F + 0.3F, false);
            }
            for (int i = 0; i < 3; ++i) {
                double d0 = (double) pos.getX() + 0.25D + (rand.nextDouble() * 0.5D);
                double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.25D;
                double d2 = (double) pos.getZ() + 0.25D + (rand.nextDouble() * 0.5D);
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCampfire();
    }
}
