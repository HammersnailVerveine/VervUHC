package me.Verveine.LGUHC.Game.Configuration.Scenarios.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioCutCleanOres extends ConfigurationScenario {

	public ScenarioCutCleanOres(Main main) {
		super(main);
		this.setName("Cut Clean Minerais");
		this.enabled = true;
		this.setItem(Material.IRON_INGOT);
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		if (!this.getGame().getGamePermissionsManager().isLimiteMinage()) {
			Material material = event.getBlock().getType();
			if (material.equals(Material.IRON_ORE)) {
				event.setCancelled(true);
				event.getBlock().setType(Material.AIR);
				Location location = this.center(event.getBlock().getLocation());	
				event.getPlayer().getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT, 1));
			}
			
			if (material.equals(Material.GOLD_ORE)) {
				event.setCancelled(true);
				event.getBlock().setType(Material.AIR);
				Location location = this.center(event.getBlock().getLocation());	
				event.getPlayer().getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_INGOT, 1));
			}
		} 
	}
}
