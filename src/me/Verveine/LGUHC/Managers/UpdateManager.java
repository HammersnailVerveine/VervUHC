package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Players.Profile;

public class UpdateManager {
	
	private Main plugin;
	
	public UpdateManager(Main main) {
		this.plugin = main;
	}
	
	public void updateProfiles(ArrayList<Profile> profiles) {
		for (Profile p : profiles) {
			long time = p.getPlayer().getWorld().getTime();
			if (time > 12000) {
				p.getRole().updateNight(p.getPlayer());
			} else {
				p.getRole().updateDay(p.getPlayer());
			}
		}
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}
