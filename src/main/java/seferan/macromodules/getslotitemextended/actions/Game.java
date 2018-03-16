package seferan.macromodules.getslotitemextended.actions;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Game {

	public static final Item ITEM_AIR_VIRTUAL = new Item();
			
	 public static String getItemName(Item item)
	  {
	    if (item == ITEM_AIR_VIRTUAL) {
	      return "air";
	    }
	    ResourceLocation itemName = (ResourceLocation)Item.REGISTRY.getNameForObject(item);
	    return itemName == null ? "air" : stripNamespace(itemName.toString());
	  }
	
	  private static String stripNamespace(String itemName)
	  {
	    //return (SETTINGS.stripDefaultNamespace) && (itemName.startsWith("minecraft:")) ? itemName.substring(10) : itemName;
	    
	    //return itemName;  
		return (itemName.startsWith("minecraft:")) ? itemName.substring(10) : itemName;
	  }
}
