package me.Verveine.LGUHC.Managers.Game.configUI;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Managers.Game.WorldManager;
import net.md_5.bungee.api.ChatColor;

public class BorderMenuManager extends InternalMenuManager {
	private Material selectedMaterial;
	
	public BorderMenuManager(Main main, MenusManager menusManager) {
		super(main, 9, "Configuration Border");
		menusManager.setBorderMenuManager(this);
	}
	
	public void updateDisplay() {
		GameLG game = this.getGame();
		String startName = ChatColor.YELLOW + "Starting Border" + " : " + game.getWorldManager().getStartBorderSize();
		String endName = ChatColor.YELLOW +  "Final Border" + " : " + game.getWorldManager().getEndBorderSize();

		int slot1 = this.selectedMaterial.equals(Material.CLAY_BRICK) ? 6 : 2;
		int slot2 = slot1 == 6 ? 2 : 6;

		this.setItem(this.getInventory(), slot1, Material.CLAY_BRICK, startName, 1);
		this.setItem(this.getInventory(), slot2, Material.NETHER_BRICK_ITEM, endName, 1);
	}

	@Override
	public void setDefaults() {
		selectedMaterial = Material.CLAY_BRICK;
		this.updateDisplay();
		this.setItem(this.getInventory(), 0, Material.BARRIER, ChatColor.RED + "Retour", 1);
		this.setItem(this.getInventory(), 4, Material.REDSTONE_BLOCK, ChatColor.RED + "Remove 200 blocks", 1);
		this.setItem(this.getInventory(), 5, Material.REDSTONE_BLOCK, ChatColor.RED + "Remove 50 blocks", 1);
		this.setItem(this.getInventory(), 7, Material.EMERALD_BLOCK, ChatColor.GREEN + "Add 50 blocks", 1);
		this.setItem(this.getInventory(), 8, Material.EMERALD_BLOCK, ChatColor.GREEN + "Add 200 blocks", 1);
	}

	@Override
	public void onClick(InventoryClickEvent clickEvent) {
		int slot = clickEvent.getSlot();
		int value = 0;
		//int value = this.selectedMaterial.equals(Material.CLAY_BRICK) ? this.getGame().getWorldManager().getStartBorderSize() : this.getGame().getWorldManager().getEndBorderSize();
		switch(slot) {
			case 0:
				clickEvent.getWhoClicked().openInventory(this.getGame().getMenusManager().getMainMenuManager().getInventory());
				break;
			case 2:
				selectedMaterial = this.selectedMaterial.equals(Material.CLAY_BRICK) ? Material.NETHER_BRICK_ITEM :Material.CLAY_BRICK;
				break;
			case 4:
				value = -200;
				break;
			case 5:
				value = -50;
				break;
			case 7:
				value = 50;
				break;
			case 8:
				value = 200;
				break;
			default:
				break;
		}
		
		if (value != 0) {
			WorldManager worldManager = this.getGame().getWorldManager();
			if (this.selectedMaterial.equals(Material.CLAY_BRICK)) {
				worldManager.setStartBorderSize(worldManager.getStartBorderSize() + value);
				if (worldManager.getStartBorderSize() < 50) {
					worldManager.setStartBorderSize(50);
				}
			} else {
				worldManager.setEndBorderSize(worldManager.getEndBorderSize() + value);
				if (worldManager.getEndBorderSize() < 50) {
					worldManager.setEndBorderSize(50);
				}
			}
			
			if (this.getGame().getGameState().equals(GameState.LOBBY)) {
				this.getGame().getWorldManager().getWorld().getWorldBorder().setSize(this.getGame().getWorldManager().getStartBorderSize());
			}
		}
		
		this.updateDisplay();
	}

	public Material getSelectedMaterial() {
		return selectedMaterial;
	}

	public void setSelectedMaterial(Material selectedMaterial) {
		this.selectedMaterial = selectedMaterial;
	}

}
