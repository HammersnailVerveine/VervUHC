package me.Verveine.LGUHC.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.LG.CommandPower;
import me.Verveine.LGUHC.Commands.LG.CommandRole;
import net.md_5.bungee.api.ChatColor;

public class PlayerCommands implements CommandExecutor, TabCompleter {

	static List<PlayerCommand> playerCommands = new ArrayList<PlayerCommand>();
	static Main plugin;
	
	public PlayerCommands(Main instance) {
		plugin = instance;
		
		playerCommands = new ArrayList<PlayerCommand>();
		playerCommands.add(new CommandRole(plugin));
		playerCommands.add(new CommandPower(plugin));
	}

	@Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
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
			for (PlayerCommand command : playerCommands) {
				if (command.getLabels().contains(args[0])) {
					command.onCommand((Player)(sender), cmd, label, args);
					return true;
				}
			}
		}
		return false;
	}

}
