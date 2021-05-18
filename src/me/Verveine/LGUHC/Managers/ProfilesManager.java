package me.Verveine.LGUHC.Managers;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Statistics;
import me.Verveine.LGUHC.Players.Roles.RoleBlank;

public class ProfilesManager extends InternalManager {
	private ArrayList<Profile> profiles;
	
	public ProfilesManager(Main main, GameLG game) {
		super(main, game);
		this.profiles = new ArrayList<Profile>();
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
			chatManager.sendSystemMessage("Profil " + playerName + " actualisé");
		} else {
			profile = new Profile(player, new RoleBlank(), new Statistics());
			this.profiles.add(profile);
			chatManager.sendSystemMessage("Profil " + playerName + " ajouté");
			
			if (!game.started()) {
				player.teleport(game.getGameObjectManager().getSpawnBox().getLocation());
			}
		}
	}

	public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}
}
