package kimit.enocraft;

import kimit.enocraft.util.ConfigFile.ConfigFile;
import kimit.enocraft.util.ConfigFile.ConfigFileProvider;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class PlayerInfo extends ConfigFileProvider
{
	private final UUID PLAYERUUID;
	private final String CASHCONFIG = "Cash";
	private long CASH = 0;

	public PlayerInfo(UUID uuid)
	{
		super(Main.PLAYERSFOLDER + "/" + uuid.toString() + ".yml");
		PLAYERUUID = uuid;

		CASH = CONFIG.getLong(CASHCONFIG);
	}

	@Override
	public void Save()
	{
		CONFIG.set(CASHCONFIG, CASH);
		super.Save();
	}

	public UUID getUUID()
	{
		return PLAYERUUID;
	}

	public long getCash()
	{
		return CASH;
	}

	public void setCash(long cash)
	{
		CASH = cash;
	}

	public void addCash(long cash)
	{
		CASH += cash;
	}
}
