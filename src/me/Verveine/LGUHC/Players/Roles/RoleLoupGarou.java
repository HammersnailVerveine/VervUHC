package me.Verveine.LGUHC.Players.Roles;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Role;

public class RoleLoupGarou extends Role {

	@Override
	public void setDefaults() {
		this.setColor(ChatColor.RED);
		this.setName("Loup Garou");
		this.camps.add(Camps.WOLF);	
		this.tags.add(Tags.WOLF);
		this.appearsOnWolfList = true;
	}

	@Override
	public void updateStart(Player player) {
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0, false, false));
	}

	@Override
	public void resetDay(Player player) {
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(Player player) {
	}
}
