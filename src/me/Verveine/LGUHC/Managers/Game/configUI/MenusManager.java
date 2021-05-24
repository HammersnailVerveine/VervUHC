package me.Verveine.LGUHC.Managers.Game.configUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Managers.Game.InternalManager;

public class MenusManager extends InternalManager {

	private List<InternalMenuManager> menus = new ArrayList<InternalMenuManager>();
	
	public MenusManager(Main main, GameLG game) {
		super(main);
		game.setMenusManager(this);
		new MainMenuManager(main, this);
		new RolesMenuManager(main, this);
		new TimersMenuManager(main, this);
		new BorderMenuManager(main, this);
		new ScenariosMenuManager(main, this);
	}

	public MainMenuManager getMainMenuManager() {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof MainMenuManager) {
				return (MainMenuManager) menu;
			}
		}
		return null;
	}

	public InternalMenuManager getScenariosMenuManager() {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof ScenariosMenuManager) {
				return (ScenariosMenuManager) menu;
			}
		}
		return null;
	}

	public InternalMenuManager getBorderMenuManager() {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof BorderMenuManager) {
				return (BorderMenuManager) menu;
			}
		}
		return null;
	}

	public InternalMenuManager getTimersMenuManager() {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof TimersMenuManager) {
				return (TimersMenuManager) menu;
			}
		}
		return null;
	}

	public InternalMenuManager getRolesMenuManager() {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof RolesMenuManager) {
				return (RolesMenuManager) menu;
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

	public void setBorderMenuManager(BorderMenuManager borderMenuManager) {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof BorderMenuManager) {
				menus.remove(menu);
			}
		}		
		this.menus.add(borderMenuManager);
	}

	public void setScenariosMenuManager(ScenariosMenuManager scenariosMenuManager) {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof ScenariosMenuManager) {
				menus.remove(menu);
			}
		}		
		this.menus.add(scenariosMenuManager);
	}

	public void setTimersMenuManager(TimersMenuManager timersMenuManager) {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof TimersMenuManager) {
				menus.remove(menu);
			}
		}		
		this.menus.add(timersMenuManager);
	}

	public void setRolesMenuManager(RolesMenuManager rolesMenuManager) {
		for (InternalMenuManager menu : menus) {
			if (menu instanceof RolesMenuManager) {
				menus.remove(menu);
			}
		}		
		this.menus.add(rolesMenuManager);
	}
	
	public void onClick(InventoryClickEvent clickEvent) {
		for (InternalMenuManager menu : this.getMenus()) {
			if (menu.getInventory().equals(clickEvent.getInventory())) {
				if (clickEvent.getCurrentItem() != null && clickEvent.getCurrentItem().getItemMeta() != null && clickEvent.getCurrentItem().getItemMeta().getDisplayName() != null) {
					menu.onClick(clickEvent);
				}
				clickEvent.setCancelled(true);
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
