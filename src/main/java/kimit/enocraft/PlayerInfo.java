package kimit.enocraft;

import kimit.enocraft.util.ConfigFile;

import java.util.UUID;

public class PlayerInfo
{
	private final UUID PLAYERUUID;
	private final ConfigFile PLAYER;
	private long CASH = 0;

	public PlayerInfo(UUID uuid)
	{
		PLAYERUUID = uuid;
		PLAYER = new ConfigFile(Main.PLAYERSFOLDER + "/" + uuid.toString() + ".yml");
		PLAYER.Open();

		CASH = PLAYER.CONFIG.getLong("Cash");
	}

	public void Save()
	{
		PLAYER.CONFIG.set("Cash", CASH);

		PLAYER.Close();
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
