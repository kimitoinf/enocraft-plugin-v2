package kimit.enocraft.Market;

import kimit.enocraft.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class MarketEventHandler implements Listener
{
	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getView().getTitle().equals(Market.NAME) && Main.INVENTORYPAGEMANAGER.getInventoryPages().get(e.getView().getTitle()) != null && e.getClickedInventory() != null && e.getClickedInventory().equals(e.getView().getTopInventory()))
		{

		}
	}

	@EventHandler
	public void CloseInventory(InventoryCloseEvent e)
	{
		if (e.getView().getTitle().equals(MarketCommand.SELLINVENTORY))
		{
			Player player = (Player)e.getPlayer();
			int count = 0;
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (ItemStack loop : e.getView().getTopInventory().getContents())
			{
				if (loop != null && loop.getType() != Material.AIR)
				{
					count++;
					items.add(loop);
				}
			}
			if (count == 0)
				player.sendMessage("판매하고자 하는 아이템을 인벤토리 한 칸에 올려놔 주십시오.");
			else if (count > 1)
			{
				player.sendMessage("판매하고자 하는 아이템을 인벤토리 한 칸에 올려놔 주십시오.");
				player.sendMessage("이전에 올려놓은 아이템은 /receive 명령어로 다시 받을 수 있습니다.");
				for (ItemStack loop : items)
					Main.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).AddItem(loop);
			}
			else
			{
				player.sendMessage("asdf");
			}
		}
	}
}
