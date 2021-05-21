package me.Verveine.LGUHC.Players;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;

public class Profile {

	private Main plugin;
	private Player player;
	private Role role;
	private Statistics statistics;
	private PlayerState playerState;
	
	public Profile (Main main, Player p, Role r, Statistics s) {
		this.plugin = main;
		this.player = p;
		this.role = r;
		this.statistics = s;
		this.setPlayerStateFromGame();
	}
	
	private void setPlayerStateFromGame() {
		GameLG game = this.getPlugin().getGameManager().getGame();
		if (game.getTime() > 0) {
			this.setPlayerState(PlayerState.SPECTATOR);
		} else {
			this.setPlayerState(PlayerState.LOBBY);
		}
	}
	
	public Main getPlugin() {
		return this.plugin;
	}
	
	public void setPlugin(Main main) {
		this.plugin = main;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Statistics getStatistics() {
		return statistics;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}
}
