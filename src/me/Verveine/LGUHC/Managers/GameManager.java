package me.Verveine.LGUHC.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.GameListeners;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Runnables.RunnableUpdate;

public class GameManager {

	private GameLG game;
	private GameListeners gameListeners;
	private RunnableUpdate gameRunnable;
	private Main plugin;
	
	public GameManager(Main main) {
		this.plugin = main;
	}
	
	public void createGame(Player host) {
		if (this.gameRunnable != null) { 
			this.gameRunnable.cancel(); // Maybe not necessary --> Avoid creating new runnable if one already exists ?
		}
		
		new GameLG(plugin, host, this);
		this.gameRunnable = new RunnableUpdate(plugin);
		
		this.gameRunnable .runTaskTimer(plugin, 0, 20);
		this.game.getWorldManager().setSpawnLocation(host.getLocation());
		this.game.getGameObjectManager().getSpawnBox().CreateFromPlayer(host);
		this.game.getWorldManager().getWorld().getWorldBorder().setCenter(game.getGameObjectManager().getSpawnBox().getLocation());
		this.game.getWorldManager().getWorld().getWorldBorder().setSize(game.getWorldManager().getStartBorderSize());
		this.game.getChatManager().sendSystemMessage("New game successfully created.\n ");
		this.game.getChatManager().sendSystemMessage(host.getName() + " was set as te host");
		for (Player p : Bukkit.getOnlinePlayers()) {
			game.getProfilesManager().updateProfiles(p);
		}
		
		if (this.gameListeners == null) {
			new GameListeners(plugin, this);
			plugin.getServer().getPluginManager().registerEvents(gameListeners, plugin);
		}
	}

	public void startGame() {
		GameLG game = this.getGame();
		switch (game.getGameState()) {
			case LOBBY:
				game.setGameState(GameState.STARTED);
				
				game.getWorldManager().getWorld().getWorldBorder().setCenter(game.getGameObjectManager().getSpawnBox().getLocation());
				game.getWorldManager().getWorld().getWorldBorder().setSize(game.getWorldManager().getStartBorderSize());
				/*
				for (ConfigurationScenario config : this.getGame().getConfigurationsManager().getConfigurationScenarios()) {
					plugin.getServer().getPluginManager().registerEvents(config, plugin);
				}
				*/
				
				for (World w : Bukkit.getWorlds())
				{
					w.setDifficulty(Difficulty.HARD);
					w.setGameRuleValue("doDaylightCycle", "true");
					w.setGameRuleValue("naturalRegeneration", "false");
					w.setGameRuleValue("reducedDebugInfo", "true");
					w.setGameRuleValue("showDeathMessages", "false");
					w.setPVP(false);
				}
				
				for (Profile profile : game.getProfilesManager().getProfiles()) {
					profile.resetPlayer();
					if (profile.getState().getPlayerState().equals(PlayerState.LOBBY)) {
						profile.getState().setPlayerState(PlayerState.ALIVE);
						profile.getPlayer().setGameMode(GameMode.SURVIVAL);
						profile.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 10, 4, false, false)); // TODO : listener game start
						profile.randomTeleport();
					}

					if (profile.getState().getPlayerState().equals(PlayerState.SPECTATOR)) {
						profile.getPlayer().setGameMode(GameMode.SPECTATOR);
					}
				}
				
				break;
			case ENDED:
				game.setGameState(GameState.STARTED);
				break;
		default:
			break;
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

	public GameListeners getGameListeners() {
		return gameListeners;
	}

	public void setGameListeners(GameListeners gameListeners) {
		this.gameListeners = gameListeners;
	}
}
