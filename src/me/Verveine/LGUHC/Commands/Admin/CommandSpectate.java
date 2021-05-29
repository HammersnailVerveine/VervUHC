package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class CommandSpectate extends PluginCommand {

	public CommandSpectate(Main main) {
		super(main);
		this.labels.add("spectate");
		this.labels.add("spec");
		this.labels.add("spectator");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 2) {
			Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
			if (profile != null) {
				if (profile.getState().getPlayerState().equals(PlayerState.SPECTATOR) && this.getGame().getGameState().equals(GameState.LOBBY)) {
					profile.getState().setPlayerState(PlayerState.LOBBY);
					this.getGame().getChatManager().sendSystemMessage("Le joueur " + profile.getPlayer().getName() + " n'est plus un spectateur.");
				} else {
					profile.getState().setPlayerState(PlayerState.SPECTATOR);
					this.getGame().getChatManager().sendSystemMessage("Le joueur " + profile.getPlayer().getName() + " est désormais un spectateur.");
				}
			} else {
				sender.sendMessage(ChatColor.GOLD + "Le profil du joueur " + args[1] + " est introuvable");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.GOLD + "Usage : /alg spectate pseudo");
			return;
		}
	}
}
