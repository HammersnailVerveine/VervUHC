package me.Verveine.LGUHC.Commands.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Enums.PlayerState;
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
				List<String> liste = new ArrayList<String>();
				liste.add("Liste des loups : ");
				for (Profile profile : this.getGame().getProfilesManager().getProfiles()) {
					if (profile.getRole().isAppearsOnWolfList() && profile.getState().getPlayerState().equals(PlayerState.ALIVE)) {
						liste.add(ChatColor.WHITE + profile.getPlayer().getName());
					}
				}
				this.getGame().getChatManager().sendPrivateMessage(liste, (Player) sender);
			} else {
				sender.sendMessage(ChatColor.RED + "Vous n'avez pas accès à la liste des loups");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "La liste des loups n'est pas disponible");
		}
	}
}
