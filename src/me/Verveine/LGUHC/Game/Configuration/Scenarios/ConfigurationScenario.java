package me.Verveine.LGUHC.Game.Configuration.Scenarios;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class ConfigurationScenario {

	private Main plugin;
	protected boolean enabled;
	protected boolean permanent;
	protected String name;
	protected String scoreboardName;
	private ItemStack displayItem;
	
	public ConfigurationScenario(Main main) {
		this.setPlugin(main);
		enabled = false;
		setPermanent(false);
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
	public void onEntityDeath(EntityDeathEvent event) {}
	
	public Location center(Location location) {
		Location newLocation = location.clone();
		newLocation.setX((int)(newLocation.getBlockX()) + 0.5);
		newLocation.setY((int)(newLocation.getBlockY()) + 0.5);
		newLocation.setZ((int)(newLocation.getBlockZ()) + 0.5);
		return newLocation;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}
}
