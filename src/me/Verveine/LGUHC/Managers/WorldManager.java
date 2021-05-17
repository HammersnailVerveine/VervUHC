package me.Verveine.LGUHC.Managers;

import org.bukkit.Location;
import org.bukkit.World;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public class WorldManager {
	
	private Main plugin;
	private GameLG game;
	private World world;
	private Location spawnLocation; 

	public WorldManager(Main main, GameLG game, World world) {
		this.plugin = main;
		this.setGame(game);
		this.world = world;
		this.spawnLocation = world.getSpawnLocation();
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

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}

}
