package me.Verveine.LGUHC.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.Player.*;
import net.md_5.bungee.api.ChatColor;

public class PlayerCommands implements CommandExecutor, TabCompleter {

	static List<PluginCommand> pluginCommands = new ArrayList<PluginCommand>();
	static Main plugin;
	
	public PlayerCommands(Main instance) {
		plugin = instance;
		
		pluginCommands = new ArrayList<PluginCommand>();
		pluginCommands.add(new CommandRole(plugin));
		pluginCommands.add(new CommandPower(plugin));
		pluginCommands.add(new CommandInventory(plugin));
		pluginCommands.add(new CommandLG(plugin));
	}
	
	@Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> result = new ArrayList<String>();
		
		if (args.length == 1) {
			ArrayList<String> allCommands = new ArrayList<String>();
			for (PluginCommand command : pluginCommands) {
				if (command.getLabels().size() > 0) {
					allCommands.add(command.getLabels().get(0));
				}
			}
				
			if (!(args[0].contentEquals(""))) {
				for (String s : allCommands) {
					if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
						result.add(s);
					}
				}
			} else {
				for (String s : allCommands) {
					result.add(s);
				}
			}
			
			Collections.sort(result);
			return result;
		}	
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!plugin.getGameManager().hasGame()) {
			sender.sendMessage(ChatColor.RED + "Aucune partie trouvée.");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "La commande doit être utilisée par un joueur.");
			return true;
		}
		
		if (args.length > 0) {
			for (PluginCommand command : pluginCommands) {
				if (command.getLabels().contains(args[0])) {
					command.onCommand((Player)(sender), cmd, label, args);
					return true;
				}
			}
		}
		sender.sendMessage(ChatColor.RED + "Commande invalide");
		return false;
	}

}
