package kimit.enocraft.PlayerInfo;

import kimit.enocraft.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerInfoEventHandler implements Listener
{
	@EventHandler
	public void OnJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		Main.PLAYERS.put(player.getUniqueId(), new PlayerInfo(player.getUniqueId()));
	}

	@EventHandler
	public void OnQuit(PlayerQuitEvent e)
	{
		Main.PLAYERS.get(e.getPlayer().getUniqueId()).Save();
		Main.PLAYERS.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getView().getTitle().equals(PlayerInfo.RECEIVE) && e.getClickedInventory().equals(e.getView().getBottomInventory()))
			e.setCancelled(true);
	}
}
