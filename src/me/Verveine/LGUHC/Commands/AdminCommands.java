package me.Verveine.LGUHC.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Runnables.RunnableUpdate;
import net.md_5.bungee.api.ChatColor;

public class AdminCommands implements CommandExecutor, TabCompleter {
	
	static Main plugin;
	public AdminCommands(Main instance) {
		plugin = instance;
	}
	
	@Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender.hasPermission("alg.use"))) {
			sender.sendMessage(ChatColor.RED + "Permissions insuffisantes");
			return true;
		}
		
		if (args.length != 1) {
			sender.sendMessage(ChatColor.RED + "needs 1 arg");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("new")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				GameLG game = new GameLG(plugin, player);
				game.getChatManager().sendSystemMessage("New game successfully created.\n ");
				game.getChatManager().sendSystemMessage(sender.getName() + " was set as te host");
				game.getWorldManager().setWorld(player.getWorld());
				game.getWorldManager().setSpawnLocation(player.getLocation());
				for (Player p : Bukkit.getOnlinePlayers()) {
					game.updateProfiles(p);
				}
				plugin.setGame(game);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Non utilisable depuis la console (requiert d'être utilisé par l'host).");
				return true;
			}
		}
		
		if (plugin.getGame() == null) {
			sender.sendMessage(ChatColor.RED + "Aucune partie trouvée");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("start")) {
			RunnableUpdate runnable = new RunnableUpdate(plugin);
			runnable.runTaskTimer(plugin, 0, 20);
			plugin.setGameRunnable(runnable);
			plugin.getGame().getChatManager().sendSystemMessage(sender.getName() + " started the game!");
		}

		sender.sendMessage(ChatColor.RED + "Commande invalide");
		return false;
	}
}