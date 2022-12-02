package kimit.enocraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		switch (label)
		{
			case "test":
				Main.INVENTORYPAGES.Register("TEST");
				Main.INVENTORYPAGES.getInventoryPages().get("TEST").OpenInventory((Player)sender);
				break;
			case "newpage":
				Main.INVENTORYPAGES.getInventoryPages().get("TEST").CreatePage();
				break;
			case "removepage":
				Main.INVENTORYPAGES.getInventoryPages().get("TEST").RemovePage();
				break;
			case "openpage":
				Main.INVENTORYPAGES.getInventoryPages().get("TEST").OpenInventory((Player)sender);
				break;
		}
		return true;
	}
}
