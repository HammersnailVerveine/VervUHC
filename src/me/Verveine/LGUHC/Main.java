package me.Verveine.LGUHC;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Verveine.LGUHC.Commands.*;
import me.Verveine.LGUHC.Managers.GameManager;

public class Main extends JavaPlugin implements Listener {
	
	private GameManager gameManager; // Code can be arranged to potentially host multiple games at once ?
	
	@Override
	public void onEnable() {
		registerCmds();
		PluginManager pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(this, this);
		gameManager = new GameManager(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerCmds() {
		this.getCommand("lgtest").setExecutor(new TestCommands(this));
		this.getCommand("alg").setExecutor(new AdminCommands(this));
		this.getCommand("lg").setExecutor(new PlayerCommands(this));
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	// Handlers (temp)
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (gameManager.hasGame()) {
			gameManager.getGame().getProfilesManager().updateProfiles(event.getPlayer());
		}
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onClick(InventoryClickEvent clickEvent) {
		this.getGameManager().getGame().getMenusManager().onClick(clickEvent);
	}
	
}
