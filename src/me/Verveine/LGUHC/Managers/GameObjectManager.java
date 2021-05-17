package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.GameObjects.SpawnBox;

public class GameObjectManager {
	private Main plugin;
	private GameLG game;
	private SpawnBox spawnBox;
	
	public GameObjectManager(Main main, GameLG game) {
		this.plugin = main;
		this.game = game;
		this.spawnBox = new SpawnBox(game.getWorldManager().getSpawnLocation(), false);
	}
	
	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public SpawnBox getSpawnBox() {
		return spawnBox;
	}

	public void setSpawnBox(SpawnBox spawnBox) {
		this.spawnBox = spawnBox;
	}
}
