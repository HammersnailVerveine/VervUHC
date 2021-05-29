package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class CommandGetRole extends PluginCommand {

	public CommandGetRole(Main main) {
		super(main);
		this.labels.add("getrole");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 2) {
			Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
			if (profile != null) {
				sender.sendMessage(ChatColor.GOLD + "Le joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " est " + profile.getRole().getColor() + profile.getRole().getName());
				return;
			} else {
				sender.sendMessage(ChatColor.GOLD + "Le profil du joueur " + args[1] + " est introuvable");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.GOLD + "Usage : /alg getrole pseudo");
			return;
		}
	}
}
