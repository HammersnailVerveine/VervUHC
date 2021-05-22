package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Players.State;

public class UpdateManager extends InternalManager {
	private boolean isNight;
	
	public UpdateManager(Main main, GameLG game) {
		super(main);
		game.setUpdateManager(this);
	}

	public void update() {
		if (this.getGame().getGameState().equals(GameState.STARTED)) {
			this.updateTimer();
			this.updateProfiles();
			this.updateNight();
		}
	}
	
	private void updateNight() {
		GameLG game = this.getGame();
		long time = game.getWorldManager().getWorld().getTime();
		if (!isNight && time > 12000) {
			isNight = true;
			game.getChatManager().sendSystemMessage("C'est la nuit.");
			for (Profile p : game.getProfilesManager().getProfiles()) {
				p.getRole().resetNight(p.getPlayer());
			}
			return;
		}
		
		if (isNight && time < 12000) {
			isNight = false;
			game.getChatManager().sendSystemMessage("C'est le jour.");
			for (Profile p : game.getProfilesManager().getProfiles()) {
				p.getRole().resetDay(p.getPlayer());
			}
			return;
		}
	}

	private void updateTimer() {
		GameLG game = this.getGame();
		int time = game.getTime();
		game.setTime(time + 1);
		
		ArrayList<ConfigurationTimer> configurationTimers = game.getConfigurationsManager().getConfigurationTimers();
		for (ConfigurationTimer configurationTimer : configurationTimers) {
			if (time == configurationTimer.getTimer() && !configurationTimer.isActive()) {
				configurationTimer.activate(game);
			}
		}
	}

	public void updateProfiles() {
		GameLG game = this.getGame();
		for (Profile profile : game.getProfilesManager().getProfiles()) {
			if (isNight) {
				profile.getRole().updateNight(profile.getPlayer());
			} else {
				profile.getRole().updateDay(profile.getPlayer());
			}
			
			State state = profile.getState();
			if (state.getPlayerState().equals(PlayerState.PREDEAD)) {
				state.setDeathTimer(state.getDeathTimer() - 1);
				if (state.getDeathTimer() < 1) {
					Player player = profile.getPlayer();
					Role role = profile.getRole();
					state.setPlayerState(PlayerState.DEAD);
					game.getChatManager().sendSystemMessage("Le joueur " + player.getName() + " est mort.");
					game.getChatManager().sendSystemMessage("Il était " + role.getColor() + role.getName() + ".");
					player.setGameMode(GameMode.SPECTATOR);
					player.teleport(state.getDeathLocation());
					
					for (ItemStack item : player.getInventory()) {
						if (item != null) {
							player.getWorld().dropItemNaturally(state.getDeathLocation(), item);
						}
					}
					
					if (player.getInventory().getHelmet() != null) {
						player.getWorld().dropItemNaturally(state.getDeathLocation(), player.getInventory().getHelmet());
					}
					
					if (player.getInventory().getChestplate() != null) {
						player.getWorld().dropItemNaturally(state.getDeathLocation(), player.getInventory().getChestplate());
					}
					
					if (player.getInventory().getLeggings() != null) {
						player.getWorld().dropItemNaturally(state.getDeathLocation(), player.getInventory().getLeggings());
					}
					
					if (player.getInventory().getBoots() != null) {
						player.getWorld().dropItemNaturally(state.getDeathLocation(), player.getInventory().getBoots());
					}
					
					player.getWorld().dropItemNaturally(state.getDeathLocation(), new ItemStack(Material.GOLDEN_APPLE, 2));
				}
			}
		}
	}
}
