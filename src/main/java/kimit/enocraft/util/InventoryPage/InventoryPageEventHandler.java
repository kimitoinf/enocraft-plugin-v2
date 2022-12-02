package kimit.enocraft.util.InventoryPage;

import kimit.enocraft.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryPageEventHandler implements Listener
{
	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (Main.INVENTORYPAGES.getInventoryPages().get(e.getView().getTitle()) != null && e.getClickedInventory() != null && e.getClickedInventory().equals(e.getView().getTopInventory()))
		{
			InventoryPage ip = Main.INVENTORYPAGES.getInventoryPages().get(e.getView().getTitle());
			int page = Integer.parseInt(e.getClickedInventory().getItem(InventoryPage.CURRENTPOS).getItemMeta().getLore().get(0).split(" ")[0]) - 1;
			final int index = e.getSlot();

			Player player = (Player)e.getWhoClicked();
			if (index == InventoryPage.PREVIOUSPOS && page != 0)
			{
				page--;
				player.openInventory(ip.getInventories().get(page));
			}
			else if (index == InventoryPage.NEXTPOS && page != ip.getInventories().size() - 1)
			{
				page++;
				player.openInventory(ip.getInventories().get(page));
			}
			e.setCancelled(true);
		}
	}
}