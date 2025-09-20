package roidrole.coloredstone.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import roidrole.coloredstone.Tags;

public class ItemBlockLamp extends ItemCloth {
	public static ItemBlockLamp INSTANCE = new ItemBlockLamp();
	public ItemBlockLamp() {
		super(Blocks.REDSTONE_LAMP);
		this.setRegistryName(Tags.MOD_ID, "colorstone_lamp");
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return "tile."+Tags.MOD_ID+"."+ EnumDyeColor.byMetadata(stack.getMetadata()).getDyeColorName() +"stone_lamp";
	}
}
