package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;
import me.Verveine.LGUHC.GameObjects.Indice;

public class ScenarioIndiceBreak extends ConfigurationScenario {

	public ScenarioIndiceBreak(Main main) {
		super(main);
		this.setName("Indice Reveals Wolf");
		this.enabled = true;
	}


	@EventHandler(priority = EventPriority.NORMAL)
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.GOLD_BLOCK) && this.isEnabled()) {
			for (Indice indice : this.getGame().getGameObjectManager().getIndices()) {
				//if (indice.getLocation().equals(event.getBlock().getLocation())) {
				if (indice.getLocation().distance(event.getBlock().getLocation()) < 1 && !indice.isActivated()) { // .equals() ne fonctionne pas quoi que je fasse ?
					indice.revealWolf(event);
				}
			}
		}
	}
}
