package me.Verveine.LGUHC.Managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Runnables.RunnableUpdate;

public class GameManager {

	private GameLG game;
	private RunnableUpdate gameRunnable;
	private Main plugin;
	
	public GameManager(Main main) {
		this.plugin = main;
	}
	
	public void createGame(Player host) {
		new GameLG(plugin, host, this);
		this.gameRunnable = new RunnableUpdate(plugin);
		
		this.gameRunnable .runTaskTimer(plugin, 0, 20);
		this.game.getWorldManager().setSpawnLocation(host.getLocation());
		this.game.getGameObjectManager().getSpawnBox().CreateFromPlayer(host);
		this.game.getChatManager().sendSystemMessage("New game successfully created.\n ");
		this.game.getChatManager().sendSystemMessage(host.getName() + " was set as te host");
		for (Player p : Bukkit.getOnlinePlayers()) {
			game.getProfilesManager().updateProfiles(p);
		}
	}

	public boolean hasGame() {
		return game != null;
	}

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}

	public RunnableUpdate getGameRunnable() {
		return gameRunnable;
	}

	public void setGameRunnable(RunnableUpdate gameRunnable) {
		this.gameRunnable = gameRunnable;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
