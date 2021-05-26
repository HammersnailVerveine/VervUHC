package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;

public class CommandIndice extends PluginCommand {

	public CommandIndice(Main main) {
		super(main);
		this.labels.add("indice");
		this.labels.add("hint");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		this.getGame().getGameObjectManager().generateIndice();
		this.getGame().getChatManager().sendGameMessage("Un indice est apparu.");
	}
}
