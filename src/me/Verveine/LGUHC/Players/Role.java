package me.Verveine.LGUHC.Players;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Game.GameLG;

public abstract class Role implements Cloneable {
	private Main plugin;
	private boolean infected = false;
	protected List<Camp> camps;
	protected List<Tags> tags;
	protected List<ItemStack> startInventory;
	protected List<Camp> campsDescription;
	protected boolean appearsOnWolfList;
	protected boolean accessWolfList;
	protected String name;
	protected ChatColor color;
	protected String description;
	
	public Role(Main main) {
		this.setPlugin(main);
		this.camps = new ArrayList<Camp>();
		this.tags = new ArrayList<Tags>();
		this.startInventory = new ArrayList<ItemStack>();
		this.setCampsDescription(new ArrayList<Camp>());
		appearsOnWolfList = false;
		name = "Blank Name";
		color = ChatColor.WHITE;
		description = "Blank Description";
	}
	
	public abstract void update(Player player);
	public abstract void updateDay(Player player);
	public abstract void updateNight(Player player);
	public abstract void resetDay(Player player);
	public abstract void resetNight(Player player);
	public abstract void useCommand(CommandSender sender, Command cmd, String label, String[] args);
	public abstract void resetPower();
	public abstract void updateDeath(Player player, Profile deadProfile);
	
	public void obtain(Player player) {
		player.setMaxHealth(20);
		
		if (this.getStartInventory().size() > 0) {
			this.giveStartInventory();
		}
	}
	
	public String getScoreboardLine() {
		return null;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public GameLG getGame() {
		return this.getPlugin().getGameManager().getGame();
	}
	
	public ArrayList<String> campsDescriptionToStringList() {
		ArrayList<String> camps = new ArrayList<String>();
		for (Camp camp : this.getCamps()) {
			camps.add(camp.toChatColor() + camp.toString());
		}
		return camps;
	}
	
	public void giveStartInventory() {
		for (Profile profile : this.getGame().getProfilesManager().getProfiles()) {
			if (profile.getRole() == this) {
				Player player = profile.getPlayer();
				if (player.isOnline()) {
					if (startInventory.size() < 1) {
						this.getGame().getChatManager().sendPrivateMessage("Tu as déjà reçu tous les objets de ton inventaire de départ", player);
					} else {
						ArrayList<ItemStack> givenItems = new ArrayList<ItemStack>();
						for (ItemStack item : startInventory) {
							if (player.getInventory().firstEmpty() == -1) {
								this.getGame().getChatManager().sendPrivateMessage("Ton inventaire est plein, utilise la commande </lg i> pour recevoir les objets restants.", player);
							} else {
								player.getInventory().addItem(item);
								givenItems.add(item);
							}
						}
						
						for (ItemStack item : givenItems) {
							startInventory.remove(item);
						}
						givenItems.clear();
					}
				}
				return;
			}
		}
	}

	public void buff(Player player, PotionEffectType potionEffect) {
		buff(player, potionEffect, 0);
	}

	public void buff(Player player, PotionEffectType potionEffect, int level) {
		player.removePotionEffect(potionEffect);
		player.addPotionEffect(new PotionEffect(potionEffect, 40, level, false, false));
	}
	
	public List<Camp> getCamps() {
		return camps;
	}

	public void setCamps(List<Camp> camps) {
		this.camps = camps;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public boolean isAppearsOnWolfList() {
		return appearsOnWolfList;
	}

	public void setAppearsOnWolfList(boolean appearsOnWolfList) {
		this.appearsOnWolfList = appearsOnWolfList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}

	public List<ItemStack> getStartInventory() {
		return startInventory;
	}

	public void setStartInventory(List<ItemStack> startInventory) {
		this.startInventory = startInventory;
	}

	public Main getPlugin() {
		return plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public boolean isAccessWolfList() {
		return accessWolfList;
	}

	public void setAccessWolfList(boolean accessWolfList) {
		this.accessWolfList = accessWolfList;
	}

	public List<Camp> getCampsDescription() {
		return campsDescription;
	}

	public void setCampsDescription(List<Camp> campsDescription) {
		this.campsDescription = campsDescription;
	}

	public boolean isInfected() {
		return infected;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}
}
