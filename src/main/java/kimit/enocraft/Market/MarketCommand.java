package kimit.enocraft.Market;

import kimit.enocraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MarketCommand implements CommandExecutor
{
	public static final String MARKET = "market";
	public static final String SELL = "sell";
	public static final String SELLINVENTORY = "Sell";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player)sender;

			switch (label)
			{
				case MARKET:
					Main.MARKET.Open(player);
					break;
				case SELL:
					Inventory inventory = Bukkit.createInventory(null, 9, SELLINVENTORY);
					player.openInventory(inventory);
					break;
				case "getcash":
					player.sendMessage(Long.toString(Main.PLAYERS.get(player.getUniqueId()).getCash()));
					break;
				case "setcash":
					Main.PLAYERS.get(player.getUniqueId()).setCash(Long.parseLong(args[0]));
					break;
			}
		}
		return true;
	}
}
