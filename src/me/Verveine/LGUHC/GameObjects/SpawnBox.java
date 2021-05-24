package me.Verveine.LGUHC.GameObjects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Managers.Game.GameObjectManager;

public class SpawnBox extends GameObject {
	private boolean visible;
	
	public SpawnBox(Main plugin, GameObjectManager manager, boolean visible) {
		super(plugin);
		manager.setSpawnBox(this);
		this.visible = visible;
	}
	
	public void CreateFromPlayer(Player player) {
		Location location = player.getLocation();
		location.setY(150);
		this.location = location;
		this.create();
	}
	
	public void create() { //TODO : supprimer l'ancienne box
		if (location != null) {
			Location ref = this.location.clone();
			ref.setY(ref.getY() - 3);
			ref.setX(ref.getX() - 10);
			ref.setZ(ref.getZ() - 10);
			
			for (int i = 0 ; i < 20 ; i ++) {
				ref.setX(ref.getX() + 1);
				for (int j = 0 ; j < 20 ; j ++) {
					ref.setZ(ref.getZ() + 1);
					if ((i == 0 || j == 0 || i == 19 || j == 19) && this.visible) {
						ref.getBlock().setType(Material.STAINED_GLASS);
					} else {
						ref.getBlock().setType(Material.BARRIER);
					}
				}
				ref.setZ(ref.getZ() - 20);
			}
			ref.setX(ref.getX() - 20);
			for (int k = 0 ; k < 5 ; k ++) {
				ref.setY(ref.getY() + 1);
				for (int i = 0 ; i < 20 ; i ++) {
					ref.setX(ref.getX() + 1);
					for (int j = 0 ; j < 20 ; j ++) {
						ref.setZ(ref.getZ() + 1);
						if (i == 0 || j == 0 || i == 19 || j == 19) {
							if ((i == j || i == j - 19 || j == i - 19) && this.visible) {
								ref.getBlock().setType(Material.STAINED_GLASS);
							} else {
								ref.getBlock().setType(Material.BARRIER);
							}
						}
					}
					ref.setZ(ref.getZ() - 20);
				}
				ref.setX(ref.getX() - 20);
			}
			ref.setY(ref.getY() + 1);
			for (int i = 0 ; i < 20 ; i ++) {
				ref.setX(ref.getX() + 1);
				for (int j = 0 ; j < 20 ; j ++) {
					ref.setZ(ref.getZ() + 1);
					if ((i == 0 || j == 0 || i == 19 || j == 19) && this.visible) {
						ref.getBlock().setType(Material.STAINED_GLASS);
					} else {
						ref.getBlock().setType(Material.BARRIER);
					}
				}
				ref.setZ(ref.getZ() - 20);
			}
		}
		this.created = true;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
