package me.Verveine.LGUHC.Managers;

import org.bukkit.Location;
import org.bukkit.World;

import me.Verveine.LGUHC.Main;

public class WorldManager {
	
	private Main plugin;
	private World world;
	private Location spawnLocation; 

	public WorldManager(Main main, World world) {
		this.plugin = main;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
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
