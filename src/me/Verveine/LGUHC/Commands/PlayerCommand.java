package me.Verveine.LGUHC.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;

public abstract class PlayerCommand {

	private Main plugin;
	protected List<String> labels = new ArrayList<String>();
	
	public PlayerCommand(Main main) {
		this.setPlugin(main);
	}
	
	public abstract void onCommand(Player sender, Command cmd, String label, String[] args);
	
	public Profile getProfile(Player sender) {
		return plugin.getGameManager().getGame().getProfilesManager().getProfileFromName(sender.getName());
	}
	
	public GameLG getGame() {
		return this.getPlugin().getGameManager().getGame();	
	}
	
	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}
