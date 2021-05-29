package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;

public class TimerPvP extends ConfigurationTimer {

	public TimerPvP(Main main) {
		super(main);
		this.name = "Activation du PvP";
		scoreboardName = "PvP";
		this.material = Material.IRON_SWORD;
		this.timer = 60 * 40; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		game.getChatManager().sendGameMessage("Le PvP est activé.");
		game.getGamePermissionsManager().setPvpHit(true);

		for (World w : Bukkit.getWorlds()) {
			w.setPVP(true);
		}
	}
}
