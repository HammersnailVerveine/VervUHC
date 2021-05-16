package me.Verveine.LGUHC;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Verveine.LGUHC.Commands.AdminCommands;
import me.Verveine.LGUHC.Commands.TestCommands;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Runnables.RunnableUpdate;

public class Main extends JavaPlugin implements Listener {
	
	private GameLG game;
	private RunnableUpdate gameRunnable;
	
	@Override
	public void onEnable() {
		registerCmds();
		PluginManager pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerCmds() {
		this.getCommand("lgtest").setExecutor(new TestCommands(this));
		this.getCommand("alg").setExecutor(new AdminCommands(this));
	}

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}

	public RunnableUpdate getGameRunnable() {
		return gameRunnable;
	}

	public void setGameRunnable(RunnableUpdate gameRunnable) {
		this.gameRunnable = gameRunnable;
	}
	
	// Handlers (temp)
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (game != null) {
			game.updateProfiles(event.getPlayer());
		}
	}
}
