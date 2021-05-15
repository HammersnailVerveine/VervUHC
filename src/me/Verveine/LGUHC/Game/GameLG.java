package me.Verveine.LGUHC.Game;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Statistics;
import me.Verveine.LGUHC.Players.Roles.*;
import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Managers.ChatManager;
import me.Verveine.LGUHC.Managers.UpdateManager;

public class GameLG {
	private Main plugin;
	private ChatManager chatManager;
	private UpdateManager updateManager;
	private ArrayList<Profile> profiles;
	
	public GameLG(Main main) {
		this.setPlugin(main);
		this.setChatManager(new ChatManager(main));
		this.setUpdateManager(new UpdateManager(main));
		profiles = new ArrayList<Profile>();
	}

	public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
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
		
		if (profile != null) {
			profile.setPlayer(player);
			chatManager.sendSystemMessage("Profil " + playerName + " actualisé");
		} else {
			profile = new Profile(player, new RoleBlank(), new Statistics());
			this.profiles.add(profile);
			chatManager.sendSystemMessage("Profil " + playerName + " ajouté");
		}
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public ChatManager getChatManager() {
		return chatManager;
	}

	public void setChatManager(ChatManager chatManager) {
		this.chatManager = chatManager;
	}

	public void update() {
		updateManager.updateProfiles(profiles);
	}

	public UpdateManager getUpdateManager() {
		return updateManager;
	}

	public void setUpdateManager(UpdateManager updateManager) {
		this.updateManager = updateManager;
	}
}
