package roidrole.coloredstone.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import roidrole.coloredstone.items.ItemBlockLamp;

import static net.minecraft.block.BlockColored.COLOR;

@Mixin(BlockRedstoneLight.class)
public abstract class MixinBlockRedstoneLight extends Block {
	public MixinBlockRedstoneLight(Material materialIn) {
		super(materialIn);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < 16; i++) {
			items.add(new ItemStack(ItemBlockLamp.INSTANCE, 1, i));
		}
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.getBlockColor(state.getValue(COLOR));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLOR).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, COLOR);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(ItemBlockLamp.INSTANCE, 1, state.getValue(COLOR).getMetadata());
	}

	@Redirect(
		method = {"onBlockAdded", "updateTick"},
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/state/IBlockState;"
		)
	)
	public IBlockState coloredstone_injectColor(Block instance, World worldIn, BlockPos pos, IBlockState state){
		return instance.getDefaultState().withProperty(COLOR, state.getValue(COLOR));
	}

	@Redirect(
		method = "neighborChanged",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/state/IBlockState;"
		)
	)
	public IBlockState coloredstone_injectColorNeighbour(Block instance, IBlockState state){
		return instance.getDefaultState().withProperty(COLOR, state.getValue(COLOR));
	}
}
