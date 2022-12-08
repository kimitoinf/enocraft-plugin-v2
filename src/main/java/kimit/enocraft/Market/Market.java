package kimit.enocraft.Market;

import kimit.enocraft.Main;
import kimit.enocraft.PlayerInfo.PlayerInfoCommand;
import kimit.enocraft.util.ConfigFile.ConfigFileProvider;
import kimit.enocraft.util.InventoryPage.InventoryPage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Market extends ConfigFileProvider
{
	public static final String NAME = "Market";
	public static final String COUNT = "Count";
	private final InventoryPage MARKET;
	public static final int FEE = 10;

	public Market(String filename)
	{
		super(filename);
		Main.INVENTORYPAGEMANAGER.Register(NAME, NAME);
		MARKET = Main.INVENTORYPAGEMANAGER.getInventoryPages().get(NAME);

		for (int loop = 0; loop != CONFIG.getInt(COUNT); loop++)
			MARKET.AddItem(CONFIG.getItemStack(Integer.toString(loop)));
	}

	public void Open(Player player)
	{
		MARKET.OpenInventory(player);
	}

	public void SellItem(Player player, ItemStack item, long price)
	{
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("판매자 : " + player.getName());
		lore.add("개당 가격 : " + Long.toString(price));
		meta.setLore(lore);
		item.setItemMeta(meta);
		MARKET.AddItem(item);
		long fee = (long)Math.ceil((double)(price * item.getAmount()) / FEE);
		Main.PLAYERS.get(player.getUniqueId()).addCash(-fee);
		player.sendMessage("수수료 " + Long.toString(fee) + "원이 지불되었습니다.");
		player.sendMessage("아이템 등록이 완료되었습니다.");
	}

	public void PurchaseItem(Player player, int page, int index, long price)
	{
		ItemStack item = Main.MARKET.MARKET.getInventories().get(page).getItem(index).clone();
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.setAmount(1);
		Main.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).AddItem(item);
		Main.PLAYERS.get(player.getUniqueId()).addCash(-price);
		player.sendMessage("아이템 " + item.getType().toString() + "을 " + Long.toString(price) + "원에 구매하였습니다.");
		player.sendMessage("구매한 아이템은 /" + PlayerInfoCommand.RECEIVE + " 명령어로 받을 수 있습니다.");
		Main.MARKET.MARKET.RemoveItem(page, index, 1);
	}

	@Override
	public void Save()
	{
		ArrayList<ItemStack> items = MARKET.getItems();
		CONFIG.set(COUNT, items.size());
		for (int loop = 0; loop != items.size(); loop++)
			CONFIG.set(Integer.toString(loop), items.get(loop));
		super.Save();
	}
}
