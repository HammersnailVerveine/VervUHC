package me.Verveine.LGUHC.Managers.ConfigUI;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import net.md_5.bungee.api.ChatColor;

public class RolesMenuManager extends InternalMenuManager {
	
	private ConfigurationRole selectedConfiguration;

	public RolesMenuManager(Main main, MenusManager menusManager) {
		super(main, 54, "Configuration Rôles");
		menusManager.setRolesMenuManager(this);
	}
	
	public void updateRoles() {
		int slot = 9;
		for (ConfigurationRole configRole : this.getGame().getConfigurationsManager().getConfigurationRoles()) {
			ItemStack item = configRole.getDisplayItem();
			this.setItem(this.getInventory(), slot, item.getType(), item.getItemMeta().getDisplayName(), configRole.getAmount());
			slot ++;

			if (selectedConfiguration == null) {
				selectedConfiguration = configRole;
				selectedConfiguration.setSelected(true);
			}
		}
	}

	@Override
	public void setDefaults() {
		this.updateRoles();
		ItemStack item = getSelectedConfiguration().getDisplayItem();
		this.setItem(this.getInventory(), 3, Material.REDSTONE_BLOCK, ChatColor.RED + "Remove 1", 1);
		this.setItem(this.getInventory(), 4, item.getType(), item.getItemMeta().getDisplayName(), 1);
		this.setItem(this.getInventory(), 5, Material.EMERALD_BLOCK, ChatColor.RED + "Add 1", 1);
	}

	@Override
	public void onClick(InventoryClickEvent clickEvent) {
		int slot = clickEvent.getSlot();
		if (slot < 9) {
			switch(clickEvent.getCurrentItem().getType()) {
			case BARRIER:
				clickEvent.getWhoClicked().closeInventory();
				break;
			case EMERALD_BLOCK:
				this.getSelectedConfiguration().add();
				break;
			case REDSTONE_BLOCK:
				this.getSelectedConfiguration().remove();
			default:
				break;
			}
		} else if (clickEvent.getCurrentItem().getType() != Material.IRON_FENCE) {
			selectedConfiguration.setSelected(false);
			this.setSelectedConfiguration(this.getGame().getConfigurationsManager().getConfigurationRoles().get(slot - 9));
			selectedConfiguration.setSelected(true);
		}
		this.updateRoles();
	}

	public ConfigurationRole getSelectedConfiguration() {
		return selectedConfiguration;
	}

	public void setSelectedConfiguration(ConfigurationRole selectedItem) {
		this.selectedConfiguration = selectedItem;
	}

}
