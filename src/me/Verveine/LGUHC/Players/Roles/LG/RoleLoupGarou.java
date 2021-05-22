package me.Verveine.LGUHC.Players.Roles.LG;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.translations.FR;

public class RoleLoupGarou extends Role {

	public RoleLoupGarou(Main main) {
		super(main);
		this.setColor(ChatColor.RED);
		this.setName(FR.LG_ROLE);
		this.camps.add(Camps.WOLF);	
		this.tags.add(Tags.WOLF);
		this.appearsOnWolfList = true;
		this.description = FR.LG_DESC;
	}
	
	@Override
	public void updateStart(Player player) {
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, false, false));
	}

	@Override
	public void resetDay(Player player) {
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
	}
}
