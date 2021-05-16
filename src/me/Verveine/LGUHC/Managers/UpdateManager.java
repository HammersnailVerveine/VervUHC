package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;

public class UpdateManager {
	
	private Main plugin;
	private GameLG game;
	private boolean isNight;
	
	public UpdateManager(Main main) {
		this.plugin = main;
		this.game = main.getGame();
	}

	public void update() {
		this.updateTimer();
		this.updateProfiles();
		this.updateNight();
	}
	
	private void updateNight() {
		long time = game.getWorldManager().getWorld().getTime();
		if (!isNight && time > 12000) {
			isNight = true;
			game.getChatManager().sendSystemMessage("C'est la nuit.");
			for (Profile p : game.getProfiles()) {
				p.getRole().resetNight(p.getPlayer());
			}
			return;
		}
		
		if (isNight && time < 12000) {
			isNight = false;
			game.getChatManager().sendSystemMessage("C'est le jour.");
			for (Profile p : game.getProfiles()) {
				p.getRole().resetDay(p.getPlayer());
			}
			return;
		}
	}

	private void updateTimer() {
		game.setTime(game.getTime() + 1);
	}

	public void updateProfiles() {
		for (Profile p : game.getProfiles()) {
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
