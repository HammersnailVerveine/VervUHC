package me.Verveine.LGUHC.Managers.ConfigUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Managers.InternalManager;

public class MenusManager extends InternalManager {

	private List<InternalMenuManager> menus = new ArrayList<InternalMenuManager>();
	
	public MenusManager(Main main, GameLG game) {
		super(main);
		game.setMenusManager(this);
		new MainMenuManager(main, this);
	}

	public InternalMenuManager getMainMenuManager() {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof MainMenuManager) {
				return  menu;
			}
		}
		return null;
	}

	public void setMainMenuManager(MainMenuManager mainMenuManager) {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof MainMenuManager) {
				menus.remove(menu);
			}
		}		
		this.menus.add(mainMenuManager);
	}
	
	public void onClick(InventoryClickEvent clickEvent) {
		for (InternalMenuManager menu : this.getMenus()) {
			if (clickEvent.getCurrentItem() == null || clickEvent.getCurrentItem().getItemMeta() == null || clickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
				return;
			} else {
				if (menu.getInventory().equals(clickEvent.getInventory())) {
					menu.onClick(clickEvent);
				}
			}
		}
	}

	public List<InternalMenuManager> getMenus() {
		return menus;
	}

	public void setMenus(List<InternalMenuManager> menus) {
		this.menus = menus;
	}

}
