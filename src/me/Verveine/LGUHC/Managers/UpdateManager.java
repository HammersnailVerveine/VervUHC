package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import me.Verveine.LGUHC.Players.Profile;

public class UpdateManager extends InternalManager {
	private boolean isNight;
	
	public UpdateManager(Main main, GameLG game) {
		super(main, game);
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
		
		ArrayList<ConfigurationTimer> configurationTimers = game.getConfigurationsManager().getConfigurationTimers();
		for (ConfigurationTimer configurationTimer : configurationTimers) {
			if (time == configurationTimer.getTimer() && !configurationTimer.isActive()) {
				configurationTimer.activate(game);
			}
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
}
