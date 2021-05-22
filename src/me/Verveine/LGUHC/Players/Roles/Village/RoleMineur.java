package me.Verveine.LGUHC.Players.Roles.Village;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.translations.FR;

public class RoleMineur extends Role {

	public RoleMineur(Main main) {
		super(main);
		this.setColor(ChatColor.AQUA);
		this.setName(FR.MINEUR_ROLE);
		this.camps.add(Camps.VILLAGE);		
		this.description = FR.MINEUR_DESC;
		
		ItemStack	item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta	meta = item.getItemMeta();
		meta.getEnchants().put(Enchantment.DURABILITY, 1);
		meta.getEnchants().put(Enchantment.DIG_SPEED, 1);
		item.setItemMeta(meta);
		
		this.startInventory.add(item);
		this.startInventory.add(new ItemStack(Material.TNT, 3));
	}

	@Override
	public void updateStart(Player player) {
	}

	@Override
	public void updateDay(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 1, false, false));
	}

	@Override
	public void updateNight(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 1, false, false));
	}

	@Override
	public void resetDay(Player player) {
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("test");
	}

}