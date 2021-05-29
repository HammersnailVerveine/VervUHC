package me.Verveine.LGUHC.Game.Configuration.Scenarios.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioPommes extends ConfigurationScenario {

	public ScenarioPommes(Main main) {
		super(main);
		this.setName("Drop Pommes");
		this.enabled = true;
		this.setItem(Material.APPLE);
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.LEAVES) || event.getBlock().getType().equals(Material.LEAVES_2)) {
			if ((int)(Math.random() * (5)) == 1 && event.getPlayer().getItemInHand().getType() != Material.SHEARS) {
				event.setCancelled(true);
				event.getBlock().setType(Material.AIR);
					
				Location location = this.center(event.getBlock().getLocation());
				event.getPlayer().getWorld().dropItemNaturally(location, new ItemStack(Material.APPLE, 1));
			}
		}
	}
}
