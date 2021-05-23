package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerBorderEnd extends ConfigurationTimer {

	public TimerBorderEnd(Main main) {
		super(main);
		this.name = "Arrivée de la Border";
		scoreboardName = "Border Fin";
		this.material = Material.NETHER_BRICK_ITEM;
		this.timer = 60 * 7; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		
	}
}
