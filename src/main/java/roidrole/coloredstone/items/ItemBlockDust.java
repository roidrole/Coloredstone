package roidrole.coloredstone.items;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import roidrole.coloredstone.Tags;
import roidrole.coloredstone.blocks.BlockColorstoneWire;

public class ItemBlockDust extends ItemBlock {
	public EnumDyeColor color;
	public ItemBlockDust(BlockColorstoneWire block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
		this.color = block.color;
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return "tile."+ Tags.MOD_ID+"."+ EnumDyeColor.byMetadata(stack.getMetadata()).getDyeColorName() +"stone_dust";
	}
}
