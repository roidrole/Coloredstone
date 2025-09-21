package roidrole.coloredstone.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import roidrole.coloredstone.blocks.BlockRedstoneTorchInject;

@Mixin(BlockRedstoneTorch.class)
public abstract class MixinBlockRedstoneTorch implements BlockRedstoneTorchInject {
	@Redirect(
		method = "updateTick",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/init/Blocks;REDSTONE_TORCH:Lnet/minecraft/block/Block;"
		)
	)
	public Block coloredstone_modifiableLitBlock(){
		return coloredstone_getLitBlock();
	}

	@Redirect(
		method = "updateTick",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/init/Blocks;UNLIT_REDSTONE_TORCH:Lnet/minecraft/block/Block;"
		)
	)
	public Block coloredstone_modifiableUnlitBlock(){
		return coloredstone_getUnlitBlock();
	}


	@Override
	public Block coloredstone_getLitBlock(){
		return Blocks.REDSTONE_TORCH;
	}
	@Override
	public Block coloredstone_getUnlitBlock(){
		return Blocks.UNLIT_REDSTONE_TORCH;
	}
}
