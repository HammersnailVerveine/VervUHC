package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import net.md_5.bungee.api.ChatColor;

public class CommandNew extends PluginCommand {

	public CommandNew(Main main) {
		super(main);
		this.labels.add("new");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			this.getPlugin().getGameManager().createGame(player);
		} else {
			sender.sendMessage(ChatColor.RED + "Non utilisable depuis la console (requiert d'être utilisé par l'host).");
		}
	}
}
