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
		GameLG game = new GameLG(plugin, host);
		RunnableUpdate runnable = new RunnableUpdate(plugin, game);
		
		runnable.runTaskTimer(plugin, 0, 20);
		
		game.getWorldManager().setSpawnLocation(host.getLocation());
		game.getGameObjectManager().getSpawnBox().CreateFromPlayer(host);
		game.getChatManager().sendSystemMessage("New game successfully created.\n ");
		game.getChatManager().sendSystemMessage(host.getName() + " was set as te host");
		for (Player p : Bukkit.getOnlinePlayers()) {
			game.getProfilesManager().updateProfiles(p);
		}
		
		this.game = game;
		this.gameRunnable = runnable;
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
