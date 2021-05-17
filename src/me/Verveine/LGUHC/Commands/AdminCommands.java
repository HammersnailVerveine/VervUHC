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
import me.Verveine.LGUHC.Managers.GameManager;
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
				RunnableUpdate runnable = new RunnableUpdate(plugin, game);
				plugin.setGameManager(new GameManager(plugin, game, runnable));
				game.getWorldManager().setSpawnLocation(player.getLocation());
				game.getGameObjectManager().getSpawnBox().CreateFromPlayer(player);
				Bukkit.broadcastMessage("test 6");
				for (Player p : Bukkit.getOnlinePlayers()) {
					game.getProfilesManager().updateProfiles(p);
				}
				game.getChatManager().sendSystemMessage("New game successfully created.\n ");
				game.getChatManager().sendSystemMessage(sender.getName() + " was set as te host");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Non utilisable depuis la console (requiert d'être utilisé par l'host).");
				return true;
			}
		}
		
		if (plugin.getGameManager() == null) {
			sender.sendMessage(ChatColor.RED + "Aucune partie trouvée");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("start")) {
			plugin.getGameManager().getGameRunnable().runTaskTimer(plugin, 0, 20);
			plugin.getGameManager().getGame().getChatManager().sendSystemMessage(sender.getName() + " started the game!");
		}

		sender.sendMessage(ChatColor.RED + "Commande invalide");
		return false;
	}
}