package me.Verveine.LGUHC;

import org.bukkit.plugin.java.JavaPlugin;

import me.Verveine.LGUHC.Commands.*;
import me.Verveine.LGUHC.Managers.GameManager;

public class Main extends JavaPlugin {
	
	private GameManager gameManager; // Code can be arranged to potentially host multiple games at once ?
	
	@Override
	public void onEnable() {
		registerCmds();
		gameManager = new GameManager(this);
	}
	
	@Override
	public void onDisable() {}
	
	public void registerCmds() {
		this.getCommand("alg").setExecutor(new AdminCommands(this));
		this.getCommand("lg").setExecutor(new PlayerCommands(this));
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
}
