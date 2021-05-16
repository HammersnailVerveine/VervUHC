package me.Verveine.LGUHC.Game;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Statistics;
import me.Verveine.LGUHC.Players.Roles.*;
import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Managers.ChatManager;
import me.Verveine.LGUHC.Managers.UpdateManager;
import me.Verveine.LGUHC.Managers.WorldManager;

public class GameLG {
	private Main plugin;
	private ChatManager chatManager;
	private UpdateManager updateManager;
	private WorldManager worldManager;
	private ArrayList<Profile> profiles;
	private GameState gameState;
	private String hostName;
	private int time;
	
	public GameLG(Main main, Player player) {
		this.setPlugin(main);
		this.setChatManager(new ChatManager(main));
		this.setUpdateManager(new UpdateManager(main, this));
		this.setWorldManager(new WorldManager(main, player.getWorld()));
		this.setHostName(player.getName());
		this.setGameState(GameState.LOBBY);
		this.setTime(0);
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

	public UpdateManager getUpdateManager() {
		return updateManager;
	}

	public void setUpdateManager(UpdateManager updateManager) {
		this.updateManager = updateManager;
	}

	public WorldManager getWorldManager() {
		return worldManager;
	}

	public void setWorldManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void update() {
		this.updateManager.update();
	}
}
