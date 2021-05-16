package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Statistics;
import me.Verveine.LGUHC.Players.Roles.RoleBlank;

public class ProfilesManager {
	private ArrayList<Profile> profiles;
	private GameLG game;
	private Main plugin;
	
	public ProfilesManager(Main main) {
		setPlugin(main);
		setGame(main.getGame());
		setProfiles(main.getGame().getProfiles());
	}
	
	private Profile getProfileFromName(String name) {
		Profile profile = null;
		
		for (Profile p : this.profiles) {
			if (name.equalsIgnoreCase(p.getPlayer().getName())) {
				profile = p;
				break;
			}
		}
		
		return profile;
	}
	
	public void updateProfiles(Player player) {
		String playerName = player.getName();
		Profile profile = getProfileFromName(playerName);
		ChatManager chatManager = game.getChatManager();
		
		if (profile != null) {
			profile.setPlayer(player);
			chatManager.sendSystemMessage("Profil " + playerName + " actualis�");
		} else {
			profile = new Profile(player, new RoleBlank(), new Statistics());
			this.profiles.add(profile);
			chatManager.sendSystemMessage("Profil " + playerName + " ajout�");
		}
	}

	public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
}