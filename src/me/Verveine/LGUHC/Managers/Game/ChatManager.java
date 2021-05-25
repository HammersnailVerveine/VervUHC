package me.Verveine.LGUHC.Managers.Game;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import net.md_5.bungee.api.ChatColor;

public class ChatManager extends InternalManager {

	public ChatManager(Main main, GameLG game) {
		super(main);
		game.setChatManager(this);
	}
	
	public void sendSystemMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("alg.use")) {
				player.sendMessage(ChatColor.GOLD + "Système : " + message);
			}
		}
	}
	
	public void sendGameMessage(String message) {
		String filler = "+";
		char[] chars = message.toCharArray();
		for (int i = 0 ; i < chars.length - 4; i ++) {
			filler += "-";
		}
		filler += "+";
		Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + filler);
		Bukkit.broadcastMessage(ChatColor.AQUA + " " +  message);
		Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + filler);
	}
	
	public void sendPrivateMessage(String message, Player player) {
		String filler = "+";
		char[] chars = message.toCharArray();
		for (int i = 0 ; i < chars.length - 4; i ++) {
			filler += "-";
		}
		filler += "+";
		player.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + filler);
		player.sendMessage(ChatColor.YELLOW + " " +  message);
		player.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + filler);
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
	
	public void sendProfileRole(Profile profile) {
		Role role = profile.getRole();
		String camps = "";
		int index = 0;
		List<String> descriptions = role.campsDescriptionToStringList();
		for (String string : descriptions) {
			camps += string + ChatColor.WHITE + (index ++ < descriptions.size() ? ", " : ".");
		}
		
		profile.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "+--------------------------------------+");
		profile.getPlayer().sendMessage(ChatColor.YELLOW + "Vous êtes " + role.getColor() + "" + ChatColor.UNDERLINE + role.getName());
		profile.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "+--------------------------------------+");
		profile.getPlayer().sendMessage(ChatColor.YELLOW + "Camps : " + camps);
		profile.getPlayer().sendMessage(ChatColor.ITALIC + profile.getRole().getDescription());
		profile.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "+--------------------------------------+");
		
	}
}
