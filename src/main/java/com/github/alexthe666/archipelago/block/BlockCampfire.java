package com.github.alexthe666.archipelago.block;

import com.github.alexthe666.archipelago.Archipelago;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class BlockCampfire extends Block {

    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockCampfire() {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setHarvestLevel("axe", 0);
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false));
        this.setUnlocalizedName("archipelago.campfire");
        this.setCreativeTab(Archipelago.tab);
        GameRegistry.registerBlock(this, "campfire");
        Archipelago.PROXY.addItemRender(Item.getItemFromBlock(this), "campfire");
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ACTIVE, meta == 0 ? false : true);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(ACTIVE) == true ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVE);
    }

    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        if(worldIn.getBlockState(pos).getValue(ACTIVE)){

        }else if(playerIn.getActiveItemStack() != null && playerIn.getActiveItemStack().getItem() != null && playerIn.getActiveItemStack().getItem() == Items.FLINT && new Random().nextInt(3) == 0){
            worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(ACTIVE, true));
        }
    }
}
