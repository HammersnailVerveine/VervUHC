package me.Verveine.LGUHC.Players.Roles.Village.Info;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;

public class RoleRenard extends Role {
	
	private boolean canUsePower;
	private Profile trackedProfile;
	private int trackingProgress;
	private int powerUses;

	public RoleRenard(Main main) {
		super(main);
		this.setColor(ChatColor.AQUA);
		this.setName(FR.RENARD_ROLE);
		this.camps.add(Camp.VILLAGE);		
		this.getCampsDescription().add(Camp.VILLAGE);
		this.description = FR.RENARD_DESC;
		this.canUsePower = false;
		this.trackedProfile = null;
		this.trackingProgress = 0;
		this.powerUses = 3;
	}

	@Override
	public String getScoreboardLine() { // ✓✔✕✖
		if (this.getTrackedProfile() != null) {
			return "Flair : " + ChatColor.WHITE + this.getTrackingProgress() + "%";
		}
		
		if (this.isCanUsePower()) {
			return "Flair : " + ChatColor.GREEN + "✔" + ChatColor.AQUA + " : " + ChatColor.WHITE + this.getPowerUses() + "/3";
		} else {
			return "Flair : " + ChatColor.RED + "✕" + ChatColor.AQUA + " : " + ChatColor.WHITE + this.getPowerUses() + "/3"; 
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
		if (this.getTrackedProfile() != null) {
			if (player.getLocation().distance(this.getTrackedProfile().getPlayer().getLocation()) < 15) {
				this.setTrackingProgress(trackingProgress + 4);
			}
			
			if (this.getTrackingProgress() >= 100) {
				if (this.getTrackedProfile().getRole().getTags().contains(Tags.WOLF)) {
					this.getGame().getChatManager().sendPrivateMessage("Le joueur " + ChatColor.WHITE + this.getTrackedProfile().getPlayer().getName() + ChatColor.YELLOW + " est un loup.", player);
				} else {
					this.getGame().getChatManager().sendPrivateMessage("Le joueur " + ChatColor.WHITE + this.getTrackedProfile().getPlayer().getName() + ChatColor.YELLOW + " n'est pas un loup.", player);
				}
				this.setTrackingProgress(0);
				this.setTrackedProfile(null);
			}
		}
	}

	@Override
	public void resetDay(Player player) {
		if (this.isCanUsePower()) {
			this.getGame().getChatManager().sendPrivateMessage("Il est trop tard pour flairer un joueur.", player);
			this.setCanUsePower(false);
		}
		
		if (this.getTrackedProfile() != null) {
			this.getGame().getChatManager().sendPrivateMessage("Vous n'avez pas réussi à flairer votre cible.", player);
			this.setTrackedProfile(null);
			this.setTrackingProgress(0);
		}
	}

	@Override
	public void resetNight(Player player) {
		if (this.getPowerUses() > 0) {
			this.getGame().getChatManager().sendPrivateMessage("Vous pouvez flairer un joueur avec </lg use pseudo>.", player);
			this.setCanUsePower(true);
		}
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
	    	Profile p = this.getGame().getProfilesManager().getProfileFromUUID(((Player)sender).getUniqueId().toString());
	    	if (p.isAlive()) {
				if (this.isCanUsePower()) {
					if (args.length == 2) {
						Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
						if (profile != null) {
							if (!profile.equals(this.getGame().getProfilesManager().getProfileFromName(sender.getName()))) {
								this.getGame().getChatManager().sendPrivateMessage("Vous flairez " + ChatColor.WHITE + profile.getPlayer().getName(), (Player) sender);
								this.setCanUsePower(false);
								this.setTrackingProgress(0);
								this.setTrackedProfile(profile);
								this.setPowerUses(powerUses - 1);
							} else {
								sender.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous flairer vous même.");
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
					sender.sendMessage(ChatColor.RED + "Vous ne pouvez pas flairer un joueur maintenant.");
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
		this.powerUses = 3;
	}

	public boolean isCanUsePower() {
		return canUsePower;
	}

	public void setCanUsePower(boolean canUsePower) {
		this.canUsePower = canUsePower;
	}

	public int getTrackingProgress() {
		return trackingProgress;
	}

	public void setTrackingProgress(int trackingProgress) {
		this.trackingProgress = trackingProgress;
	}

	public int getPowerUses() {
		return powerUses;
	}

	public void setPowerUses(int powerUses) {
		this.powerUses = powerUses;
	}

	public Profile getTrackedProfile() {
		return trackedProfile;
	}

	public void setTrackedProfile(Profile trackedProfile) {
		this.trackedProfile = trackedProfile;
	}

	@Override
	public void updateDeath(Player player, Profile deadProfile) {
	}
}
