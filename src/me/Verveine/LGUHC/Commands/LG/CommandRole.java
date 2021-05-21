package me.Verveine.LGUHC.Commands.LG;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PlayerCommand;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;

public class CommandRole extends PlayerCommand {

	public CommandRole(Main main) {
		super(main);
		this.labels.add("role");
	}
	
	@Override
	public void onCommand(Player sender, Command cmd, String label, String[] args) {
		Profile profile = this.getProfile(sender);
		if (profile != null) {
			Role role = profile.getRole();
			profile.getPlayer().sendMessage(role.getColor() + role.getName());
			profile.getPlayer().sendMessage(role.getDescription());
		}
	}
}
