package me.Verveine.LGUHC.Game;

import org.bukkit.World;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Managers.ChatManager;
import me.Verveine.LGUHC.Managers.ConfigurationsManager;
import me.Verveine.LGUHC.Managers.GameObjectManager;
import me.Verveine.LGUHC.Managers.GamePermissionsManager;
import me.Verveine.LGUHC.Managers.ProfilesManager;
import me.Verveine.LGUHC.Managers.UpdateManager;
import me.Verveine.LGUHC.Managers.WorldManager;

public class GameLG {
	private Main plugin;
	private ChatManager chatManager;
	private UpdateManager updateManager;
	private WorldManager worldManager;
	private ProfilesManager profilesManager;
	private GameObjectManager gameObjectManager;
	private ConfigurationsManager configurationsManager;
	private GamePermissionsManager gamePermissionsManager;
	private World world;
	private GameState gameState;
	private String hostName;
	private int time;
	
	public GameLG(Main main, Player player) {
		this.setPlugin(main);
		this.setHostName(player.getName());
		this.setGameState(GameState.LOBBY);
		this.setWorld(player.getWorld());
		this.setTime(0);
		this.setConfigurationsManager(new ConfigurationsManager(main, this));
		this.setChatManager(new ChatManager(main, this));
		this.setUpdateManager(new UpdateManager(main, this));
		this.setWorldManager(new WorldManager(main, this));
		this.setProfilesManager(new ProfilesManager(main, this));
		this.setGameObjectManager(new GameObjectManager(main, this));
		this.setGamePermissionsManager(new GamePermissionsManager(main, this));
	}
	
	public boolean started() {
		return this.time > 0;
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

	public GameObjectManager getGameObjectManager() {
		return gameObjectManager;
	}

	public void setGameObjectManager(GameObjectManager gameObjectManager) {
		this.gameObjectManager = gameObjectManager;
	}

	public ConfigurationsManager getConfigurationsManager() {
		return configurationsManager;
	}

	public void setConfigurationsManager(ConfigurationsManager configurationsManager) {
		this.configurationsManager = configurationsManager;
	}

	public GamePermissionsManager getGamePermissionsManager() {
		return gamePermissionsManager;
	}

	public void setGamePermissionsManager(GamePermissionsManager gamePermissionsManager) {
		this.gamePermissionsManager = gamePermissionsManager;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
