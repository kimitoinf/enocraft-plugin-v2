package kimit.enocraft.util.ConfigFile;

import kimit.enocraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile
{
	private final String FILENAME;
	private final File CONFIGFILE;
	private final FileConfiguration CONFIG;

	public ConfigFile(String filename)
	{
		FILENAME = filename;
		CONFIGFILE = new File(Bukkit.getPluginManager().getPlugin(Main.PLUGINNAME).getDataFolder(), FILENAME);
		CONFIG = YamlConfiguration.loadConfiguration(CONFIGFILE);
	}

	public FileConfiguration getConfig()
	{
		return CONFIG;
	}

	public void Open()
	{
		if (!CONFIGFILE.exists())
		{
			try
			{
				CONFIGFILE.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void Close()
	{
		try
		{
			CONFIG.save(CONFIGFILE);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
