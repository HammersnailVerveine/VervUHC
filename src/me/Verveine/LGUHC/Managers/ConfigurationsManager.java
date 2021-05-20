package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import me.Verveine.LGUHC.Game.Configuration.Timers.*;
import me.Verveine.LGUHC.Players.Roles.*;

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
		configuration.add(new ConfigurationRole(new RoleSimpleVillageois()));
		configuration.add(new ConfigurationRole(new RoleLoupGarou()));
		return configuration;
	}
	
	public ArrayList<ConfigurationTimer> generateConfigurationTimers() {
		ArrayList<ConfigurationTimer> configuration = new ArrayList<ConfigurationTimer>();
		configuration.add(new TimerRoles());
		configuration.add(new TimerLoups());
		return configuration;
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
