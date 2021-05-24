package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Game.GameLG;

public class CommandConfig extends PluginCommand {

	public CommandConfig(Main main) {
		super(main);
		this.labels.add("config");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 1) {
			sender.sendMessage(ChatColor.RED + "Usage : /alg config");
		}
		
		if (sender instanceof Player) {
			Player playerTarget = (Player) sender;
			GameLG game = this.getPlugin().getGameManager().getGame();
			playerTarget.openInventory(game.getMenusManager().getMainMenuManager().getInventory());
		} else {
			sender.sendMessage(ChatColor.RED + "Vous n'etes pas un joueur");
		}
	}
}
