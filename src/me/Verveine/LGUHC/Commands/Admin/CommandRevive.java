package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class CommandRevive extends PluginCommand {

	public CommandRevive(Main main) {
		super(main);
		this.labels.add("revive");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 3) {
			Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
			if (profile != null) {
				if (profile.getState().getPlayerState().equals(PlayerState.DEAD) || profile.getState().getPlayerState().equals(PlayerState.PREDEAD)) {
					sender.sendMessage(ChatColor.GOLD + "Joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " ressuscité avec succès.");
					this.getGame().getChatManager().sendPrivateMessage("Vous avez été ressuscité(e) par un administrateur.", profile.getPlayer());
					profile.respawn();
				} else {
					sender.sendMessage(ChatColor.GOLD + "Le joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " n'est pas mort.");
				}
			} else {
				sender.sendMessage(ChatColor.GOLD + "Le profil du joueur " + args[1] + " est introuvable");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.GOLD + "Usage : /alg revive pseudo");
			return;
		}
	}
}
