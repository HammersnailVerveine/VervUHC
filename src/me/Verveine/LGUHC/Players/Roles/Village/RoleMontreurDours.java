package me.Verveine.LGUHC.Players.Roles.Village;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleMontreurDours extends Role {

	public RoleMontreurDours(Main main) {
		super(main);
		this.setColor(ChatColor.AQUA);
		this.setName(FR.OURS_ROLE);
		this.camps.add(Camps.VILLAGE);		
		this.getCampsDescription().add(Camps.VILLAGE);
		this.description = FR.OURS_DESC;
	}

	@Override
	public void setupStart(Player player) {
	}
	
	@Override
	public void update(Player player) {
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
	}

	@Override
	public void resetDay(Player player) {
		Bukkit.broadcastMessage(ChatColor.YELLOW + "L'ours du Montreur d'Ours se réveille.");
		Collection<Entity> entitys = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 30, 30, 30);
		for (Entity entity : entitys) {
		    if (entity.getType().equals(EntityType.PLAYER)) {
		    	if (this.getGame().getProfilesManager().getProfileFromUUID(entity.getUniqueId().toString()).getRole().getTags().contains(Tags.WOLF)) {
		    		Bukkit.broadcastMessage(ChatColor.YELLOW + "'Grrrrrrr'");
		    	}
		    }
		}
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("test");
	}

}
