package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerLoups extends ConfigurationTimer {

	public TimerLoups(Main main) {
		super(main);
		this.name = "Liste des Loups";
		this.scoreboardName = "L. Loups";
		this.material = Material.BONE;
		this.timer = 10; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		game.getGamePermissionsManager().setWolfList(true);
	}
}
