package me.Verveine.LGUHC.Managers;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

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
				
				for (Profile profile : game.getProfilesManager().getProfiles()) {
					profile.getPlayer().getInventory().clear();
					if (profile.getState().getPlayerState().equals(PlayerState.LOBBY)) {
						profile.getState().setPlayerState(PlayerState.ALIVE);
						profile.randomTeleport();
					}
				}
				
				for (World w : Bukkit.getWorlds())
				{
					w.setDifficulty(Difficulty.HARD);
					w.setGameRuleValue("doDaylightCycle", "true");
					w.setGameRuleValue("naturalRegeneration", "false");
					w.setGameRuleValue("reducedDebugInfo", "true");
					w.setGameRuleValue("showDeathMessages", "false");
				}
				
				for (Player ap:Bukkit.getOnlinePlayers())
				{
					ap.getInventory().clear();
					ap.getEquipment().setBoots(new ItemStack(Material.AIR));
					ap.getEquipment().setLeggings(new ItemStack(Material.AIR));
					ap.getEquipment().setChestplate(new ItemStack(Material.AIR));
					ap.getEquipment().setHelmet(new ItemStack(Material.AIR));
					ap.setFoodLevel(20);
					ap.setSaturation(20);
					ap.setHealth(1);
					ap.setMaxHealth(20);
					ap.setHealth(ap.getMaxHealth());
					ap.setLevel(0);
					ap.resetPlayerTime();
					ap.setGameMode(GameMode.SURVIVAL);
					for (PotionEffect pe: ap.getActivePotionEffects())
						ap.removePotionEffect(pe.getType());
					for (Achievement ac:Achievement.values())
						ap.removeAchievement(ac);
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
