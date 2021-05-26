package me.Verveine.LGUHC.Enums;

import org.bukkit.ChatColor;

import me.Verveine.LGUHC.Translations.FR;

public enum Camp {
	VILLAGE(FR.CAMP_VILLAGE, ChatColor.GREEN),
	WOLF(FR.CAMP_LOUPS, ChatColor.RED),
	COUPLE(FR.CAMP_COUPLE, ChatColor.LIGHT_PURPLE),
	SOLO(FR.CAMP_SOLO, ChatColor.YELLOW);

	private String name;
	private ChatColor color;

	Camp(String name, ChatColor color) {
		this.name = name;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public ChatColor toChatColor() {
		return this.color;
	}
}