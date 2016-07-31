package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.enums.TropicTreeType;
import com.github.alexthe666.archipelago.world.tree.WorldGenCalophyllum;
import com.github.alexthe666.archipelago.world.tree.WorldGenCanaryIslandDatePalm;
import com.github.alexthe666.archipelago.world.tree.WorldGenCanaryMadrone;
import com.github.alexthe666.archipelago.world.tree.WorldGenCoconutPalm;
import com.github.alexthe666.archipelago.world.tree.WorldGenHispaniolan;
import com.github.alexthe666.archipelago.world.tree.WorldGenMangrove;
import com.github.alexthe666.archipelago.world.tree.WorldGenTambalocoque;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class BlockArchipelagoSapling extends BlockBush implements IGrowable {
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    private TropicTreeType treeType;

    public BlockArchipelagoSapling(TropicTreeType tree) {
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
        this.setCreativeTab(Archipelago.tab);
        this.setUnlocalizedName("archipelago." + tree.name().toLowerCase() + "_sapling");
        this.setCreativeTab(Archipelago.tab);
        GameRegistry.registerBlock(this, tree.name().toLowerCase() + "_sapling");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), tree.name().toLowerCase() + "_sapling");
        this.treeType = tree;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rand);
            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(world, pos, state, rand);
            }
        }
    }

    public void grow(World world, BlockPos pos, IBlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(world, pos, rand);
        }
    }

    public void generateTree(World world, BlockPos pos, Random rand) {
        WorldGenerator gen = new WorldGenTrees(true);

        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, pos))
            return;
        switch (this.treeType) {
            case CANARY_ISLAND_DATE_PALM:
                gen = new WorldGenCanaryIslandDatePalm();
                break;
            case COCONUT_PALM:
                gen = new WorldGenCoconutPalm();
                break;
            case CALOPHYLLUM:
                gen = new WorldGenCalophyllum();
                break;
            case CANARY_MADRONE:
                gen = new WorldGenCanaryMadrone();
                break;
            case HISPANIOLAN_PINE:
                gen = new WorldGenHispaniolan();
                break;
            case TAMBALACOQUE:
                gen = new WorldGenTambalocoque();
                break;
            case MANGROVE:
                gen = new WorldGenMangrove();
                break;
            case CORRIOSA:
                gen = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), TropicTreeType.CORRIOSA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
                break;
            case GALAPAGOS_MICONIA:
                gen = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), TropicTreeType.GALAPAGOS_MICONIA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
                break;
            case TABERNAEMONTANA_CERIFERA:
                gen = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), TropicTreeType.TABERNAEMONTANA_CERIFERA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
                break;
            default:
                break;
        }
        gen.generate(world, rand, gen instanceof WorldGenShrub ? pos.down() : pos);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return (double) world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        this.grow(world, pos, state, rand);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(STAGE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }
}