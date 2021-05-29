package me.Verveine.LGUHC.Game.Configuration.Scenarios.Optional;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioCutCleanFood extends ConfigurationScenario {

	public ScenarioCutCleanFood(Main main) {
		super(main);
		this.setName("Cut Clean Food");
		this.enabled = true;
		this.setItem(Material.COOKED_MUTTON);
	}

	@Override
	public void onEntityDeath(EntityDeathEvent eDeathEvent) {
		for (ItemStack i : eDeathEvent.getDrops()) {
			switch (i.getType()) {
				case RAW_BEEF:
					i.setType(Material.COOKED_BEEF);
					break;
				case PORK:
					i.setType(Material.GRILLED_PORK);
					break;
				case RAW_CHICKEN:
					i.setType(Material.COOKED_CHICKEN);
					break;
				case RABBIT:
					i.setType(Material.COOKED_RABBIT);
					break;
				case MUTTON:
					i.setType(Material.COOKED_MUTTON);
					break;
				default:
					break;
			}
		}	
	} 
}
