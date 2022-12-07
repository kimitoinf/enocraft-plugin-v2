package kimit.enocraft.PlayerInfo;

import kimit.enocraft.Main;
import kimit.enocraft.util.ConfigFile.ConfigFileProvider;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerInfo extends ConfigFileProvider
{
	private final UUID PLAYERUUID;
	public static final String RECEIVE = "Receive";
	public static final String RECEIVECOUNT = RECEIVE + ".Count";

	private final String CASHCONFIG = "Cash";
	private long CASH = 0;
	public boolean ONMARKETSELL = false;
	public ArrayList<ItemStack> MARKETSELL = new ArrayList<ItemStack>();

	public PlayerInfo(UUID uuid)
	{
		super(Main.PLAYERSFOLDER + File.separator + uuid.toString() + ".yml");
		PLAYERUUID = uuid;
		Main.INVENTORYPAGEMANAGER.Register(PLAYERUUID.toString(), RECEIVE);

		for (int loop = 0; loop != CONFIG.getInt(RECEIVECOUNT); loop++)
			Main.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).AddItem(CONFIG.getItemStack(RECEIVE + "." + Integer.toString(loop)));

		CASH = CONFIG.getLong(CASHCONFIG);
	}

	@Override
	public void Save()
	{
		CONFIG.set(CASHCONFIG, CASH);

		ArrayList<ItemStack> items = Main.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).getItems();
		CONFIG.set(RECEIVECOUNT, items.size());
		for (int loop = 0; loop != items.size(); loop++)
			CONFIG.set(RECEIVE + "." + Integer.toString(loop), items.get(loop));
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
