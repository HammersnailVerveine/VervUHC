package me.Verveine.LGUHC.Game;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Managers.ChatManager;
import me.Verveine.LGUHC.Managers.ProfilesManager;
import me.Verveine.LGUHC.Managers.UpdateManager;
import me.Verveine.LGUHC.Managers.WorldManager;

public class GameLG {
	private Main plugin;
	private ChatManager chatManager;
	private UpdateManager updateManager;
	private WorldManager worldManager;
	private ProfilesManager profilesManager;
	private ArrayList<Profile> profiles;
	private GameState gameState;
	private String hostName;
	private int time;
	
	public GameLG(Main main, Player player) {
		this.setPlugin(main);
		this.setChatManager(new ChatManager(main));
		this.setUpdateManager(new UpdateManager(main));
		this.setWorldManager(new WorldManager(main, player.getWorld()));
		this.setProfilesManager(new ProfilesManager(main));
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
	
	public void updateProfiles(Player player) {
		this.profilesManager.updateProfiles(player);
	}

	public void update() {
		this.updateManager.update();
	}
	
	// Getters & Setters //
	
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

	public ProfilesManager getProfilesManager() {
		return profilesManager;
	}

	public void setProfilesManager(ProfilesManager profilesManager) {
		this.profilesManager = profilesManager;
	}
}
