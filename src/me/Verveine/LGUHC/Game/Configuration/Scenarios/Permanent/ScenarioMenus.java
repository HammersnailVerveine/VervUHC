package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioMenus extends ConfigurationScenario {

	public ScenarioMenus(Main main) {
		super(main);
		this.setName("Menus");
		this.enabled = true;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onClick(InventoryClickEvent event) {
		if (this.isEnabled()) {
			this.getGame().getMenusManager().onClick(event);
		}
	}
}
