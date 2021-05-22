package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import me.Verveine.LGUHC.Game.Configuration.Timers.TimerLoups;
import me.Verveine.LGUHC.Game.Configuration.Timers.TimerRoles;
import me.Verveine.LGUHC.Players.Roles.LG.RoleLoupGarou;
import me.Verveine.LGUHC.Players.Roles.Village.RoleSimpleVillageois;

public class ConfigurationsManager extends InternalManager {
	private ArrayList<ConfigurationTimer> configurationTimers;
	private ArrayList<ConfigurationRole> configurationRoles;
	
	public ConfigurationsManager(Main main, GameLG game) {
		super(main);
		game.setConfigurationsManager(this);
		configurationTimers = generateConfigurationTimers();
		configurationRoles = generateConfigurationRoles();
	}
	
	public ArrayList<ConfigurationRole> generateConfigurationRoles() {
		ArrayList<ConfigurationRole> configuration = new ArrayList<ConfigurationRole>();
		configuration.add(new ConfigurationRole(new RoleSimpleVillageois(this.getPlugin())));
		configuration.add(new ConfigurationRole(new RoleLoupGarou(this.getPlugin())));
		return configuration;
	}
	
	public ArrayList<ConfigurationTimer> generateConfigurationTimers() {
		ArrayList<ConfigurationTimer> configuration = new ArrayList<ConfigurationTimer>();
		configuration.add(new TimerRoles());
		configuration.add(new TimerLoups());
		return configuration;
	}
	
	public int countRoles() {
		int count = 0;
		for (ConfigurationRole configurationRole : this.getConfigurationRoles()) {
			int amount = configurationRole.getAmount();
			if (amount > 0) {
				count += amount;
			}
		}
		return count;
	}
	
	public ConfigurationTimer getNextConfigurationTimer() {
		ConfigurationTimer configReturn = null;
		int time = Integer.MAX_VALUE;
		for (ConfigurationTimer config : this.getConfigurationTimers()) {
			if (config.getTimer() > this.getGame().getTime() && config.getTimer() < time) {
				time = config.getTimer();
				configReturn = config;
			}
		}
		return configReturn;
	}

	public ArrayList<ConfigurationTimer> getConfigurationTimers() {
		return configurationTimers;
	}

	public void setConfigurationTimers(ArrayList<ConfigurationTimer> configurationTimers) {
		this.configurationTimers = configurationTimers;
	}

	public ArrayList<ConfigurationRole> getConfigurationRoles() {
		return configurationRoles;
	}

	public void setConfigurationRoles(ArrayList<ConfigurationRole> configurationRoles) {
		this.configurationRoles = configurationRoles;
	}
}
