package roidrole.coloredstone.blocks;

import net.minecraft.block.BlockRedstoneComparator;
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
import roidrole.coloredstone.items.ItemBlockComparator;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class BlockColorstoneComparator extends BlockRedstoneComparator {
	public static Map<EnumDyeColor, BlockColorstoneComparator> poweredMap = new EnumMap<>(EnumDyeColor.class);
	public static Map<EnumDyeColor, BlockColorstoneComparator> unpoweredMap = new EnumMap<>(EnumDyeColor.class);

	public EnumDyeColor color;
	public Item item;
	public BlockColorstoneComparator(boolean powered, EnumDyeColor color) {
		super(powered);
		this.color = color;
		this.setTranslationKey(Tags.MOD_ID+"."+color.getName()+"stone_comparator");
		if(powered) {
			this.setRegistryName(Tags.MOD_ID, color.getName() + "stone_powered_comparator");
			poweredMap.put(color, this);
		} else {
			this.setRegistryName(Tags.MOD_ID, color.getName() + "stone_unpowered_comparator");
			this.item = new ItemBlockComparator(this);
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
		EnumFacing facing = unpoweredState.getValue(FACING);
		BlockRedstoneComparator.Mode mode = unpoweredState.getValue(MODE);
		return poweredMap.get(this.color).getDefaultState().withProperty(FACING, facing).withProperty(MODE, mode);
	}

	@Override
	protected IBlockState getUnpoweredState(IBlockState poweredState){
		EnumFacing facing = poweredState.getValue(FACING);
		BlockRedstoneComparator.Mode mode = poweredState.getValue(MODE);
		return unpoweredMap.get(this.color).getDefaultState().withProperty(FACING, facing).withProperty(MODE, mode);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(item);
	}
}
