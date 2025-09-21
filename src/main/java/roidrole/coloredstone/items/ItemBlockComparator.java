package roidrole.coloredstone.items;

import net.minecraft.item.ItemBlock;
import roidrole.coloredstone.Tags;
import roidrole.coloredstone.blocks.BlockColorstoneComparator;

public class ItemBlockComparator extends ItemBlock {
	public ItemBlockComparator(BlockColorstoneComparator block) {
		super(block);
		this.setRegistryName(Tags.MOD_ID, block.color.getDyeColorName()+"stone_comparator");
		this.setTranslationKey(block.getTranslationKey());
	}
}