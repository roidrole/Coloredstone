package roidrole.coloredstone.blocks;

import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import roidrole.coloredstone.Tags;
import roidrole.coloredstone.items.ItemBlockRepeater;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class BlockColorstoneRepeater extends BlockRedstoneRepeater {
	public static Map<EnumDyeColor, BlockColorstoneRepeater> poweredMap = new EnumMap<>(EnumDyeColor.class);
	public static Map<EnumDyeColor, BlockColorstoneRepeater> unpoweredMap = new EnumMap<>(EnumDyeColor.class);

	public EnumDyeColor color;
	public Item item;
	public BlockColorstoneRepeater(boolean powered, EnumDyeColor color) {
		super(powered);
		this.color = color;
		this.setTranslationKey(Tags.MOD_ID+"."+color.getName()+"stone_repeater");
		if(powered) {
			this.setRegistryName(Tags.MOD_ID, color.getName() + "stone_powered_repeater");
			poweredMap.put(color, this);
		} else {
			this.setRegistryName(Tags.MOD_ID, color.getName() + "stone_unpowered_repeater");
			this.item = new ItemBlockRepeater(this);
			ForgeRegistries.ITEMS.register(item);
			unpoweredMap.put(color, this);
		}
		ForgeRegistries.BLOCKS.register(this);
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
	protected IBlockState getPoweredState(IBlockState unpoweredState) {
		int delay = unpoweredState.getValue(DELAY);
		boolean locked = unpoweredState.getValue(LOCKED);
		EnumFacing facing = unpoweredState.getValue(FACING);
		return poweredMap.get(this.color).getDefaultState().withProperty(FACING, facing).withProperty(DELAY, delay).withProperty(LOCKED, locked);
	}

	@Override
	protected IBlockState getUnpoweredState(IBlockState poweredState){
		int delay = poweredState.getValue(DELAY);
		boolean locked = poweredState.getValue(LOCKED);
		EnumFacing facing = poweredState.getValue(FACING);
		return unpoweredMap.get(this.color).getDefaultState().withProperty(FACING, facing).withProperty(DELAY, delay).withProperty(LOCKED, locked);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(item);
	}
}
