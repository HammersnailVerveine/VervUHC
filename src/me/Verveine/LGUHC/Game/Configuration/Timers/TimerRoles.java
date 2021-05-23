package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerRoles extends ConfigurationTimer {

	public TimerRoles(Main main) {
		super(main);
		this.name = "Distribution des Rôles";
		scoreboardName = "Rôles";
		this.material = Material.APPLE;
		this.timer = 5; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		game.getProfilesManager().giveRoles();
	}
}
