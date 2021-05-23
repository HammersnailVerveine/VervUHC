package me.Verveine.LGUHC.Game.Configuration;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Players.Role;

public class ConfigurationRole {
	private Main plugin;
	private int amount;
	private Role role;
	private boolean selected = false;
	
	public ConfigurationRole(Main main, Role role) {
		this.setPlugin(main);
		this.role = role;
		selected = false;
		amount = 0;
	}
	
	public void add() {
		amount = amount < 50 ? amount + 1 : amount;
	}
	
	public void remove() {
		amount = amount > 0 ? amount - 1 : amount;
	}
	
	public ItemStack getDisplayItem() {
		ItemStack item = new ItemStack(selected ? Material.MAP : Material.EMPTY_MAP, 1);
		if (amount > 0) item = new ItemStack(selected ? Material.BOOK_AND_QUILL : Material.BOOK, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(role.getColor() + role.getName());
		item.setItemMeta(meta);
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
