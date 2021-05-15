package me.Verveine.LGUHC.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Players.Roles.*;
import net.md_5.bungee.api.ChatColor;

public class TestCommands implements CommandExecutor, TabCompleter {
	
	static Main plugin;
	public TestCommands(Main instance) {
		plugin = instance;
	}
	
	@Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("lgtest")) {
			if (sender instanceof Player) {
				if (!(sender.hasPermission("lgtest.use"))) {
					sender.sendMessage(ChatColor.RED + "Permissions insuffisantes");
					return true;
				}
				
				if (args.length != 1) {
					sender.sendMessage(ChatColor.RED + "needs 1 arg");
					return true;
				}
				
				if (args[0].equalsIgnoreCase("villageois")) {
					Player player = (Player) sender;
					Profile profile = getProfileFromName(player.getName());
					if (profile != null) {
						profile.setRole(new RoleSimpleVillageois());
						sender.sendMessage(ChatColor.BLUE + "Role defini : Simple Villageois");
					} else {
						sender.sendMessage(ChatColor.RED + "null profile");
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("owo")) {
					Player player = (Player) sender;
					Profile profile = getProfileFromName(player.getName());
					if (profile != null) {
						profile.setRole(new RoleLoupGarou());
						sender.sendMessage(ChatColor.BLUE + "Role defini : owo");
					} else {
						sender.sendMessage(ChatColor.RED + "null profile");
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("role")) {
					Player player = (Player) sender;
					Profile profile = getProfileFromName(player.getName());
					if (profile != null) {
						Role role = profile.getRole();
						sender.sendMessage(ChatColor.BLUE + "role : " + role.getColor() + role.getName());
					} else {
						sender.sendMessage(ChatColor.RED + "null profile");
					}
					return true;
				}
				
				sender.sendMessage(ChatColor.RED + "pong");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Non utilisable depuis la console");
			}
		}
		
		return false;
	}
	
	private Profile getProfileFromName(String name) {
		GameLG game = plugin.getGame();
		ArrayList<Profile> profiles = game.getProfiles();
		Profile profile = null;
		
		for (Profile p : profiles) {
			if (name.equalsIgnoreCase(p.getPlayer().getName())) {
				profile = p;
				break;
			}
		}
		
		return profile;
	}
}
