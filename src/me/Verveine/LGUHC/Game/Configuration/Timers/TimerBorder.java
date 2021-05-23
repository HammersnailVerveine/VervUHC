package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerBorder extends ConfigurationTimer {

	@Override
	public void setDefaults() {
		this.name = "R�tr�cissement de la Border";
		scoreboardName = "Border";
		this.material = Material.CLAY_BRICK;
		this.timer = 60 * 5; // TODO : edit default with config
	}

	@Override
	public void run(GameLG game) {
		int diff = 0;
		for (ConfigurationTimer configTimer : game.getConfigurationsManager().getConfigurationTimers()) {
			if (configTimer instanceof TimerBorderEnd) {
				diff = configTimer.getTimer() - this.getTimer();
			}
		}
		
		diff = diff > 0 ? diff : 0;
		game.getWorldManager().getWorld().getWorldBorder().setSize(game.getWorldManager().getEndBorderSize(), diff);
	}
}
