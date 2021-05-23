package me.Verveine.LGUHC.Game.Configuration;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class ConfigurationScenario implements Listener {

	private Main plugin;
	protected boolean enabled;
	protected String name;
	protected String scoreboardName;
	private ItemStack displayItem;
	protected Material material;
	
	public ConfigurationScenario(Main main) {
		this.setPlugin(main);
		enabled = false;
		name = "default";
		material = Material.APPLE;
		setItem();
	}
	
	public void setItem() {
		displayItem = new ItemStack(material);
		ItemMeta meta = displayItem.getItemMeta();
		meta.setDisplayName(name);
		displayItem.setItemMeta(meta);
	}
	
	public GameLG getGame() {
		return this.getPlugin().getGameManager().getGame();
	}
	
	public ItemStack getDisplayItem() {
		return displayItem;
	}
	
	public void setDisplayItem(ItemStack displayItem) {
		this.displayItem = displayItem;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Main getPlugin() {
		return plugin;
	}
	
	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}