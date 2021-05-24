package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;

public class CommandStart extends PluginCommand {

	public CommandStart(Main main) {
		super(main);
		this.labels.add("start");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		this.getPlugin().getGameManager().startGame();
		this.getPlugin().getGameManager().getGame().getChatManager().sendSystemMessage("La partie commence");
	}
}
