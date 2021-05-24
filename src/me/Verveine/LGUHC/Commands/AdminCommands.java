package me.Verveine.LGUHC.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.Admin.*;
import net.md_5.bungee.api.ChatColor;

public class AdminCommands implements CommandExecutor, TabCompleter {
	
	static List<PluginCommand> pluginCommands = new ArrayList<PluginCommand>();
	static Main plugin;
	
	public AdminCommands(Main instance) {
		plugin = instance;
		
		pluginCommands = new ArrayList<PluginCommand>();
		pluginCommands.add(new CommandConfig(plugin));
		pluginCommands.add(new CommandNew(plugin));
		pluginCommands.add(new CommandStart(plugin));
		pluginCommands.add(new CommandIndice(plugin));
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
		if (!(sender.hasPermission("alg.use"))) {
			sender.sendMessage(ChatColor.RED + "Permissions insuffisantes");
			return true;
		}
		
		if (args.length > 0) {
			for (PluginCommand command : pluginCommands) {
				if (command.getLabels().contains(args[0])) {
					if (plugin.getGameManager().hasGame() || command instanceof CommandNew) {
						command.onCommand(sender, cmd, label, args);
					} else {
						sender.sendMessage(ChatColor.RED + "Aucune partie trouvée");
					}
					return true;
				}
			}
		}
		sender.sendMessage(ChatColor.RED + "Commande invalide");
		return false;
	}
}