package me.Verveine.LGUHC.Commands.LG;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PlayerCommand;
import me.Verveine.LGUHC.Players.Profile;

public class CommandInventory extends PlayerCommand {

	public CommandInventory(Main main) {
		super(main);
		this.labels.add("inventory");
		this.labels.add("inventaire");
		this.labels.add("objets");
		this.labels.add("items");
		this.labels.add("item");
		this.labels.add("i");
		this.labels.add("kit");
		this.labels.add("k");
	}

	@Override
	public void onCommand(Player sender, Command cmd, String label, String[] args) {
		Profile profile = this.getProfile(sender);
		if (profile != null) {
			profile.getPlayer().sendMessage(ChatColor.YELLOW + "Voici votre inventaire de départ");
			profile.getRole().giveStartInventory();
		}
	}

}
