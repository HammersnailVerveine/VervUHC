package me.Verveine.LGUHC.Players.Roles;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;

public class RoleBlank extends Role {

	public RoleBlank(Main main) {
		super(main);
		this.setColor(ChatColor.WHITE);
		this.setName("Aucun Role");	
		this.description = "Vous n'avez pas de rôle. Vous ne devez pas gagner. C'est bête. Peut être pourriez vous attendre d'en avoir un, ou blâmer Verveine pour s'être planté quelque part?";
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
