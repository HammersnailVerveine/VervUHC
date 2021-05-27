package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class CommandTeleport extends PluginCommand {

	public CommandTeleport(Main main) {
		super(main);
		this.labels.add("teleport");
		this.labels.add("tp");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 2) {
			Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
			if (profile != null) {
				profile.randomTeleport();
				this.getGame().getChatManager().sendPrivateMessage("Vous avez �t� t�l�port� par un administrateur.", profile.getPlayer());
				this.getGame().getChatManager().sendSystemMessage("Joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " t�l�port� avec succ�s.");
			} else {
				sender.sendMessage(ChatColor.GOLD + "Le profil du joueur " + args[1] + " est introuvable");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.GOLD + "Usage : /alg teleport pseudo");
			return;
		}
	}
}
