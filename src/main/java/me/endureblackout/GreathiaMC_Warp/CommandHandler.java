package me.endureblackout.GreathiaMC_Warp;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

	WarpMain core;

	public CommandHandler(WarpMain core) {
		this.core = core;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p;

		if (isPlayer(sender)) {
			p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("warp")) {
				if (args.length == 0) {
					File warps = new File(core.getDataFolder(), "warps.yml");
					YamlConfiguration file = YamlConfiguration.loadConfiguration(warps);

					Set<String> keys = file.getConfigurationSection("Warps").getKeys(false);

					p.sendMessage(ChatColor.BLUE + "Warps:");

					for (String warp : keys) {
						p.sendMessage(ChatColor.BLUE + "     " + warp);
					}
				} else if (args.length == 1) {
					Warp warp = new Warp(args[0]);

					if (warp.warpExists()) {
						warp.getWarp();

						String world = warp.getWorld();
						double x = warp.getX();
						double y = warp.getY();
						double z = warp.getZ();

						Location loc = new Location(Bukkit.getServer().getWorld(world), x, y, z);

						p.teleport(loc);
						p.sendMessage(ChatColor.DARK_BLUE + "WHOOSH!");
					} else {
						p.sendMessage(ChatColor.RED + "That warp doesn't exist.");
					}
				} else if (args.length == 2) {
					if (p.hasPermission("gwarp.admin")) {
						if (args[0].equalsIgnoreCase("create")) {
							String name = args[1];

							String world = p.getLocation().getWorld().getName();
							double x = p.getLocation().getX();
							double y = p.getLocation().getY();
							double z = p.getLocation().getZ();

							Warp newWarp = new Warp(name, world, x, y, z);

							if (newWarp.warpExists()) {
								p.sendMessage(ChatColor.RED + "That warp already exists.");
							} else {
								newWarp.saveWarp();

								p.sendMessage(ChatColor.DARK_GREEN + name + " warp was created.");
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							String name = args[1];

							Warp newWarp = new Warp(name);

							if (newWarp.warpExists()) {
								newWarp.removeWarp();

								p.sendMessage(ChatColor.DARK_GREEN + "Warp removed.");
							} else {
								p.sendMessage(ChatColor.RED + "Warp doesn't exist.");
							}
						}
					}
				}
			}
		}

		return true;

	}

	public boolean isPlayer(CommandSender sender) {
		if (sender instanceof Player)
			return true;

		return false;
	}

}
