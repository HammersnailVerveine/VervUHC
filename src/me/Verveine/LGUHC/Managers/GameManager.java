package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Runnables.RunnableUpdate;

public class GameManager {

	private GameLG game;
	private RunnableUpdate gameRunnable;
	private Main plugin;
	
	public GameManager(Main main, GameLG game, RunnableUpdate gameRunnable) {
		this.plugin = main;
		this.game = game;
		this.gameRunnable = gameRunnable;
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

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
