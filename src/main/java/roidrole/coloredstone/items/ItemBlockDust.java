package roidrole.coloredstone.items;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;
import roidrole.coloredstone.blocks.BlockColorstoneWire;

public class ItemBlockDust extends ItemBlock {
	public EnumDyeColor color;
	public ItemBlockDust(BlockColorstoneWire block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
		OreDictionary.registerOre("blockRedstone", this);
	}
}
