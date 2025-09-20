package roidrole.coloredstone;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import roidrole.coloredstone.blocks.BlockColorstoneRepeater;
import roidrole.coloredstone.blocks.BlockColorstoneWire;
import roidrole.coloredstone.items.ItemBlockLamp;
import roidrole.coloredstone.items.ItemBlockRedstone;

import java.util.EnumMap;
import java.util.Map;


@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class ColoredStone {
    public static Map<EnumDyeColor, Item> dustMap = new EnumMap<>(EnumDyeColor.class);
    public static Block[] dustArray = new Block[16];

    @Mod.EventHandler
    public void construction(FMLConstructionEvent event){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerBlockColours(ColorHandlerEvent.Block event){
        event.getBlockColors().registerBlockColorHandler(
			(state, worldIn, pos, tintIndex) ->
                BlockColorstoneWire.getColor(state),
            dustArray
        );
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        for(EnumDyeColor color : EnumDyeColor.values()){
            dustMap.put(color, new ItemBlock(new BlockColorstoneWire(color)){{
                this.setRegistryName(block.getRegistryName());
                this.setTranslationKey(block.getTranslationKey().substring(5));
            }});
            new BlockColorstoneRepeater(true, color);
            new BlockColorstoneRepeater(false, color);
            BlockColorstoneRepeater.poweredMap.get(color).item = BlockColorstoneRepeater.unpoweredMap.get(color).item;
        }
        for(Item item : dustMap.values()){
            ForgeRegistries.ITEMS.register(item);
        }
        for(Block block : dustArray){
            ForgeRegistries.BLOCKS.register(block);
        }
        ForgeRegistries.ITEMS.register(ItemBlockRedstone.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemBlockLamp.INSTANCE);
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event){
        for(Item item : dustMap.values()){
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
        }
        for (int i = 0; i < 16; i++) {
            ModelLoader.setCustomModelResourceLocation(ItemBlockRedstone.INSTANCE, i, new ModelResourceLocation(
                new ResourceLocation("minecraft", "redstone_block"),
                "color="+EnumDyeColor.byMetadata(i).getDyeColorName())
            );
            ModelLoader.setCustomModelResourceLocation(ItemBlockLamp.INSTANCE, i, new ModelResourceLocation(
                new ResourceLocation("minecraft", "redstone_lamp"),
                "color="+EnumDyeColor.byMetadata(i).getDyeColorName())
            );
        }
        for(BlockColorstoneRepeater block : BlockColorstoneRepeater.unpoweredMap.values()){
            ModelLoader.setCustomModelResourceLocation(block.item, 0, new ModelResourceLocation(block.item.getRegistryName().toString()));
        }
    }
}