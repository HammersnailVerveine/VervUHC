package me.Verveine.LGUHC.Players.Roles;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Players.Role;

public class RoleBlank extends Role {

	@Override
	public void setDefaults() {
		this.setColor(ChatColor.WHITE);
		this.setName("Aucun Role");	
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
	}
}
