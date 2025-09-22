package roidrole.coloredstone.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import roidrole.coloredstone.Tags;

public class ItemBlockRedstone extends ItemCloth {
	public static ItemBlockRedstone INSTANCE = new ItemBlockRedstone();
	public ItemBlockRedstone() {
		super(Blocks.REDSTONE_BLOCK);
		this.setRegistryName(Tags.MOD_ID, "colorstone_block");
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return "tile."+Tags.MOD_ID+"."+ EnumDyeColor.byMetadata(stack.getMetadata()).getDyeColorName() +"stone_block";
	}
}
