package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimers;
import me.Verveine.LGUHC.Players.Profile;

public class UpdateManager {
	
	private Main plugin;
	private GameLG game;
	private ConfigurationTimers configurationTimers;
	private boolean isNight;
	
	public UpdateManager(Main main, GameLG game, ConfigurationTimers configurationTimers) {
		this.plugin = main;
		this.game = game;
		this.configurationTimers = configurationTimers;
	}

	public void update() {
		if (game.getGameState().equals(GameState.STARTED)) {
			this.updateTimer();
			this.updateProfiles();
			this.updateNight();
		}
	}
	
	private void updateNight() {
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
		int time = game.getTime();
		game.setTime(time + 1);
		
		if (time == configurationTimers.getTimerRoles()) {
			// TODO
		}
		
		if (time == configurationTimers.getTimerLoups()) {
			// TODO
		}
		
		if (time == configurationTimers.getTimerPvP()) {
			// TODO
		}
		
		if (time == configurationTimers.getTimerBorder()) {
			// TODO
		}
		
		if (time == configurationTimers.getTimerMinage()) {
			// TODO
		}
	}

	public void updateProfiles() {
		for (Profile p : game.getProfilesManager().getProfiles()) {
			if (isNight) {
				p.getRole().updateNight(p.getPlayer());
			} else {
				p.getRole().updateDay(p.getPlayer());
			}
		}
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
