package me.Verveine.LGUHC.Players;

import org.bukkit.Location;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;

public class State {
	private Main plugin;
	private PlayerState playerState;
	private int deathTimer;
	private Location deathLocation;
	
	public State(Main main, Profile profile) {
		this.plugin = main;
		profile.setState(this);
		this.playerState = PlayerState.NONE;
		this.deathTimer = 0;
		this.setDeathLocation(plugin.getGameManager().getGame().getWorldManager().getSpawnLocation());
	}
	
	public void setPlayerStateFromGame() {
		GameLG game = this.getPlugin().getGameManager().getGame();
		if (game.getTime() > 0) {
			this.setPlayerState(PlayerState.SPECTATOR);
		} else {
			this.setPlayerState(PlayerState.LOBBY);
		}
	}
	

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}

	public int getDeathTimer() {
		return deathTimer;
	}

	public void setDeathTimer(int deathTimer) {
		this.deathTimer = deathTimer;
	}

	public Location getDeathLocation() {
		return deathLocation;
	}

	public void setDeathLocation(Location deathLocation) {
		this.deathLocation = deathLocation;
	}
	
}
