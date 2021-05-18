package me.Verveine.LGUHC.Game.Configuration;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Players.Role;

public class ConfigurationRole {
	private int number;
	private Role role;
	private boolean selected = false;
	
	public ConfigurationRole(Role role) {
		this.role = role;
		selected = false;
		number = 0;
	}
	
	public void add() {
		number = number < 50 ? number + 1 : number;
	}
	
	public void remove() {
		number = number > 0 ? number - 1 : number;
	}
	
	public ItemStack getDisplayItem() {
		ItemStack item = new ItemStack(selected ? Material.MAP : Material.EMPTY_MAP, 1);
		if (number > 0) item = new ItemStack(selected ? Material.BOOK_AND_QUILL : Material.BOOK, number);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(role.getColor() + role.getName());
		item.setItemMeta(meta);
		return item;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
