package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.GameObjects.SpawnBox;

public class GameObjectManager extends InternalManager {
	private SpawnBox spawnBox;
	
	public GameObjectManager(Main main, GameLG game) {
		super(main, game);
		this.spawnBox = new SpawnBox(game.getWorldManager().getSpawnLocation(), false);
	}
	
	public SpawnBox getSpawnBox() {
		return spawnBox;
	}

	public void setSpawnBox(SpawnBox spawnBox) {
		this.spawnBox = spawnBox;
	}
}
