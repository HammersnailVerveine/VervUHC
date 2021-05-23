package me.Verveine.LGUHC.Managers.Game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public class WorldManager extends InternalManager {

	private World world;
	private Location spawnLocation;
	private int startBorderSize;
	private int endBorderSize;
	
	public WorldManager(Main main, GameLG game) {
		super(main);
		game.setWorldManager(this);
		this.world = Bukkit.getWorlds().get(0);
		startBorderSize = 2000;
		endBorderSize = 350;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Location getSpawnLocation() {
		return spawnLocation;
	}

	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

	public int getStartBorderSize() {
		return startBorderSize;
	}

	public void setStartBorderSize(int startBorderSize) {
		this.startBorderSize = startBorderSize;
	}

	public int getEndBorderSize() {
		return endBorderSize;
	}

	public void setEndBorderSize(int endBorderSize) {
		this.endBorderSize = endBorderSize;
	}
}
