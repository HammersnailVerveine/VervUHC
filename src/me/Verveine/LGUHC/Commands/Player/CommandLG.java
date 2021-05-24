package me.Verveine.LGUHC.Commands.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class CommandLG extends PluginCommand {

	public CommandLG(Main main) {
		super(main);
		this.labels.add("lg");
		this.labels.add("loups");
		this.labels.add("loup");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (this.getGame().getGamePermissionsManager().isWolfList()) {
			Profile senderProfile = this.getProfile((Player) sender);
			if (senderProfile.getRole().isAccessWolfList()) {
				sender.sendMessage(ChatColor.RED + "Liste des loups :");
				for (Profile profile : this.getGame().getProfilesManager().getProfiles()) {
					if (profile.getRole().isAppearsOnWolfList()) {
						sender.sendMessage(profile.getPlayer().getName());
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Vous n'avez pas accès à la liste des loups");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "La liste des loups n'est pas disponible");
		}
	}
}
