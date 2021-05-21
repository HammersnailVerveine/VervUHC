package me.Verveine.LGUHC.Runnables;

import org.bukkit.scheduler.BukkitRunnable;

import me.Verveine.LGUHC.Main;


public class RunnableUpdate extends BukkitRunnable {
	Main plugin;
	
	public RunnableUpdate(Main main) {
		this.plugin = main;
	}
	
	@Override
	public void run() {
		plugin.getGameManager().getGame().getUpdateManager().update();
	}
}
