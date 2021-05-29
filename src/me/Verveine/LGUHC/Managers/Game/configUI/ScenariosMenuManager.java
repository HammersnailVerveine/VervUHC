package me.Verveine.LGUHC.Managers.Game.configUI;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;
import net.md_5.bungee.api.ChatColor;

public class ScenariosMenuManager extends InternalMenuManager {

	public ScenariosMenuManager(Main main, MenusManager menusManager) {
		super(main, 54, "Configuration Scénarios");
		menusManager.setScenariosMenuManager(this);
	}
	
	public void updateScenarios() {
		int slot = 18;
		for (ConfigurationScenario configScenario : this.getGame().getConfigurationsManager().getConfigurationScenarios()) {
			if (!configScenario.isPermanent()) {
				ItemStack item = configScenario.getDisplayItem();
				String name = (configScenario.isEnabled() ? ChatColor.GREEN : ChatColor.RED) + configScenario.getName();
				this.setItem(this.getInventory(), slot, item.getType(), name, 1);
				slot ++;
			}
		}
	}
	
	@Override
	public void setDefaults() {
		this.updateScenarios();
		this.setItem(this.getInventory(), 0, Material.BARRIER, ChatColor.RED + "Retour", 1);
	}

	@Override
	public void onClick(InventoryClickEvent clickEvent) {
		int slot = clickEvent.getSlot();
		if (slot < 9) {
			switch(slot) {
			case 0:
				clickEvent.getWhoClicked().openInventory(this.getGame().getMenusManager().getMainMenuManager().getInventory());
				break;
			default:
				break;
			}
		} else if (clickEvent.getCurrentItem().getType() != Material.IRON_FENCE) {
			ConfigurationScenario configScenario = this.getGame().getConfigurationsManager().getConfigurationScenarios().get(slot - 18);
			configScenario.setEnabled(!configScenario.isEnabled());
		}
		this.updateScenarios();
	}
}
