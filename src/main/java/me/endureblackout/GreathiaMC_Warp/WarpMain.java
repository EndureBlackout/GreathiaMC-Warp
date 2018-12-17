package me.endureblackout.GreathiaMC_Warp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class WarpMain extends JavaPlugin {
	public void onEnable() {
		File fl = new File(getDataFolder(), "warps.yml");
		YamlConfiguration conf = new YamlConfiguration();
		try {
			if (!fl.exists()) {
				conf.save(fl);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		getCommand("warp").setExecutor(new CommandHandler(this));
	}
}
