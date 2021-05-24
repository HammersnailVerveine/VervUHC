package me.Verveine.LGUHC.GameObjects;

import org.bukkit.Location;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class GameObject {
	private Main plugin;
	protected Location location;
	protected boolean created;
	
	public abstract void create();

	public GameObject(Main main) {
		this.setPlugin(main);
	}
	
	public GameLG getGame() {
		return this.getPlugin().getGameManager().getGame();
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}
}
