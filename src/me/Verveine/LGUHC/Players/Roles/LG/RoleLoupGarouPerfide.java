package me.Verveine.LGUHC.Players.Roles.LG;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleLoupGarouPerfide extends Role {

	public RoleLoupGarouPerfide(Main main) {
		super(main);
		this.setColor(ChatColor.RED);
		this.setName(FR.LGP_ROLE);
		this.camps.add(Camp.WOLF);
		this.getCampsDescription().add(Camp.WOLF);
		this.tags.add(Tags.WOLF);
		this.appearsOnWolfList = true;
		this.accessWolfList = true;
		this.description = FR.LGP_DESC;
	}
	
	@Override
	public void update(Player player) {
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
		if (player.getInventory().getHelmet() == null
		&& player.getInventory().getChestplate() == null
		&& player.getInventory().getLeggings() == null
		&& player.getInventory().getBoots() == null) {
			buff(player, PotionEffectType.INVISIBILITY);
			buff(player, PotionEffectType.WEAKNESS, 1);
		} else {
			buff(player, PotionEffectType.INCREASE_DAMAGE);
		}
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

	@Override
	public void resetPower() {
	}

	@Override
	public void updateDeath(Player player, Profile deadProfile) {
	}
}
