package roidrole.coloredstone.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import roidrole.coloredstone.BlockColorstoneWire;

//Could probably redirect the == to an instanceOf, but this works, if a bit roundabout
@Mixin(BlockRedstoneDiode.class)
public class MixinBlockRedstoneDiode {
	@WrapOperation(
		method = "getPowerOnSide",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;"
		)
	)
	Block getPowerFromAnyWire(IBlockState instance, Operation<Block> original){
		Block block = instance.getBlock();
		if(block instanceof BlockColorstoneWire){
			return Blocks.REDSTONE_WIRE;
		}
		return block;
	}

	@WrapOperation(
		method = "calculateInputStrength",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;"
		)
	)
	Block inputFromAnyWire(IBlockState instance, Operation<Block> original){
		Block block = instance.getBlock();
		if(block instanceof BlockColorstoneWire){
			return Blocks.REDSTONE_WIRE;
		}
		return block;
	}
}
