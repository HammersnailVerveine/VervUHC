package me.Verveine.LGUHC.Players.Roles.Solo;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.translations.FR;

public class RoleAssassin extends Role {

	public RoleAssassin(Main main) {
		super(main);
		this.setColor(ChatColor.GOLD);
		this.setName(FR.ASSASSIN_ROLE);
		this.description = FR.ASSASSIN_DESC;
		
		ItemStack book1 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book1.getItemMeta();
		meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 3, true);
		book1.setItemMeta((ItemMeta)meta);
		
		ItemStack book2 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		meta = (EnchantmentStorageMeta)book2.getItemMeta();
		meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		book2.setItemMeta((ItemMeta)meta);
		
		ItemStack book3 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		meta = (EnchantmentStorageMeta)book3.getItemMeta();
		meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 3, true);
		book3.setItemMeta((ItemMeta)meta);
		
		this.startInventory.add(book1);
		this.startInventory.add(book2);
		this.startInventory.add(book3);

	}

	@Override
	public void updateStart(Player player) {
	}

	@Override
	public void updateDay(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, false, false));
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
		sender.sendMessage("test");
	}

}