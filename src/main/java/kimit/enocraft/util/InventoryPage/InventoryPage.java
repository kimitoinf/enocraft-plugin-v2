package kimit.enocraft.util.InventoryPage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class InventoryPage
{
	private final String NAME;
	private ArrayList<Inventory> INVENTORIES = new ArrayList<Inventory>();

	public static final int PREVIOUSPOS = 48;
	public static final int CURRENTPOS = 49;
	public static final int NEXTPOS = 50;
	public static final int LIMITPOS = 45;

	public static final String PREVIOUS = "이전 페이지";
	public static final String CURRENT = "현재 페이지";
	public static final String NEXT = "다음 페이지";

	public InventoryPage(String name)
	{
		NAME = name;
		CreatePage();
	}

	public ArrayList<Inventory> getInventories()
	{
		return INVENTORIES;
	}

	private void SetPrevious(Inventory inventory)
	{
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(PREVIOUS);
		item.setItemMeta(meta);
		inventory.setItem(PREVIOUSPOS, item);
	}

	private void SetNext(Inventory inventory)
	{
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(NEXT);
		item.setItemMeta(meta);
		inventory.setItem(NEXTPOS, item);
	}

	public void CreatePage()
	{
		INVENTORIES.add(Bukkit.createInventory(null, 54, NAME));
		int page = INVENTORIES.size() - 1;
		Inventory inventory = INVENTORIES.get(page);
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(CURRENT);
		meta.setLore(Arrays.asList(Integer.toString(page + 1) + " 페이지"));
		item.setItemMeta(meta);
		inventory.setItem(CURRENTPOS, item);

		if (page != 0)
		{
			SetPrevious(inventory);
			page--;
			SetNext(INVENTORIES.get(page));
		}
	}

	public void RemovePage()
	{
		INVENTORIES.remove(INVENTORIES.size() - 1);
		INVENTORIES.get(INVENTORIES.size() - 1).setItem(NEXTPOS, new ItemStack(Material.AIR));
	}

	public void AddItem(ItemStack item)
	{
		int page = 0;
		int pos = -1;
		for (; page != INVENTORIES.size(); page++)
		{
			if (INVENTORIES.get(page).firstEmpty() <= LIMITPOS)
				pos = INVENTORIES.get(page).firstEmpty();
		}

		if (pos == -1)
		{
			CreatePage();
			page = INVENTORIES.size() - 1;
			pos = 0;
		}
		INVENTORIES.get(page).setItem(pos, item);
	}

	public void RemoveItem(int page, int index, int count)
	{

	}

	public void OpenInventory(Player player)
	{
		player.openInventory(INVENTORIES.get(0));
	}
}
