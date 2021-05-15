package me.Verveine.LGUHC.Players;

import org.bukkit.entity.Player;

public class Profile {

	private Player player;
	private Role role;
	private Statistics statistics;
	
	public Profile (Player p, Role r, Statistics s) {
		this.player = p;
		this.role = r;
		this.statistics = s;
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
}
