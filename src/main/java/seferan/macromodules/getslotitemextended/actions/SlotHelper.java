package seferan.macromodules.getslotitemextended.actions;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SlotHelper {

	private final Minecraft mc; 
	public SlotHelper(Minecraft mc) {
		this.mc = mc;
	}
	public ItemStack getSlotStack(int slotId) {
	    try
	    {
	      if (currentScreenIsContainer())
	      {
	        GuiContainer containerGui = getGuiContainer();
	        if (((containerGui instanceof GuiContainerCreative)) && (slotId >= 100)) {
	          return getStackFromCreativeTabs(slotId);
	        }
	        return getStackFromSurvivalInventory(slotId, containerGui.inventorySlots);
	      }
	      if ((noScreenInGame()) && (slotId >= 1) && (slotId <= 9)) {
	        return (ItemStack)this.mc.player.inventory.mainInventory.get(slotId - 1);
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    return null;
	}

	public GuiContainer getGuiContainer()
	  {
	    if ((this.mc.currentScreen instanceof GuiContainer)) {
	      return (GuiContainer)this.mc.currentScreen;
	    }
	    return null;
	  }
	 
	public boolean currentScreenIsContainer()
	  {
	    return (this.mc.currentScreen != null) && ((this.mc.currentScreen instanceof GuiContainer));
	  }

	  protected ItemStack getStackFromCreativeTabs(int slotId)
	  {
	    int pageNumber = 100;
	    for (CreativeTabs creativeTab : CreativeTabs.CREATIVE_TAB_ARRAY)
	    {
	      if ((creativeTab != CreativeTabs.INVENTORY) && (creativeTab != CreativeTabs.SEARCH))
	      {
	        NonNullList<ItemStack> itemStacks = NonNullList.create();
	        creativeTab.displayAllRelevantItems(itemStacks);
	        for (int stackIndex = 0; stackIndex < itemStacks.size(); stackIndex++)
	        {
	          int virtualSlotId = pageNumber + stackIndex;
	          if (virtualSlotId == slotId) {
	            return (ItemStack)itemStacks.get(stackIndex);
	          }
	          if (virtualSlotId > slotId) {
	            return null;
	          }
	        }
	      }
	      pageNumber += 100;
	    }
	    return null;
	  }
	  
	  protected ItemStack getStackFromSurvivalInventory(int slotId, Container survivalInventory)
	  {
	    List<Slot> itemStacks = survivalInventory.inventorySlots;
	    if ((slotId >= 0) && (slotId < itemStacks.size()))
	    {
	      ItemStack slotStack = ((Slot)itemStacks.get(slotId)).getStack();
	      if (slotStack != null) {
	        return slotStack;
	      }
	    }
	    return null;
	  }
	  
	  private boolean noScreenInGame()
	  {
		  return (this.mc.currentScreen==null) && (this.mc.player != null) && (this.mc.player.inventory != null) && (this.mc.player.inventory.mainInventory != null);
	  }
}
