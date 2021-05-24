package me.Verveine.LGUHC.Managers.Game;

import java.util.ArrayList;
import java.util.List;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.GameObjects.Indice;
import me.Verveine.LGUHC.GameObjects.SpawnBox;

public class GameObjectManager extends InternalManager {
	private SpawnBox spawnBox;
	private List<Indice> indices;
	
	public GameObjectManager(Main main, GameLG game) {
		super(main);
		game.setGameObjectManager(this);
		this.spawnBox = new SpawnBox(this.getPlugin(), this, false);
		this.setIndices(new ArrayList<Indice>());
	}
	
	public void generateIndice() {
		Indice indice = new Indice(this.getPlugin(), this);
		indice.setLocation(indice.generateLocation());
		indice.create();
	}
	
	public SpawnBox getSpawnBox() {
		return spawnBox;
	}

	public void setSpawnBox(SpawnBox spawnBox) {
		this.spawnBox = spawnBox;
	}

	public List<Indice> getIndices() {
		return indices;
	}

	public void setIndices(List<Indice> indices) {
		this.indices = indices;
	}
}
