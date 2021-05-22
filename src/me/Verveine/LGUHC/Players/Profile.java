package me.Verveine.LGUHC.Players;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;

public class Profile {

	private Main plugin;
	private Player player;
	private Role role;
	private Statistics statistics;
	private State state;
	
	public Profile (Main main, Player p, Role r, Statistics s) {
		this.plugin = main;
		this.player = p;
		this.role = r;
		this.statistics = s;
		new State(plugin, this);
		this.getState().setPlayerStateFromGame();
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
