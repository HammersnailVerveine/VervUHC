package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class InternalManager {
	protected Main plugin;
	
	public InternalManager(Main main) {
		this.plugin = main;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public GameLG getGame() {
		return plugin.getGameManager().getGame();
	}
}
