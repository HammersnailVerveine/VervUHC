package me.Verveine.LGUHC.Players.Roles.Village.Info;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RolePetiteFille extends Role {

	public RolePetiteFille(Main main) {
		super(main);
		this.setColor(ChatColor.AQUA);
		this.setName(FR.PF_ROLE);
		this.camps.add(Camp.VILLAGE);		
		this.getCampsDescription().add(Camp.VILLAGE);
		this.description = FR.PF_DESC;
		
		this.startInventory.add(new ItemStack(Material.TNT, 5));
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
