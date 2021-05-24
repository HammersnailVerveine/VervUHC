package me.Verveine.LGUHC.Commands.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Players.Profile;

public class CommandPower extends PluginCommand {

	public CommandPower(Main main) {
		super(main);
		this.labels.add("use");
		this.labels.add("u");
		this.labels.add("pouvoir");
		this.labels.add("power");
		this.labels.add("p");
		this.labels.add("utiliser");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Profile profile = this.getProfile((Player) sender);
		if (profile != null) {
			profile.getRole().useCommand(sender, cmd, label, args);
		}
	}
}
