package me.Verveine.LGUHC.Game.Configuration.Scenarios;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class ConfigurationScenario {

	private Main plugin;
	protected boolean enabled;
	protected String name;
	protected String scoreboardName;
	private ItemStack displayItem;
	
	public ConfigurationScenario(Main main) {
		this.setPlugin(main);
		enabled = false;
		name = "default";
		setItem(Material.APPLE);
	}
	
	public void setItem(Material material) {
		displayItem = new ItemStack(material);
		ItemMeta meta = displayItem.getItemMeta();
		meta.setDisplayName(name);
		displayItem.setItemMeta(meta);
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {}
	public void onBlockBreak(BlockBreakEvent event) {}
	public void onInventoryClick(InventoryClickEvent event) {}
	public void onEntityDamage(EntityDamageEvent event) {}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
