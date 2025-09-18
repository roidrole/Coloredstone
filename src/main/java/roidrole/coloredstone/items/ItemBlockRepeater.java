package roidrole.coloredstone.items;

import net.minecraft.item.ItemBlock;
import roidrole.coloredstone.Tags;
import roidrole.coloredstone.blocks.BlockColorstoneRepeater;

public class ItemBlockRepeater extends ItemBlock {
	public ItemBlockRepeater(BlockColorstoneRepeater block) {
		super(block);
		this.setRegistryName(Tags.MOD_ID, block.color.getDyeColorName()+"stone_repeater");
		this.setTranslationKey(block.getTranslationKey());
	}
}