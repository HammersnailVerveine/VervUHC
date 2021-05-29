package me.Verveine.LGUHC.Game.Configuration.Scenarios.Permanent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.Scenarios.ConfigurationScenario;
import me.Verveine.LGUHC.Players.Profile;

public class ScenarioDamage extends ConfigurationScenario {

	public ScenarioDamage(Main main) {
		super(main);
		this.setName("Damage Handling");
		this.enabled = true;
		this.permanent = true;
	}
	
	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		if (this.getGame().getGameState().equals(GameState.LOBBY)) {
			event.setCancelled(true);
		}
		
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();	
			GameLG game = this.getGame();
			Profile profile = game.getProfilesManager().getProfileFromName(player.getName());

			if (player != null && profile != null) {// && !(damageEvent.getCause().equals(DamageCause.PROJECTILE) || damageEvent.getCause().equals(DamageCause.ENTITY_ATTACK)) ) {
				if (profile.isAlive()) {
					if (this.getGame().getTime() < 30) {
						event.setCancelled(true);
						return;
					}
	
					if ((player.getHealth() - (event.getFinalDamage())) < 1) {
						int time = 15;
						event.setCancelled(true);
						profile.getState().setPlayerState(PlayerState.PREDEAD);
						profile.getState().setDeathTimer(time);
						profile.getState().setDeathLocation(player.getLocation());
						player.teleport(game.getGameObjectManager().getSpawnBox().getLocation());
						for(PotionEffect effect : player.getActivePotionEffects()) {
							player.removePotionEffect(effect.getType());
						}
						Bukkit.getScheduler().scheduleSyncDelayedTask(this.getPlugin(), () -> player.setFireTicks(0), 1);
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, time * 20, 0, false, false));
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, time * 20, 0, false, false));
						player.setHealth(20);
						player.setFoodLevel(20);
						this.getGame().getChatManager().sendPrivateMessage("Vous êtes mort, mais quelque chose peut encore se passer...", player);
						Bukkit.getScheduler().scheduleSyncDelayedTask(this.getPlugin(), () -> game.getUpdateManager().checkWin(), (time + 5) * 20);
	
						for (Profile otherProfile : this.getGame().getProfilesManager().getAliveProfiles()) {
							otherProfile.getRole().updateDeath(otherProfile.getPlayer(), profile);
						}
					}
				}
			}
		}
	}
}
