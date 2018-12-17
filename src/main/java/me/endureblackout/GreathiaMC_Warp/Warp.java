package me.endureblackout.GreathiaMC_Warp;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class Warp {
	private String name;
	private String world;
	private double x;
	private double y;
	private double z;

	public Warp(String name, String world, double x, double y, double z) {
		this.name = name;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Warp(String name) {
		this.name = name;
	}

	public void saveWarp() {
		File warps = new File(Bukkit.getPluginManager().getPlugin("GreathiaMCWarp").getDataFolder(), "warps.yml");
		YamlConfiguration file = YamlConfiguration.loadConfiguration(warps);

		file.set("Warps." + name + ".world", world);
		file.set("Warps." + name + ".x", x);
		file.set("Warps." + name + ".y", y);
		file.set("Warps." + name + ".z", z);

		try {
			file.save(warps);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean warpExists() {
		File warps = new File(Bukkit.getPluginManager().getPlugin("GreathiaMCWarp").getDataFolder(), "warps.yml");
		YamlConfiguration file = YamlConfiguration.loadConfiguration(warps);

		if (file.contains("Warps." + name)) {
			return true;
		}

		return false;
	}
	
	public void getWarp() {
		File warps = new File(Bukkit.getPluginManager().getPlugin("GreathiaMCWarp").getDataFolder(), "warps.yml");
		YamlConfiguration file = YamlConfiguration.loadConfiguration(warps);
		
		world = file.getString("Warps." + name + ".world");
		x = file.getDouble("Warps." + name + ".x");
		y = file.getDouble("Warps." + name + ".y");
		z = file.getDouble("Warps." + name + ".z");
	}
	
	public void removeWarp() {
		File warps = new File(Bukkit.getPluginManager().getPlugin("GreathiaMCWarp").getDataFolder(), "warps.yml");
		YamlConfiguration file = YamlConfiguration.loadConfiguration(warps);
		
		file.set("Warps." + name, null);
		
		try {
			file.save(warps);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getWorld() {
		return world;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
}
