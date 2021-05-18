package me.Verveine.LGUHC.Managers;

import org.bukkit.Location;
import org.bukkit.World;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public class WorldManager extends InternalManager {

	private World world;
	private Location spawnLocation; 
	
	public WorldManager(Main main, GameLG game) {
		super(main, game);
		this.world = game.getWorld();
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
}
