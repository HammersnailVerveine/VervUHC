package me.Verveine.LGUHC.Players.Roles.Village;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleSoeur extends Role {

	public RoleSoeur(Main main) {
		super(main);
		this.setColor(ChatColor.GREEN);
		this.setName(FR.SOEUR_ROLE);
		this.camps.add(Camp.VILLAGE);
		this.getCampsDescription().add(Camp.VILLAGE);
		this.description = FR.SOEUR_DESC;
	}
	
	@Override
	public void update(Player player) {
		
		Collection<Entity> entitys = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10);
		for (Entity entity : entitys) {
		    if (entity.getType().equals(EntityType.PLAYER) && !entity.equals(player)) {
		    	Profile p = this.getGame().getProfilesManager().getProfileFromUUID(entity.getUniqueId().toString());
		    	if (!p.getState().getPlayerState().equals(PlayerState.DEAD))
		    	{
			    	if (p.getRole() instanceof RoleSoeur) {
			    		buff(player, PotionEffectType.DAMAGE_RESISTANCE);
			    	}
		    	}
		    }
		}
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
	}

	@Override
	public void resetDay(Player player) {
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
	    	Profile p = this.getGame().getProfilesManager().getProfileFromUUID(((Player)sender).getUniqueId().toString());
	    	if (p.isAlive()) {
				List<String> liste = new ArrayList<String>();
				liste.add("Liste des soeurs : ");
				for (Profile profile : this.getGame().getProfilesManager().getAliveProfiles()) {
					if (profile.getRole() instanceof RoleSoeur) {
						liste.add(ChatColor.WHITE + profile.getPlayer().getName());
					}
				}
				this.getGame().getChatManager().sendPrivateMessage(liste, (Player) sender);
	    	} else {
				sender.sendMessage(ChatColor.RED + "Vous �tes mort(e).");
				return;
	    	}
		}
	}

	@Override
	public void resetPower() {
	}

	@Override
	public void updateDeath(Player player, Profile deadProfile) {
	}
}
