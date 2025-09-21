package roidrole.coloredstone.items;

import net.minecraft.item.ItemBlock;
import roidrole.coloredstone.ColoredStone;
import roidrole.coloredstone.Tags;
import roidrole.coloredstone.blocks.BlockColorstoneTorch;

public class ItemBlockTorch extends ItemBlock {
	public ItemBlockTorch(BlockColorstoneTorch block) {
		super(block);
		this.setRegistryName(Tags.MOD_ID, block.color.getDyeColorName()+"stone_torch");
		this.setTranslationKey(block.getTranslationKey());
		ColoredStone.itemBlockTorchArray[block.color.ordinal()] = this;
	}
}