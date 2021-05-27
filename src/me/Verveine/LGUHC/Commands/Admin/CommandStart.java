package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Game.GameLG;

public class CommandStart extends PluginCommand {

	public CommandStart(Main main) {
		super(main);
		this.labels.add("start");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		GameLG game = this.getGame();
		switch (game.getGameState()) {
			case LOBBY:
				this.getGame().getChatManager().sendSystemMessage("La partie commence.");
				break;
			case ENDED:
				this.getGame().getChatManager().sendSystemMessage("Partie relancée.");
				break;
			default:
				this.getGame().getChatManager().sendSystemMessage("La partie est déjà en cours.");
				break;
		}	
		this.getPlugin().getGameManager().startGame();
	}
}
