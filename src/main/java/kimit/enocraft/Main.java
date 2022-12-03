package kimit.enocraft;

import kimit.enocraft.Market.Market;
import kimit.enocraft.util.InventoryPage.InventoryPageEventHandler;
import kimit.enocraft.util.InventoryPage.InventoryPageManager;
import kimit.enocraft.util.PrefixLogger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin
{
	public static final String PLUGINNAME = "enocraft-plugin-v2";
	public static final String PLAYERSFOLDER = "/players";
	public final PrefixLogger LOGGER = new PrefixLogger(getServer().getLogger());
	public static HashMap<UUID, PlayerInfo> PLAYERS = new HashMap<UUID, PlayerInfo>();
	public static InventoryPageManager INVENTORYPAGEMANAGER = new InventoryPageManager();
	public static final Market MARKET = new Market("Market.yml");

	@Override
	public void onEnable()
	{
		super.onEnable();
		LOGGER.Log("Enocraft plugin is enabled.");

		File dataFolder = new File(getDataFolder().toString());
		if (!dataFolder.exists())
			dataFolder.mkdir();
		File playersFolder = new File(getDataFolder().toString() + PLAYERSFOLDER);
		if (!playersFolder.exists())
			playersFolder.mkdir();

		for (Player player : Bukkit.getServer().getOnlinePlayers())
		{
			UUID uuid = player.getUniqueId();
			PlayerInfo playerInfo = new PlayerInfo(uuid);
			PLAYERS.put(uuid, playerInfo);
		}

		this.getCommand("test").setExecutor(new Commands());
		this.getCommand("newpage").setExecutor(new Commands());
		this.getCommand("removepage").setExecutor(new Commands());
		this.getCommand("openpage").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new InventoryPageEventHandler(), this);
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
		LOGGER.Log("Enocraft plugin is disabled.");

		for (Player player : Bukkit.getServer().getOnlinePlayers())
			PLAYERS.get(player.getUniqueId()).Save();
	}
}
