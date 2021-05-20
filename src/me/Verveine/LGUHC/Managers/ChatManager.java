package me.Verveine.LGUHC.Managers;

import org.bukkit.Bukkit;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import net.md_5.bungee.api.ChatColor;

public class ChatManager extends InternalManager {

	public ChatManager(Main main, GameLG game) {
		super(main);
		game.setChatManager(this);
	}
	
	public void sendSystemMessage(String message) {
		Bukkit.broadcastMessage(ChatColor.GOLD + "Système : " + message);
	}
}
