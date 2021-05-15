package me.Verveine.LGUHC.Runnables;

import org.bukkit.scheduler.BukkitRunnable;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;


public class RunnableUpdate extends BukkitRunnable {
	
	Main plugin;
	
	public RunnableUpdate(Main main) {
		plugin = main;
	}
	
	@Override
	public void run() {
		GameLG game = plugin.getGame();
		game.update();
	}
}
