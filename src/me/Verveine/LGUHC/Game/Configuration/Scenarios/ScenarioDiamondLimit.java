package me.Verveine.LGUHC.Game.Configuration.Scenarios;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationScenario;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class ScenarioDiamondLimit extends ConfigurationScenario {

	public ScenarioDiamondLimit(Main main) {
		super(main);
		this.setName("Diamond Limit (17)");
		this.enabled = true;
		this.setItem(Material.DIAMOND);
	}


	@EventHandler(priority = EventPriority.NORMAL)
	public void onBreak(BlockBreakEvent breakEvent) {
		if (this.isEnabled()) {
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
					player.sendMessage(ChatColor.AQUA + "Limite de Diamants (17) atteinte!");
					player.sendMessage(ChatColor.AQUA + "Tout diamant miné manuellement donnera désormais un lingot d'or");
				}
			}
		}
	}
}
