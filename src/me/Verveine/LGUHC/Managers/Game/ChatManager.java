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
	
	public void sendProfileDeath(Profile profile) {
		Role role = profile.getRole();
		String messageName = "Le joueur " + role.getColor() + profile.getPlayer().getName() + ChatColor.WHITE + " est mort.";
		String messageRole = "Il était " + role.getColor() + role.getName() + ChatColor.WHITE + ".";
		String filler = "+--------------------------+";
		Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + filler);
		Bukkit.broadcastMessage(ChatColor.WHITE + " " +  messageName);
		Bukkit.broadcastMessage(ChatColor.WHITE + " " +  messageRole);
		Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + filler);
	}
	
	public void sendGameMessage(String message) {
		String filler = "+";
		char[] chars = message.toCharArray();
		for (int i = 0 ; i < chars.length - 4; i ++) {
			filler += "-";
			if (filler.length() > 50) {
				break;
			}
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
			if (filler.length() > 50) {
				break;
			}
		}
		filler += "+";
		player.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + filler);
		player.sendMessage(ChatColor.YELLOW + " " +  message);
		player.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + filler);
	}
	
	public void sendPrivateMessage(List<String> messages, Player player) {
		if (messages.size() > 0) {
			String filler = "+";
			char[] chars = messages.get(0).toCharArray();
			for (int i = 0 ; i < chars.length - 4; i ++) {
				filler += "-";
				if (filler.length() > 50) {
					break;
				}
			}
			filler += "+";
			player.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + filler);
			for (String message : messages) {
				player.sendMessage(ChatColor.YELLOW + " " +  message);
			}
			player.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + filler);
		}
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
		String[] infos = profile.getRole().getDescription().split("\n");
		for (String string : descriptions) {
			index ++;
			camps += string + ChatColor.WHITE + (index < descriptions.size() ? ", " : ".");
		}
		
		
		profile.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "+--------------------------------------+");
		profile.getPlayer().sendMessage(ChatColor.YELLOW + "Vous êtes " + role.getColor() + "" + ChatColor.UNDERLINE + role.getName());
		profile.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "+--------------------------------------+");
		profile.getPlayer().sendMessage(ChatColor.YELLOW + "Camps : " + camps);
		for (String info : infos) {
			profile.getPlayer().sendMessage(ChatColor.ITALIC + info);
		}
		profile.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "+--------------------------------------+");
		
	}
}
