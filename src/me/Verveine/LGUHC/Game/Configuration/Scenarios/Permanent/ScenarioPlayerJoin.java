package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import org.bukkit.event.player.PlayerJoinEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioPlayerJoin extends ConfigurationScenario {

	public ScenarioPlayerJoin(Main main) {
		super(main);
		this.setName("Player Join");
		this.enabled = true;
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (this.isEnabled()) {
			this.getGame().getProfilesManager().updateProfiles(event.getPlayer());
		}
	}
}
