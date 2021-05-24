package me.Verveine.LGUHC.Commands.Player;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Players.Profile;

public class CommandInventory extends PluginCommand {

	public CommandInventory(Main main) {
		super(main);
		this.labels.add("kit");
		this.labels.add("k");
		this.labels.add("inventory");
		this.labels.add("inventaire");
		this.labels.add("objets");
		this.labels.add("items");
		this.labels.add("item");
		this.labels.add("i");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Profile profile = this.getProfile((Player) sender);
		if (profile != null) {
			profile.getPlayer().sendMessage(ChatColor.YELLOW + "Voici votre inventaire de départ");
			profile.getRole().giveStartInventory();
		}
	}

}
