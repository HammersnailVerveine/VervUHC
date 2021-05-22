package me.Verveine.LGUHC.Managers.Game;

import org.bukkit.Bukkit;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class ChatManager extends InternalManager {

	public ChatManager(Main main, GameLG game) {
		super(main);
		game.setChatManager(this);
	}
	
	public void sendSystemMessage(String message) {
		Bukkit.broadcastMessage(ChatColor.GOLD + "Système : " + message);
	}
	
	public void sendPlayersList() {
		GameLG game = this.getGame();
		Bukkit.broadcastMessage(ChatColor.GOLD + "======== Joueurs ========");
		for (Profile profile : game.getProfilesManager().getProfiles()) {
			if (!profile.getState().getPlayerState().equals(PlayerState.SPECTATOR)) {
				String alive = profile.getState().getPlayerState().equals(PlayerState.DEAD) ? ChatColor.RED + "Mort" : ChatColor.GREEN + "Vivant";
				Bukkit.broadcastMessage(profile.getPlayer().getName() + " : " + alive + ChatColor.WHITE + " : " +  profile.getRole().getColor() + profile.getRole().getName());
			}
		}
	}
}
