package me.Verveine.LGUHC.Managers.ConfigUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Managers.InternalManager;
import net.md_5.bungee.api.ChatColor;

public abstract class InternalMenuManager extends InternalManager {

	private Inventory inventory;
	
	public InternalMenuManager(Main main, int size, String name) {
		super(main);
		this.initializeInventory(size, name);
		this.setDefaults();
	}

	public abstract void setDefaults();
	public abstract void onClick(InventoryClickEvent clickEvent);
	
	public void initializeInventory(int size, String name) {
		Inventory inventory = Bukkit.createInventory(null, size, name);
		for (int i = 0 ; i < inventory.getSize() ; i++) {
			this.setItem(inventory, i, Material.IRON_FENCE, ChatColor.BLUE + "", 1);
		}
		this.setInventory(inventory);
	}
	
	public void setItem(Inventory menu, int slot, Material material, String name, int amount) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		menu.setItem(slot, item);
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
