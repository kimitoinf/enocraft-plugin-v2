package kimit.enocraft.Market;

import kimit.enocraft.Main;
import kimit.enocraft.util.ConfigFile.ConfigFileProvider;
import kimit.enocraft.util.InventoryPage.InventoryPage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Market extends ConfigFileProvider
{
	public static final String NAME = "Market";
	private final InventoryPage MARKET;

	public Market(String filename)
	{
		super(filename);
		Main.INVENTORYPAGEMANAGER.Register(NAME, NAME);
		MARKET = Main.INVENTORYPAGEMANAGER.getInventoryPages().get(NAME);
	}

	public void Open(Player player)
	{
		MARKET.OpenInventory(player);
	}

	public void SellItem(Player player, ItemStack item, long price)
	{

	}

	public void PurchaseItem()
	{

	}

	@Override
	public void Save()
	{
		super.Save();
	}
}
