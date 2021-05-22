package me.Verveine.LGUHC.Players.Roles.Village;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleSimpleVillageois extends Role {

	public RoleSimpleVillageois(Main main) {
		super(main);
		this.setColor(ChatColor.GREEN);
		this.setName(FR.SV_ROLE);
		this.camps.add(Camps.VILLAGE);		
		this.description = FR.SV_DESC;
		
		this.startInventory.add(new ItemStack(Material.CARROT_ITEM, 1));
	}

	@Override
	public void updateStart(Player player) {
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
		sender.sendMessage("test");
	}

}
