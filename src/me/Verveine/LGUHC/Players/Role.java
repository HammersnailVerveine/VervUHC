package me.Verveine.LGUHC.Players;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Enums.Tags;

public abstract class Role implements Cloneable {
	protected ArrayList<Camps> camps;
	protected ArrayList<Tags> tags;
	protected boolean appearsOnWolfList;
	protected String name;
	protected ChatColor color;
	protected String description;
	
	public Role() {
		this.camps = new ArrayList<Camps>();
		this.tags = new ArrayList<Tags>();
		appearsOnWolfList = false;
		name = "Blank Name";
		color = ChatColor.WHITE;
		description = "Vous n'avez pas de rôle. Vous ne devez pas gagner. C'est bête. Peut être pourriez vous attendre d'en avoir un, ou blâmer Verveine pour s'être planté quelque part?";
		setDefaults();
	}
	
	public abstract void setDefaults();
	public abstract void updateStart(Player player);
	public abstract void updateDay(Player player);
	public abstract void updateNight(Player player);
	public abstract void resetDay(Player player);
	public abstract void resetNight(Player player);
	public abstract void useCommand(CommandSender sender, Command cmd, String label, String[] args);
	
	public Role clone() {
		return this.clone();
	}

	public ArrayList<Camps> getCamps() {
		return camps;
	}

	public void setCamps(ArrayList<Camps> camps) {
		this.camps = camps;
	}

	public ArrayList<Tags> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tags> tags) {
		this.tags = tags;
	}

	public boolean isAppearsOnWolfList() {
		return appearsOnWolfList;
	}

	public void setAppearsOnWolfList(boolean appearsOnWolfList) {
		this.appearsOnWolfList = appearsOnWolfList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}
}
