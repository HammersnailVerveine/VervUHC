package me.Verveine.LGUHC.Runnables;

import org.bukkit.scheduler.BukkitRunnable;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;


public class RunnableUpdate extends BukkitRunnable {
	
	Main plugin;
	GameLG game;
	
	public RunnableUpdate(Main main, GameLG game) {
		this.plugin = main;
		this.game = game;
	}
	
	@Override
	public void run() {
		game.update();
	}
}
