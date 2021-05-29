package me.Verveine.LGUHC.Players.Roles.Village.Info;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleVoyante extends Role {
	
	private boolean canUsePower;
	private boolean canUsePowerTomorrow;

	public RoleVoyante(Main main) {
		super(main);
		this.setColor(ChatColor.AQUA);
		this.setName(FR.VOYANTE_ROLE);
		this.camps.add(Camp.VILLAGE);		
		this.getCampsDescription().add(Camp.VILLAGE);
		this.description = FR.VOYANTE_DESC;
		this.canUsePower = true;
		this.setCanUsePowerTomorrow(true);
		
		this.startInventory.add(new ItemStack(Material.OBSIDIAN, 4));
		this.startInventory.add(new ItemStack(Material.BOOKSHELF, 4));
	}

	@Override
	public String getScoreboardLine() { // ✓✔✕✖
		if (this.isCanUsePower()) {
			return "Pouvoir : " + ChatColor.GREEN + "✔" + ChatColor.AQUA;
		} else {
			return "Pouvoir : " + ChatColor.RED + "✕" + ChatColor.AQUA; 
		}
	}
	
	@Override
	public void update(Player player) {
		buff(player, PotionEffectType.SPEED);
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
	}

	@Override
	public void resetDay(Player player) {
		if (this.isCanUsePowerTomorrow()) {
			this.getGame().getChatManager().sendPrivateMessage("Vous pouvez inspecter un joueur avec </lg use pseudo>.", player);
			this.setCanUsePower(true);
		} else {
			this.getGame().getChatManager().sendPrivateMessage("Vous ne pouvez pas inspecter de joueur aujourd'hui.", player);
			this.setCanUsePowerTomorrow(true);
		}
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
	    	Profile p = this.getGame().getProfilesManager().getProfileFromUUID(((Player)sender).getUniqueId().toString());
	    	if (p.isAlive()) {
				if (this.isCanUsePower()) {
					if (args.length == 2) {
						Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
						if (profile != null) {
								List<String> list = new ArrayList<String>();
								list.add("Le joueur " + profile.getPlayer().getName() + ChatColor.YELLOW + " est " + profile.getRole().getColor() + profile.getRole().getName() + ChatColor.WHITE + ".");
								this.setCanUsePower(false);
								if (profile.getRole().getTags().contains(Tags.WOLF)) {
									list.add(ChatColor.RED + "Il est dans le camp des loups.");
									list.add(ChatColor.RED + "Vous subissez un coeur de dégâts.");
									player.setHealth(player.getHealth() - 2);
								} else if (profile.getRole().getTags().contains(Tags.UNCERTAIN) || profile.getRole().getTags().contains(Tags.DANGEROUS)) {
									list.add(ChatColor.GOLD + "Son camp est dangereux ou incertain.");
									list.add(ChatColor.RED + "Vous subissez deux coeurs de dégâts.");
									player.setHealth(player.getHealth() - 4);
								} else {
									list.add(ChatColor.GOLD + "Il est dans le village.");
									list.add(ChatColor.RED + "Vous subissez deux coeur de dégâts et perdez un coeur permanent.");
									player.setMaxHealth(player.getMaxHealth() - 2);
									player.setHealth(player.getHealth() - 4);
									this.canUsePowerTomorrow = false;
								}
								this.getGame().getChatManager().sendPrivateMessage(list, (Player) sender);			
							if(player.getHealth() > 2.0) {
							} else {
								sender.sendMessage(ChatColor.RED + "Je suis pour la sélection naturelle, mais là, tu as un seul coeur.");
								return;
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Le profil du joueur " + args[1] + " est introuvable.");
							return;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Usage : /lg use pseudo.");
						return;
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Vous ne pouvez inspecter un joueur maintenant.");
					return;
				}
	    	} else {
				sender.sendMessage(ChatColor.RED + "Vous êtes mort(e).");
				return;
	    	}
		}
	}

	@Override
	public void resetPower() {
		this.canUsePower = true;
		this.setCanUsePowerTomorrow(true);
	}

	public boolean isCanUsePower() {
		return canUsePower;
	}

	public void setCanUsePower(boolean canUsePower) {
		this.canUsePower = canUsePower;
	}
	@Override
	public void updateDeath(Player player, Profile deadProfile) {
	}

	public boolean isCanUsePowerTomorrow() {
		return canUsePowerTomorrow;
	}

	public void setCanUsePowerTomorrow(boolean canUsePowerTomorrow) {
		this.canUsePowerTomorrow = canUsePowerTomorrow;
	}
}
