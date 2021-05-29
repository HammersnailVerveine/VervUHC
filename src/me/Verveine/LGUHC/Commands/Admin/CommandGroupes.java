package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import net.md_5.bungee.api.ChatColor;

public class CommandGroupes extends PluginCommand {

	public CommandGroupes(Main main) {
		super(main);
		this.labels.add("groups");
		this.labels.add("groupes");
		this.labels.add("group");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 2) {
			this.getGame().setGroupLimit(Integer.parseInt(args[1]));
			this.getGame().getChatManager().sendGameMessage("Limite de grouche définie à " + ChatColor.WHITE +  this.getGame().getGroupLimit() + ChatColor.AQUA + ".");
			return;
		} else {
			this.getGame().getChatManager().sendGameMessage("Attention à la limite de groupes!");
			return;
		}
	}
}
