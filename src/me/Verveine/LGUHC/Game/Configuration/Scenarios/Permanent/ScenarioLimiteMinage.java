package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioLimiteMinage extends ConfigurationScenario {
	
	public static List<Material> materials = setOreBlocks();
	
	public static List<Material> setOreBlocks() {
		List<Material> list = new ArrayList<Material>();
		list.add(Material.DIAMOND_ORE);
		list.add(Material.REDSTONE_ORE);
		list.add(Material.GLOWING_REDSTONE_ORE);
		list.add(Material.COAL_ORE);
		list.add(Material.EMERALD_ORE);
		list.add(Material.IRON_ORE);
		list.add(Material.GOLD_ORE);
		list.add(Material.LAPIS_ORE);
		return list;
	}

	public ScenarioLimiteMinage(Main main) {
		super(main);
		this.setName("Indice Break");
		this.enabled = true;
		this.permanent = true;
	}


	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		if (materials.contains(event.getBlock().getType()) && this.getGame().getGamePermissionsManager().isLimiteMinage()) {
			event.setCancelled(true);
			event.getBlock().setType(Material.AIR);
		}
	}
}
