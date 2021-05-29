package me.Verveine.LGUHC.GameObjects;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.block.BlockBreakEvent;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Managers.Game.ChatManager;
import me.Verveine.LGUHC.Managers.Game.GameObjectManager;
import me.Verveine.LGUHC.Players.Profile;
import net.md_5.bungee.api.ChatColor;

public class Indice extends GameObject {
	
	private Profile revealedProfile;
	private boolean activated;
	
	public Indice(Main main, GameObjectManager manager) {
		super(main);
		manager.getIndices().add(this);
		this.activated = false;
	}

	public Profile getRevealedProfile() {
		return revealedProfile;
	}

	public void setRevealedProfile(Profile revealedProfile) {
		this.revealedProfile = revealedProfile;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public void revealWolf(BlockBreakEvent event) {
		this.activated = true;
		this.setRevealedProfile(chooseRevealedProfile());
		ChatManager chatManager = this.getGame().getChatManager();
		if (this.getRevealedProfile() != null) {
			chatManager.sendPrivateMessage("Le joueur " + ChatColor.WHITE + this.getRevealedProfile().getPlayer().getName() + ChatColor.YELLOW + " est un loup.", event.getPlayer());
		} else {
			chatManager.sendPrivateMessage("Il n'y a aucun loup en vie", event.getPlayer());
		}
	}
	
	private Profile chooseRevealedProfile() {
		List<Profile> wolves = new ArrayList<Profile>();
		for (Profile profile : this.getGame().getProfilesManager().getAliveProfiles()) {
			if (profile.getRole().getTags().contains(Tags.WOLF)) {
				wolves.add(profile);
			}
		}
		if (wolves.size() > 0) {
		    Random rand = new Random();
			Profile profile = wolves.get(rand.nextInt(wolves.size()));
			return profile;
		} else {
			return null;
		}
	}

	@Override
	public void create() {
		Location tempLocation = this.location.clone();
		tempLocation.setX(tempLocation.getX() - 1);
		tempLocation.setY(tempLocation.getY() - 1);
		tempLocation.setZ(tempLocation.getZ() - 1);
		
		for (int i = 0 ; i < 3 ; i ++) {
			for (int j = 0 ; j < 3 ; j ++) {
				for (int k = 0 ; k < 3 ; k ++) {
					Material material = Material.OBSIDIAN;
					if (i % 2 == 0 && j % 2 == 0 && k % 2 == 0)
						material = Material.REDSTONE_BLOCK;
					if (i == 1 && j == 1 && k == 1) {
						material = Material.GOLD_BLOCK;
						this.setLocation(tempLocation.clone());
					}
					tempLocation.getBlock().setType(material);
					tempLocation.setX(tempLocation.getX() + 1);
				}
				tempLocation.setY(tempLocation.getY() + 1);
				tempLocation.setX(tempLocation.getX() - 3);
			}
			tempLocation.setZ(tempLocation.getZ() + 1);
			tempLocation.setY(tempLocation.getY() - 3);
		}
		this.created = true;
	}
	
	public Location generateLocation() {
		List<Material> resetMaterials = new ArrayList<Material>();
		resetMaterials.add(Material.STATIONARY_WATER);
		resetMaterials.add(Material.STATIONARY_LAVA);
		resetMaterials.add(Material.STAINED_GLASS);
		resetMaterials.add(Material.COBBLESTONE);
		resetMaterials.add(Material.LEAVES_2);
		resetMaterials.add(Material.BARRIER);
		resetMaterials.add(Material.LEAVES);
		resetMaterials.add(Material.WATER);
		resetMaterials.add(Material.LOG_2);
		resetMaterials.add(Material.LAVA);
		resetMaterials.add(Material.LOG);
		World world = this.getGame().getWorldManager().getWorld();
		double borderSize = world.getWorldBorder().getSize();
		
		Location tempLocation = world.getWorldBorder().getCenter().getBlock().getLocation();		
		tempLocation.setX(Math.random() * (borderSize) - (borderSize / 2) + world.getWorldBorder().getCenter().getX());
		tempLocation.setZ(Math.random() * (borderSize) - (borderSize / 2) + world.getWorldBorder().getCenter().getZ());
		
		int height = 255;
		tempLocation.setY(height);
		
		while (!(tempLocation.getBlock().getType().isSolid())) {
			height --;
			tempLocation.setY(height);

			if (resetMaterials.contains(tempLocation.getBlock().getType()) || height < 10) {
				return generateLocation();
			} else if ((tempLocation.getBlock().getType().isSolid())) {
				tempLocation.setY(tempLocation.getY() + 2);
				return (tempLocation);
			}
		}
		return generateLocation();
	}
}
