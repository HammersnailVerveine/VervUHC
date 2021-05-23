package me.Verveine.LGUHC.Game.Configuration;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class ConfigurationTimer {
	protected int timer;
	protected String name;
	protected String scoreboardName;
	private ItemStack displayItem;
	protected Material material;
	private boolean active;
	private Main plugin;

	public abstract void run(GameLG game);
	
	public ConfigurationTimer(Main main) {
		this.setPlugin(main);
		timer = 0;
		name = "default";
		scoreboardName = "default";
		material = Material.APPLE;
		active = false;
		setItem();
	}
	
	public void setItem() {
		displayItem = new ItemStack(material);
		ItemMeta meta = displayItem.getItemMeta();
		meta.setDisplayName(name);
		displayItem.setItemMeta(meta);
	}
	
	public void add(int value) {
		timer = timer + value;
	}
	
	public void remove(int value) {
		timer = timer - value;
		timer = timer < 5 ? 5 : timer;
	}
	
	public void activate(GameLG game) {
		active = true;
		run(game);
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
	public String getScoreboardName() {
		return scoreboardName;
	}
	public void setScoreboardName(String scoreboardName) {
		this.scoreboardName = scoreboardName;
	}
	public ItemStack getDisplayItem() {
		return displayItem;
	}
	public void setDisplayItem(ItemStack displayItem) {
		this.displayItem = displayItem;
	}
	public Main getPlugin() {
		return plugin;
	}
	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
