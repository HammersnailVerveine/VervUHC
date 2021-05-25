package me.Verveine.LGUHC.Players;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
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
		scoreboard.registerNewObjective("objectiveLG", "dummy");
		return scoreboard;
	}
	
	public void updateScoreboard(int nbPlayers, int nbPlayersAlive, ConfigurationTimer configTimer) {
		scoreboard.getObjective("objectiveLG").unregister();
		Objective objective = scoreboard.registerNewObjective("objectiveLG", "dummy");
		
		List<Score> scores = new ArrayList<Score>();
		
		String str1 = " " + (configTimer == null ? "-" : configTimer.getScoreboardName());
		String str2 = (configTimer == null ? "" : (" : " + getTimer(configTimer.getTimer() - this.getGame().getTime())));

		scores.add(objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "+------------+"));
		scores.add(objective.getScore(ChatColor.AQUA + " Timer: " + ChatColor.WHITE + getTimer(this.getGame().getTime())));
		scores.add(objective.getScore(ChatColor.AQUA + str1 + ChatColor.WHITE + str2));
		scores.add(objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "+----------   +"));
		scores.add(objective.getScore(ChatColor.AQUA + " Joueurs: " + ChatColor.WHITE + nbPlayersAlive + "/" + nbPlayers));
		scores.add(objective.getScore(ChatColor.AQUA + " Groupes: " + ChatColor.WHITE + this.getGame().getGroupLimit()));
		scores.add(objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "+--------      +"));
		
		int flag = 0;
		for (Score score : scores) {
			score.setScore(scores.size() - flag);
			flag ++;
		}
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.DARK_AQUA + "_   " + ChatColor.AQUA + "LG UHC" + ChatColor.DARK_AQUA + "   _   ");
	}
	
	@Deprecated
	public void updateScoreboardOld(int nbPlayers, int nbPlayersAlive, ConfigurationTimer configTimer) {
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
		positionY = objective.getScore(ChatColor.YELLOW + "Y: " + ChatColor.WHITE + (int)(this.getPlayer().getLocation().getY()));
		player = objective.getScore(ChatColor.YELLOW + "Joueurs: " + ChatColor.WHITE + nbPlayersAlive + "/" + nbPlayers);
		distCentreLabel = objective.getScore(ChatColor.YELLOW + "Dist centre: ");
		distCentre = objective.getScore(ChatColor.WHITE + getDistToCenter());
		timer = objective.getScore(ChatColor.YELLOW + "Timer: " + ChatColor.WHITE + getTimer(this.getGame().getTime()));
		nextEvent = objective.getScore(ChatColor.YELLOW + str1 + ChatColor.WHITE + str2);
		limG = objective.getScore(ChatColor.YELLOW + "Groupes: " + ChatColor.WHITE + this.getGame().getGroupLimit());
		border = objective.getScore(ChatColor.YELLOW + "Border: " + ChatColor.WHITE + (int)this.getGame().getWorldManager().getWorld().getWorldBorder().getSize());
		
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
		int timerSec = timer % 60;
		int timerMin =  (int)(timer / 60);
		String min = "";
		String sec = "";
		if (timerSec < 10) {
			sec = "0";
		}
		
		if (timerMin < 10) {
			min = "0";
		}
		return (min + timerMin + ":" + sec + timerSec);
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
	
	public void randomTeleport() {	// old code
		Player player = this.getPlayer();
		WorldBorder border = player.getWorld().getWorldBorder();
		double borderSize = border.getSize();
		boolean cancelled = false;
		
		Location location = player.getLocation().clone();		
		
		location.setX(Math.random() * (borderSize) - (borderSize / 2) + border.getCenter().getX());
		location.setZ(Math.random() * (borderSize) - (borderSize / 2) + border.getCenter().getZ());
		
		int height = 255;
		location.setY(height);
		
		while (height > 5 && !(location.getBlock().getType().isSolid())) {
			height --;
			location.setY(height);
	
			Material mat = location.getBlock().getType();
			if ((mat == Material.STATIONARY_WATER) || (mat == Material.STATIONARY_LAVA) || (mat == Material.LEAVES) || (mat == Material.LAVA) || (mat == Material.WATER) || (mat == Material.BARRIER || (mat == Material.STAINED_GLASS)) ) {
				cancelled = true;
			}
			
			if (location.distance(this.getState().getDeathLocation()) < (player.getWorld().getWorldBorder().getSize() / 3)) {
				cancelled = true;
			}
		}
		
		if (!cancelled) {
			location.setY(height + 3);
			player.teleport(location);
		} else {
			randomTeleport();
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
