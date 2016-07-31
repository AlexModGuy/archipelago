package com.github.alexthe666.archipelago.block;

import java.util.List;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.item.block.ItemBlockBlackSandstone;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBlackSandstone extends Block
{
    public static final PropertyEnum<BlockBlackSandstone.EnumType> TYPE = PropertyEnum.<BlockBlackSandstone.EnumType>create("type", BlockBlackSandstone.EnumType.class);

    public BlockBlackSandstone() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockBlackSandstone.EnumType.DEFAULT));
        this.setCreativeTab(Archipelago.tab);
        this.setHardness(2.0F);
        this.setHarvestLevel("shovel", 0);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName("archipelago.black_sandstone");
        GameRegistry.registerBlock(this, ItemBlockBlackSandstone.class, "black_sandstone");

    }

    public int damageDropped(IBlockState state) {
        return ((BlockBlackSandstone.EnumType)state.getValue(TYPE)).getMetadata();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockBlackSandstone.EnumType blocksandstone$enumtype : BlockBlackSandstone.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blocksandstone$enumtype.getMetadata()));
        }
    }

    public MapColor getMapColor(IBlockState state) {
        return MapColor.BLACK;
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, BlockBlackSandstone.EnumType.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return ((BlockBlackSandstone.EnumType)state.getValue(TYPE)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {TYPE});
    }

    public static enum EnumType implements IStringSerializable
    {
        DEFAULT(0, "black_sandstone", "default"),
        CHISELED(1, "black_chiseled_sandstone", "chiseled"),
        SMOOTH(2, "black_smooth_sandstone", "smooth");
        private static final BlockBlackSandstone.EnumType[] META_LOOKUP = new BlockBlackSandstone.EnumType[values().length];
        private final int metadata;
        private final String name;
        private final String unlocalizedName;

        private EnumType(int meta, String name, String unlocalizedName) {
            this.metadata = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.metadata;
        }

        public String toString() {
            return this.name;
        }

        public static BlockBlackSandstone.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockBlackSandstone.EnumType blocksandstone$enumtype : values()) {
                META_LOOKUP[blocksandstone$enumtype.getMetadata()] = blocksandstone$enumtype;
            }
        }
    }
}