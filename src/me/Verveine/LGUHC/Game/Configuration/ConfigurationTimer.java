package me.Verveine.LGUHC.Game.Configuration;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Game.GameLG;

public abstract class ConfigurationTimer {
	protected int timer;
	protected String name;
	private ItemStack item;
	protected Material material;
	private boolean active;

	public abstract void setDefaults();
	public abstract void run(GameLG game);
	
	public ConfigurationTimer() {
		timer = 0;
		name = "default";
		material = Material.APPLE;
		active = false;
		setDefaults();
		setItem();
	}
	
	public void setItem() {
		item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
	}
	
	public void activate(GameLG game) {
		active = true;
		run(game);
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
