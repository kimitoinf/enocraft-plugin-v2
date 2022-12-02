package kimit.enocraft.util.InventoryPage;

import java.util.HashMap;

public class InventoryPageRegisterer
{
	private HashMap<String, InventoryPage> INVENTORYPAGES = new HashMap<String, InventoryPage>();

	public void Register(String name)
	{
		INVENTORYPAGES.put(name, new InventoryPage(name));
	}

	public HashMap<String, InventoryPage> getInventoryPages()
	{
		return INVENTORYPAGES;
	}
}
