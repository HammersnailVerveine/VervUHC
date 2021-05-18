package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerRoles extends ConfigurationTimer {

	@Override
	public void setDefaults() {
		this.name = "Distribution des Rôles";
		this.material = Material.APPLE;
		this.timer = 20 * 60; // TODO : edit default with config
	}

	@Override
	public void run(GameLG game) {
	}
}
