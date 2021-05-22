package me.Verveine.LGUHC.Commands;

import java.util.List;

//import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
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
		
		if (args[0].equalsIgnoreCase("new")) { // COMMANDE NEW
			if (sender instanceof Player) {
				Player player = (Player) sender;
				plugin.getGameManager().createGame(player);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Non utilisable depuis la console (requiert d'être utilisé par l'host).");
				return true;
			} // FIN COMMANDE NEW
		}
		
		if (!plugin.getGameManager().hasGame()) {
			sender.sendMessage(ChatColor.RED + "Aucune partie trouvée");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("start")) { // COMMANDE START
			GameLG game = plugin.getGameManager().getGame();
			game.setGameState(GameState.STARTED);
			for (Profile p : game.getProfilesManager().getProfiles()) {
				if (p.getState().getPlayerState().equals(PlayerState.LOBBY)) p.getState().setPlayerState(PlayerState.ALIVE);
			}
			game.getChatManager().sendSystemMessage(sender.getName() + " started the game!");
			//Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> game.getUpdateManager().checkWin(), 5 * 20);
			return true;
		} // FIN COMMANDE START
		
		if (args[0].equalsIgnoreCase("config") || args[0].equalsIgnoreCase("configuration")) { // COMMANDE CONFIG
			
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Usage : /alg config");
				return true;
			}
			
			if (sender instanceof Player) {
				Player playerTarget = (Player) sender;
				GameLG game = plugin.getGameManager().getGame();
				playerTarget.openInventory(game.getMenusManager().getMainMenuManager().getInventory());
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Vous n'etes pas un joueur");
				return true;
			}
		}		// FIN COMMANDE CONFIG

		sender.sendMessage(ChatColor.RED + "Commande invalide");
		return false;
	}
}