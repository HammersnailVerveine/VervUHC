package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;

public class ScenarioDamage extends ConfigurationScenario {

	public ScenarioDamage(Main main) {
		super(main);
		this.setName("Damage Handling");
		this.enabled = true;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player && this.isEnabled()) {
			this.getGame().getDamageManager().onDamage(event);
		}
	}
}
