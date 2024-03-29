package me.Verveine.LGUHC.Game.Configuration.Scenarios.Optional;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;
import me.Verveine.LGUHC.Players.Profile;

public class ScenarioDiamondLimit extends ConfigurationScenario {

	public ScenarioDiamondLimit(Main main) {
		super(main);
		this.setName("Diamond Limit (17)");
		this.enabled = true;
		this.setItem(Material.DIAMOND);
	}

	@Override
	public void onBlockBreak(BlockBreakEvent breakEvent) {
		if (this.isEnabled() && breakEvent.getBlock().getType().equals(Material.DIAMOND_ORE)) {
			GameLG game = this.getGame();
			Profile profile = game.getProfilesManager().getProfileFromName(breakEvent.getPlayer().getName());
			if (profile.getStatistics().getDiamondsMined() >= 17) {
				Player player = profile.getPlayer();
				breakEvent.setCancelled(true);
				breakEvent.getBlock().setType(Material.AIR);
				
				Location location = breakEvent.getBlock().getLocation();
				location.setX((location.getBlockX()) + 0.5);
				location.setY((location.getBlockY()) + 0.5);
				location.setZ((location.getBlockZ()) + 0.5);
				
				player.getWorld().dropItem(location, new ItemStack(Material.GOLD_INGOT));
	            ExperienceOrb orb = player.getWorld().spawn(location, ExperienceOrb.class);
	            orb.setExperience(2);
			} else {
				profile.getStatistics().setDiamondsMined(profile.getStatistics().getDiamondsMined() + 1);
				if (profile.getStatistics().getDiamondsMined() == 17) {
					Player player = profile.getPlayer();
					List <String> messages = new ArrayList<String>();
					messages.add("Limite de Diamants (17) atteinte!");
					messages.add("Tout diamant min� manuellement donnera d�sormais un lingot d'or.");
					
					this.getGame().getChatManager().sendPrivateMessage(messages , player);
				}
			}
		}
	}
}
