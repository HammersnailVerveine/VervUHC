package me.Verveine.LGUHC.Managers;

import org.bukkit.Bukkit;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import net.md_5.bungee.api.ChatColor;

public class ChatManager {
	private Main plugin;
	private GameLG game;
	
	public ChatManager(Main main, GameLG game) {
		this.plugin = main;
		this.game = game;
	}
	
	public void sendSystemMessage(String message) {
		Bukkit.broadcastMessage(ChatColor.GOLD + "Système : " + message);
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}
}
