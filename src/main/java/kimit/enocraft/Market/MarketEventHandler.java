package kimit.enocraft.Market;

import kimit.enocraft.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MarketEventHandler implements Listener
{
	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getView().getTitle().equals(Market.NAME) && Main.INVENTORYPAGEMANAGER.getInventoryPages().get(e.getView().getTitle()) != null && e.getClickedInventory() != null && e.getClickedInventory().equals(e.getView().getTopInventory()))
		{

		}
	}
}
