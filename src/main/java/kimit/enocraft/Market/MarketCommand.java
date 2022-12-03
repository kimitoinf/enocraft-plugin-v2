package kimit.enocraft.Market;

import kimit.enocraft.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MarketCommand implements CommandExecutor
{
	public static final String MARKET = "market";

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
			}
		}
		return true;
	}
}
