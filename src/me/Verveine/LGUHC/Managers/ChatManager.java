package me.Verveine.LGUHC.Managers;

import org.bukkit.Bukkit;

import me.Verveine.LGUHC.Main;
import net.md_5.bungee.api.ChatColor;

public class ChatManager {
	private Main plugin;
	
	public ChatManager(Main main) {
		this.setPlugin(main);
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
}
