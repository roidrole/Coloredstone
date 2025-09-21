package roidrole.coloredstone.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import roidrole.coloredstone.Tags;
import roidrole.coloredstone.items.ItemBlockTorch;

import java.util.Random;

public class BlockColorstoneTorch extends BlockRedstoneTorch implements BlockRedstoneTorchInject{

	public EnumDyeColor color;
	public Item item;
	public Block lit;
	public Block unlit;
	public BlockColorstoneTorch(boolean powered, EnumDyeColor color) {
		super(powered);
		this.color = color;
		this.setTranslationKey(Tags.MOD_ID+"."+color.getName()+"stone_torch");
		if(powered) {
			this.setRegistryName(Tags.MOD_ID, color.getName() + "stone_powered_torch");
			this.lit = this;
			this.item = new ItemBlockTorch(this);
			ForgeRegistries.ITEMS.register(item);
		} else {
			this.setRegistryName(Tags.MOD_ID, color.getName() + "stone_unpowered_torch");
			this.unlit = this;
		}
		ForgeRegistries.BLOCKS.register(this);
	}

	public BlockColorstoneTorch(boolean powered, EnumDyeColor color, Item item) {
		this(powered, color);
		this.item = item;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.item;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(item);
	}

	@Override
	public Block coloredstone_getLitBlock() {
		return lit;
	}

	@Override
	public Block coloredstone_getUnlitBlock() {
		return unlit;
	}
}
