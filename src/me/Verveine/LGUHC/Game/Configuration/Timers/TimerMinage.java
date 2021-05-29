package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerMinage extends ConfigurationTimer {

	public TimerMinage(Main main) {
		super(main);
		this.name = "Limite de Minage";
		scoreboardName = "Fin Minage";
		this.material = Material.IRON_PICKAXE;
		this.timer = 60 * 70; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		game.getChatManager().sendGameMessage("Le minage est désactivé.");
		game.getGamePermissionsManager().setLimiteMinage(true);
	}
}
