package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioMenus extends ConfigurationScenario {

	public ScenarioMenus(Main main) {
		super(main);
		this.setName("Menus");
		this.enabled = true;
		this.permanent = true;
	}

	@Override
	public void onInventoryClick(InventoryClickEvent event) {
		this.getGame().getMenusManager().onClick(event);
	}
}
