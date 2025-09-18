package roidrole.coloredstone.blocks;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roidrole.coloredstone.ColoredStone;
import roidrole.coloredstone.Tags;

import java.awt.*;
import java.util.Random;

public class BlockColorstoneWire extends BlockRedstoneWire {
	public final EnumDyeColor color;
	public final Color defaultColor;

	public BlockColorstoneWire(EnumDyeColor color){
		super();
		this.color = color;
		this.defaultColor = new Color(color.getColorValue());
		this.setRegistryName(Tags.MOD_ID, color.getName()+"stone_wire");
		this.setTranslationKey(Tags.MOD_ID+"."+color.getName()+"stone_wire");
		ColoredStone.dustArray[color.ordinal()] = this;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ColoredStone.dustMap.get(color));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ColoredStone.dustMap.get(color);
	}

	@SideOnly(Side.CLIENT)
	public static int getColor(IBlockState state) {
		Color defaultColor = ((BlockColorstoneWire)state.getBlock()).defaultColor;
		float power = (float)(state.getValue(BlockRedstoneWire.POWER)+8)/16.0f;
		return MathHelper.rgb(
			MathHelper.clamp((int)(defaultColor.getRed() * power), 0, 255),
			MathHelper.clamp((int)(defaultColor.getGreen() * power), 0, 255),
			MathHelper.clamp((int)(defaultColor.getBlue() * power), 0, 255)
		);
	}

	@Override
	public int getMaxCurrentStrength(World world, BlockPos pos, int strength) {
		IBlockState state = world.getBlockState(pos);
		if(state.getBlock() instanceof BlockRedstoneWire){
			return Math.max(state.getValue(POWER), strength);
		}
		return strength;
	}
}
