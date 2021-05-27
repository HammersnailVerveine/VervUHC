package me.Verveine.LGUHC.Managers.Game;

import java.util.ArrayList;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent.*;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.Optional.*;
import me.Verveine.LGUHC.Game.Configuration.Timers.*;
import me.Verveine.LGUHC.Players.Roles.LG.*;
import me.Verveine.LGUHC.Players.Roles.Village.*;
import me.Verveine.LGUHC.Players.Roles.Village.Info.*;
import me.Verveine.LGUHC.Players.Roles.Solo.*;

public class ConfigurationsManager extends InternalManager {
	private ArrayList<ConfigurationTimer> configurationTimers;
	private ArrayList<ConfigurationRole> configurationRoles;
	private ArrayList<ConfigurationScenario> configurationScenarios;
	private ArrayList<ConfigurationScenario> configurationScenariosPermanent;
	
	public ConfigurationsManager(Main main, GameLG game) {
		super(main);
		game.setConfigurationsManager(this);
		configurationTimers = generateConfigurationTimers();
		configurationRoles = generateConfigurationRoles();
		configurationScenarios = generateConfigurationScenarios();
		configurationScenariosPermanent = generateConfigurationScenariosPermanent();
	}
	
	public ArrayList<ConfigurationScenario> generateConfigurationScenariosPermanent() {
		ArrayList<ConfigurationScenario> configuration = new ArrayList<ConfigurationScenario>();
		configuration.add(new ScenarioIndiceBreak(this.getPlugin()));
		configuration.add(new ScenarioDamage(this.getPlugin()));
		configuration.add(new ScenarioMenus(this.getPlugin()));
		return configuration;
	}
	
	public ArrayList<ConfigurationScenario> generateConfigurationScenarios() {
		ArrayList<ConfigurationScenario> configuration = new ArrayList<ConfigurationScenario>();
		configuration.add(new ScenarioDiamondLimit(this.getPlugin()));
		return configuration;
	}
	
	public ArrayList<ConfigurationRole> generateConfigurationRoles() {
		ArrayList<ConfigurationRole> configuration = new ArrayList<ConfigurationRole>();
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleSimpleVillageois(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleMineur(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleMontreurDours(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleRenard(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RolePetiteFille(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleLoupGarou(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleLoupGarouPerfide(this.getPlugin())));
		configuration.add(new ConfigurationRole(this.getPlugin(), new RoleAssassin(this.getPlugin())));
		return configuration;
	}
	
	public ArrayList<ConfigurationTimer> generateConfigurationTimers() {
		ArrayList<ConfigurationTimer> configuration = new ArrayList<ConfigurationTimer>();
		configuration.add(new TimerRoles(this.getPlugin()));
		configuration.add(new TimerLoups(this.getPlugin()));
		configuration.add(new TimerBorder(this.getPlugin()));
		configuration.add(new TimerBorderEnd(this.getPlugin()));
		configuration.add(new TimerIndice(this.getPlugin()));
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
			if (config.getTimer() > this.getGame().getTime() && config.getTimer() < time && !config.isActive()) {
				time = config.getTimer();
				configReturn = config;
			}
		}
		return configReturn;
	}

	public ArrayList<ConfigurationScenario> getConfigurationScenariosAll() {
		ArrayList<ConfigurationScenario> scenarios = new ArrayList<ConfigurationScenario>();
		scenarios.addAll(configurationScenarios);
		scenarios.addAll(configurationScenariosPermanent);
		return scenarios;
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

	public ArrayList<ConfigurationScenario> getConfigurationScenarios() {
		return configurationScenarios;
	}

	public void setConfigurationScenarios(ArrayList<ConfigurationScenario> configurationScenarios) {
		this.configurationScenarios = configurationScenarios;
	}
	public ArrayList<ConfigurationScenario> getConfigurationScenariosPermanent() {
		return configurationScenariosPermanent;
	}
	public void setConfigurationScenariosPermanent(ArrayList<ConfigurationScenario> configurationScenariosPermanent) {
		this.configurationScenariosPermanent = configurationScenariosPermanent;
	}
}
