package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimers;
import me.Verveine.LGUHC.Players.Roles.*;

public class ConfigurationsManager {
	private Main plugin;
	private GameLG game;
	private ConfigurationTimers configurationTimers;
	private ArrayList<ConfigurationRole> configurationRoles;
	
	public ConfigurationsManager(Main main, GameLG game) {
		this.plugin = main;
		this.game = game;
		this.setConfigurationTimers(new ConfigurationTimers());
		this.setConfigurationRoles(new ArrayList<ConfigurationRole>());
	}
	
	public void generateConfigurationRoles() {
		configurationRoles.add(new ConfigurationRole(new RoleSimpleVillageois()));
		configurationRoles.add(new ConfigurationRole(new RoleLoupGarou()));
	}

	public ConfigurationTimers getConfigurationTimers() {
		return configurationTimers;
	}

	public void setConfigurationTimers(ConfigurationTimers configurationTimers) {
		this.configurationTimers = configurationTimers;
	}

	public ArrayList<ConfigurationRole> getConfigurationRoles() {
		return configurationRoles;
	}

	public void setConfigurationRoles(ArrayList<ConfigurationRole> configurationRoles) {
		this.configurationRoles = configurationRoles;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}
}
