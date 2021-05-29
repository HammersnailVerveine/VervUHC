package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerIndice extends ConfigurationTimer {

	public TimerIndice(Main main) {
		super(main);
		this.name = "Apparition d'un indice";
		scoreboardName = "Indice";
		this.material = Material.REDSTONE;
		this.timer = 60 * 95; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		game.getGameObjectManager().generateIndice();
		game.getChatManager().sendGameMessage("Un indice est apparu.");
	}
}
