package me.Verveine.LGUHC.Players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import net.md_5.bungee.api.ChatColor;

public class Profile {

	private Main plugin;
	private Player player;
	private Role role;
	private Scoreboard scoreboard;
	private Statistics statistics;
	private State state;
	
	public Profile (Main main, Player p, Role r, Statistics s) {
		this.plugin = main;
		this.player = p;
		this.role = r;
		this.statistics = s;
		new State(plugin, this);
		this.getState().setPlayerStateFromGame();
		this.scoreboard = initializeScoreboard();
		this.getPlayer().setScoreboard(scoreboard);
	}
	
	public Scoreboard initializeScoreboard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = manager.getNewScoreboard();	
		scoreboard.registerNewObjective("test", "dummy");
		return scoreboard;
	}
	
	public void updateScoreboard(int nbPlayers, int nbPlayersAlive, ConfigurationTimer configTimer) {
		Role role = this.getRole();
		scoreboard.getObjective("test").unregister();
		Objective objective = scoreboard.registerNewObjective("test", "dummy");
		
		Score roleInfo;
		Score positionY;
		Score player;
		Score distCentreLabel;
		Score distCentre;
		Score timer;
		Score nextEvent;
		Score limG;
		Score border;
		
		String str1 = (configTimer == null ? "-" : configTimer.getScoreboardName());
		String str2 = (configTimer == null ? "" : (" : " + getTimer(configTimer.getTimer() - this.getGame().getTime())));

		roleInfo = objective.getScore(role.getColor() + role.getName());
		positionY = objective.getScore(ChatColor.YELLOW + "Y: " + ChatColor.GREEN + (int)(this.getPlayer().getLocation().getY()));
		player = objective.getScore(ChatColor.YELLOW + "Joueurs: " + ChatColor.GREEN + nbPlayersAlive + "/" + nbPlayers);
		distCentreLabel = objective.getScore(ChatColor.YELLOW + "Dist centre: ");
		distCentre = objective.getScore(ChatColor.GREEN + getDistToCenter());
		timer = objective.getScore(ChatColor.YELLOW + "Timer: " + ChatColor.GREEN + getTimer(this.getGame().getTime()));
		nextEvent = objective.getScore(ChatColor.YELLOW + str1 + ChatColor.GREEN + str2);
		limG = objective.getScore("TODO : grp");
		border = objective.getScore("TODO : border");
		
		roleInfo.setScore(8);
		nextEvent.setScore(7);
		timer.setScore(6);
		player.setScore(5);
		limG.setScore(4);
		positionY.setScore(3);
		distCentreLabel.setScore(2);
		distCentre.setScore(1);
		border.setScore(0);
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.RED + "Loup-Garou UHC ");
		
	}
	
	private GameLG getGame() {
		return this.getPlugin().getGameManager().getGame();
	}
	
	private String getTimer(int timer) {
		int timerSec = timer - ( (int)(timer / 60) ) * 60;
		int timerMin =  (int)(timer / 60);
		return (timerMin + ":" + timerSec);
	}
	
	private String getDistToCenter() {
		double dist = (this.getPlayer().getLocation().distance(this.getPlayer().getWorld().getWorldBorder().getCenter()));
		int valInf = 0;
		while (dist > 300) {
			dist -= 300;
			valInf ++;
		}
		valInf *= 300;
		if (valInf > 9600) return "> 9600";
		String resultInf = abbreviate(valInf);
		String resultSup = abbreviate(valInf + 300);
		return (resultInf + " - " + resultSup);
	}
	
	private String abbreviate(int val) {
		if (val < 1000) {
			return Integer.toString(val);
		} else {
			char[] chars = Integer.toString(val).toCharArray();
			return chars[0] + "k" + chars[1];
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}
}
