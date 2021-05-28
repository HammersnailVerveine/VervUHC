package me.Verveine.LGUHC.Managers.Game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Players.Statistics;
import me.Verveine.LGUHC.Players.Roles.RoleBlank;
import me.Verveine.LGUHC.Players.Roles.Village.RoleSimpleVillageois;

public class ProfilesManager extends InternalManager {
	private ArrayList<Profile> profiles;
	
	public ProfilesManager(Main main, GameLG game) {
		super(main);
		game.setProfilesManager(this);
		this.profiles = new ArrayList<Profile>();
	}
	
	public Profile getProfileFromName(String name) {
		Profile profile = null;
		
		for (Profile p : this.profiles) {
			if (name.equalsIgnoreCase(p.getPlayer().getName())) {
				profile = p;
				break;
			}
		}
		
		return profile;
	}
	
	public Profile getProfileFromUUID(String uuid) {
		Profile profile = null;
		
		for (Profile p : this.profiles) {
			if (uuid.equalsIgnoreCase(p.getPlayer().getUniqueId().toString())) {
				profile = p;
				break;
			}
		}

		return profile;
	}
	
	public void updateProfiles(Player player) {
		GameLG game = this.getGame();
		String playerName = player.getName();
		Profile profile = getProfileFromName(playerName);
		ChatManager chatManager = game.getChatManager();
		
		if (profile != null) {
			profile.setPlayer(player);
			player.setScoreboard(profile.getScoreboard());
			chatManager.sendSystemMessage("Profil " + playerName + " actualisé");
		} else {
			profile = new Profile(plugin, player, new RoleBlank(this.getPlugin()), new Statistics());
			this.profiles.add(profile);
			chatManager.sendSystemMessage("Profil " + playerName + " ajouté");
			
			if (!game.started()) {
				player.teleport(game.getGameObjectManager().getSpawnBox().getLocation());
				player.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
	
	public void giveRoles() {
		GameLG game = this.getGame();
		ArrayList<Role> roles = new ArrayList<Role>();
		ConfigurationsManager configurationsManager = this.getGame().getConfigurationsManager();
		
		int nbProfils = 0;
		int nbConfigRoles = configurationsManager.countRoles();

		for (Profile profile : this.getProfiles()) {
			if (!profile.getState().getPlayerState().equals(PlayerState.SPECTATOR)) {
				nbProfils ++;
			}
		}
		
		if (nbConfigRoles < nbProfils) {
			game.getChatManager().sendSystemMessage("Pas assez de Rôles, " + (nbProfils - nbConfigRoles) + " Villageois a/ont été ajouté(s) à la pool de rôles.");
			for (int i = 0 ; i < nbProfils - nbConfigRoles ; i ++) {
				roles.add(new RoleSimpleVillageois(this.getPlugin()));
			}
		}
		
		for (ConfigurationRole configRole : configurationsManager.getConfigurationRoles()) {
			for (int i = 0 ; i < configRole.getAmount() ; i ++) {
				Role role = configRole.getRole();
				Class<?> clazz;
				try {
					clazz = Class.forName(role.getClass().getName());
					Constructor<?> ctor;
					try {
						ctor = clazz.getConstructor(Main.class);
						Object object;
						try {
							object = ctor.newInstance(new Object[] { this.getPlugin() });
							roles.add((Role)object);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Profile profile : this.getProfiles()) {
		    Random rand = new Random();
			Role role = roles.get(rand.nextInt(roles.size()));
			profile.setRole(role);
			if (role.getStartInventory().size() > 0) {
				role.giveStartInventory();
			}
			game.getChatManager().sendProfileRole(profile);
			roles.remove(role);
		}
	}

	public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}
}
