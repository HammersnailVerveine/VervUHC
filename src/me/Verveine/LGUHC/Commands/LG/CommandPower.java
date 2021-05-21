package me.Verveine.LGUHC.Commands.LG;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PlayerCommand;
import me.Verveine.LGUHC.Players.Profile;

public class CommandPower extends PlayerCommand {

	public CommandPower(Main main) {
		super(main);
		this.labels.add("pouvoir");
		this.labels.add("power");
		this.labels.add("use");
		this.labels.add("utiliser");
		this.labels.add("u");
	}

	@Override
	public void onCommand(Player sender, Command cmd, String label, String[] args) {
		Profile profile = this.getProfile(sender);
		if (profile != null) {
			profile.getRole().useCommand(sender, cmd, label, args);
		}
	}
}
