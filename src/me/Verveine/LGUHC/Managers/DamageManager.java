package me.Verveine.LGUHC.Managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class DamageManager extends InternalManager {

	public DamageManager(Main main, GameLG game) {
		super(main);
		game.setDamageManager(this);
	}

	public void onDamage(EntityDamageEvent damageEvent) {
		if(damageEvent.getEntity() instanceof Player) {
	        Player player = (Player) damageEvent.getEntity();	
	        if (player != null) {// && !(damageEvent.getCause().equals(DamageCause.PROJECTILE) || damageEvent.getCause().equals(DamageCause.ENTITY_ATTACK)) ) {
	        	if (this.getGame().getTime() < 30) {
	        		damageEvent.setCancelled(true);
	        		return;
	        	}
	    			
	    		if ((player.getHealth() - (damageEvent.getFinalDamage())) < 1) {
	    			int time = 15;
	    			GameLG game = this.getGame();
	    			Profile profile = game.getProfilesManager().getProfileFromName(player.getName());
	    			damageEvent.setCancelled(true);
	    			profile.getState().setPlayerState(PlayerState.PREDEAD);
	    			profile.getState().setDeathTimer(time);
	    			profile.getState().setDeathLocation(player.getLocation());
	    			player.teleport(game.getGameObjectManager().getSpawnBox().getLocation());
	    			player.getActivePotionEffects().clear();
	    			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> player.setFireTicks(0), 1);
	    			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, time * 20, 0, false, false));
	    			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, time * 20, 0, false, false));
	    			player.setHealth(20);
	    			player.sendMessage(ChatColor.RED + "Vous êtes mort, mais quelque chose peut encore se passer...");
	    			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> game.getUpdateManager().checkWin(), (time + 5) * 20);
	    		}
	        }
		}
	}
}
