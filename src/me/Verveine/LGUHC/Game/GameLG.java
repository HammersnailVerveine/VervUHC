package me.Verveine.LGUHC.Game;

import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.GameState;
import me.Verveine.LGUHC.Managers.ChatManager;
import me.Verveine.LGUHC.Managers.ConfigurationsManager;
import me.Verveine.LGUHC.Managers.GameManager;
import me.Verveine.LGUHC.Managers.GameObjectManager;
import me.Verveine.LGUHC.Managers.GamePermissionsManager;
import me.Verveine.LGUHC.Managers.ProfilesManager;
import me.Verveine.LGUHC.Managers.UpdateManager;
import me.Verveine.LGUHC.Managers.WorldManager;
import me.Verveine.LGUHC.Managers.ConfigUI.MenusManager;

public class GameLG {
	private Main plugin;
	private ChatManager chatManager;
	private UpdateManager updateManager;
	private WorldManager worldManager;
	private ProfilesManager profilesManager;
	private GameObjectManager gameObjectManager;
	private ConfigurationsManager configurationsManager;
	private GamePermissionsManager gamePermissionsManager;
	private MenusManager menusManager;
	private GameState gameState;
	private String hostName;
	private int time;
	
	public GameLG(Main main, Player player, GameManager gameManager) {
		gameManager.setGame(this);
		this.setPlugin(main);
		this.setHostName(player.getName()); // TODO : Retirer dépendance à un player
		this.setGameState(GameState.LOBBY);
		this.setTime(0);
		new ConfigurationsManager(main, this);
		new ChatManager(main, this);
		new UpdateManager(main, this);
		new WorldManager(main, this);
		new ProfilesManager(main, this);
		new GameObjectManager(main, this);
		new GamePermissionsManager(main, this);

		new MenusManager(main, this);
	}
	
	public boolean started() {
		return this.time > 0;
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

	public MenusManager getMenusManager() {
		return menusManager;
	}

	public void setMenusManager(MenusManager menusManager) {
		this.menusManager = menusManager;
	}
}
