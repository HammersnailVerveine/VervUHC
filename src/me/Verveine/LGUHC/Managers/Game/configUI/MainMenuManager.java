package me.Verveine.LGUHC.Managers.Game.configUI;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Verveine.LGUHC.Main;
import net.md_5.bungee.api.ChatColor;

public class MainMenuManager extends InternalMenuManager {
	public MainMenuManager(Main main, MenusManager menusManager) {
		super(main, 9, ChatColor.BLUE + "Configuration");
		menusManager.setMainMenuManager(this);
	}

	@Override
	public void setDefaults() {
		this.setItem(this.getInventory(), 0, Material.BARRIER, ChatColor.RED + "Retour", 1);
		this.setItem(this.getInventory(), 3, Material.BOOK, ChatColor.BLUE + "Configuration Rôles", 1);
		this.setItem(this.getInventory(), 4, Material.CLAY_BRICK, ChatColor.BLUE + "Configuration Bordure", 1);
		this.setItem(this.getInventory(), 5, Material.WATCH, ChatColor.BLUE + "Configuration Timers", 1);
		this.setItem(this.getInventory(), 6, Material.APPLE, ChatColor.BLUE + "Configuration Règles", 1);
	}

	@Override
	public void onClick(InventoryClickEvent clickEvent) {
		switch(clickEvent.getCurrentItem().getType()) {
			case BARRIER:
				clickEvent.getWhoClicked().closeInventory();
				break;
			case BOOK:
				clickEvent.getWhoClicked().openInventory(this.getGame().getMenusManager().getRolesMenuManager().getInventory());
				break;
			case CLAY_BRICK:
				clickEvent.getWhoClicked().openInventory(this.getGame().getMenusManager().getBorderMenuManager().getInventory());
				break;
			case WATCH:
				clickEvent.getWhoClicked().openInventory(this.getGame().getMenusManager().getTimersMenuManager().getInventory());
				break;
			default:
				break;
		}
	}
}
