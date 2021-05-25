package me.Verveine.LGUHC.Enums;

import me.Verveine.LGUHC.Translations.FR;
import net.md_5.bungee.api.ChatColor;

public enum Camps {
	VILLAGE,
	WOLF,
	COUPLE,
	SOLO;
	
    @Override
    public String toString() {
        switch (this) {
            case VILLAGE :
            	return FR.CAMP_VILLAGE;
            case WOLF :
            	return FR.CAMP_LOUPS;
            case COUPLE :
            	return FR.CAMP_COUPLE;
            case SOLO :
            	return FR.CAMP_SOLO;
            default :
            	return "Default camp toString();";
        }
    }
    
    public ChatColor getColor() {
        switch (this) {
            case VILLAGE :
            	return ChatColor.GREEN;
            case WOLF :
            	return ChatColor.RED;
            case COUPLE :
            	return ChatColor.LIGHT_PURPLE;
            case SOLO :
            	return ChatColor.YELLOW;
            default :
            	return ChatColor.WHITE;
        }
    }
}
