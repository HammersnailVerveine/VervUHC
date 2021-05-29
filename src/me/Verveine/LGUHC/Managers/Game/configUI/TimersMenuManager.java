package me.Verveine.LGUHC.Managers.Game.configUI;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import net.md_5.bungee.api.ChatColor;

public class TimersMenuManager extends InternalMenuManager {
	
	private ConfigurationTimer selectedConfiguration;

	public TimersMenuManager(Main main, MenusManager menusManager) {
		super(main, 54, "Configuration Rôles");
		menusManager.setTimersMenuManager(this);
	}
	
	public void updateTimers() {
		int slot = 18;
		for (ConfigurationTimer configTimer : this.getGame().getConfigurationsManager().getConfigurationTimers()) {
			ItemStack item = configTimer.getDisplayItem();
			String name = ChatColor.YELLOW + item.getItemMeta().getDisplayName() + " : " + getTimer(configTimer.getTimer());
			this.setItem(this.getInventory(), slot, item.getType(), name, 1);
			slot ++;

			if (selectedConfiguration == null) {
				selectedConfiguration = configTimer;
			}
		}
		String configName = ChatColor.YELLOW + selectedConfiguration.getDisplayItem().getItemMeta().getDisplayName() + " : " + getTimer(selectedConfiguration.getTimer());
		this.setItem(this.getInventory(), 5, selectedConfiguration.getDisplayItem().getType(), configName, 1);
	}
	
	private String getTimer(int timer) {
		int timerSec = timer % 60;
		int timerMin =  (int)(timer / 60);
		String min = "";
		String sec = "";
		if (timerSec < 10) {
			sec = "0";
		}
		
		if (timerMin < 10) {
			min = "0";
		}
		return (min + timerMin + ":" + sec + timerSec);
	}

	@Override
	public void setDefaults() {
		this.updateTimers();
		this.setItem(this.getInventory(), 0, Material.BARRIER, ChatColor.RED + "Retour", 1);
		this.setItem(this.getInventory(), 2, Material.REDSTONE_BLOCK, ChatColor.RED + "Remove 5 minutes", 1);
		this.setItem(this.getInventory(), 3, Material.REDSTONE_BLOCK, ChatColor.RED + "Remove 1 minute", 1);
		this.setItem(this.getInventory(), 4, Material.REDSTONE_BLOCK, ChatColor.RED + "Remove 5 seconds", 1);
		this.setItem(this.getInventory(), 6, Material.EMERALD_BLOCK, ChatColor.GREEN + "Add 5 seconds", 1);
		this.setItem(this.getInventory(), 7, Material.EMERALD_BLOCK, ChatColor.GREEN + "Add 1 minute", 1);
		this.setItem(this.getInventory(), 8, Material.EMERALD_BLOCK, ChatColor.GREEN + "Add 5 minutes", 1);
	}

	@Override
	public void onClick(InventoryClickEvent clickEvent) {
		int slot = clickEvent.getSlot();
		if (slot < 9) {
			switch(slot) {
			case 0:
				clickEvent.getWhoClicked().openInventory(this.getGame().getMenusManager().getMainMenuManager().getInventory());
				break;
			case 2:
				this.getSelectedConfiguration().remove(300);
				break;
			case 3:
				this.getSelectedConfiguration().remove(60);
				break;
			case 4:
				this.getSelectedConfiguration().remove(5);
				break;
			case 6:
				this.getSelectedConfiguration().add(5);
				break;
			case 7:
				this.getSelectedConfiguration().add(60);
				break;
			case 8:
				this.getSelectedConfiguration().add(300);
				break;
			default:
				break;
			}
		} else if (clickEvent.getCurrentItem().getType() != Material.IRON_FENCE) {
			this.setSelectedConfiguration(this.getGame().getConfigurationsManager().getConfigurationTimers().get(slot - 18));
		}
		this.updateTimers();
	}

	public ConfigurationTimer getSelectedConfiguration() {
		return selectedConfiguration;
	}

	public void setSelectedConfiguration(ConfigurationTimer selectedItem) {
		this.selectedConfiguration = selectedItem;
	}

}
