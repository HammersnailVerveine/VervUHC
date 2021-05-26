package me.Verveine.LGUHC.Players.Roles.Village;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleMineur extends Role {

	public RoleMineur(Main main) {
		super(main);
		this.setColor(ChatColor.GREEN);
		this.setName(FR.MINEUR_ROLE);
		this.camps.add(Camp.VILLAGE);
		this.getCampsDescription().add(Camp.VILLAGE);
		this.description = FR.MINEUR_DESC;
		
		ItemStack	item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta	meta = item.getItemMeta();
		meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		item.setItemMeta(meta);
		
		this.startInventory.add(item);
		this.startInventory.add(new ItemStack(Material.TNT, 3));
	}

	@Override
	public void setupStart(Player player) {
	}
	
	@Override
	public void update(Player player) {
		buff(player, PotionEffectType.FAST_DIGGING, 1);
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
	}
}
