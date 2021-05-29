package me.Verveine.LGUHC.Game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;
import me.Verveine.LGUHC.Managers.GameManager;
import me.Verveine.LGUHC.Managers.Game.ConfigurationsManager;

public class GameListeners implements Listener {
	
	private Main plugin;
	
	public GameListeners(Main main, GameManager gameManager) {
		this.setPlugin(main);
		gameManager.setGameListeners(this);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		for (ConfigurationScenario scenario : this.getConfigurationManager().getConfigurationScenarios()) {
			if (scenario.isEnabled()) {
				scenario.onPlayerJoin(event);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		for (ConfigurationScenario scenario : this.getConfigurationManager().getConfigurationScenarios()) {
			if (scenario.isEnabled()) {
				scenario.onBlockBreak(event);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		for (ConfigurationScenario scenario : this.getConfigurationManager().getConfigurationScenarios()) {
			if (scenario.isEnabled()) {
				scenario.onInventoryClick(event);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamage(EntityDamageEvent event) {
		for (ConfigurationScenario scenario : this.getConfigurationManager().getConfigurationScenarios()) {
			if (scenario.isEnabled()) {
				scenario.onEntityDamage(event);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeath(EntityDeathEvent event) {
		for (ConfigurationScenario scenario : this.getConfigurationManager().getConfigurationScenarios()) {
			if (scenario.isEnabled()) {
				scenario.onEntityDeath(event);
			}
		}
	}
	
	public ConfigurationsManager getConfigurationManager() {
		return this.getPlugin().getGameManager().getGame().getConfigurationsManager();
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
