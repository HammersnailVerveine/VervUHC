package me.Verveine.LGUHC.Players.Roles.Village;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Enums.Camp;
import me.Verveine.LGUHC.Enums.PlayerState;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import me.Verveine.LGUHC.Translations.FR;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class RoleSorciere extends Role {
	
	private boolean powerUsed;

	public RoleSorciere(Main main) {
		super(main);
		this.setColor(ChatColor.GREEN);
		this.setName(FR.SORCIERE_ROLE);
		this.camps.add(Camp.VILLAGE);
		this.getCampsDescription().add(Camp.VILLAGE);
		this.description = FR.SORCIERE_DESC;

		Potion potHeal = new Potion(PotionType.INSTANT_HEAL, 1).splash();
		Potion potRegen = new Potion(PotionType.REGEN, 1).splash();
		Potion potDamage = new Potion(PotionType.INSTANT_DAMAGE, 1).splash();
		
		ItemStack itemPotHeal = new ItemStack(potHeal.toItemStack(3));
		ItemStack itemPotRegen = new ItemStack(potRegen.toItemStack(1));
		ItemStack itemPotDamage = new ItemStack(potDamage.toItemStack(3));
		
		this.startInventory.add(itemPotHeal);
		this.startInventory.add(itemPotRegen);
		this.startInventory.add(itemPotDamage);
		
		this.powerUsed = false;
	}
	
	@Override
	public void update(Player player) {
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
	}

	@Override
	public void resetDay(Player player) {
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
	    	Profile p = this.getGame().getProfilesManager().getProfileFromUUID(((Player)sender).getUniqueId().toString());
	    	if (p.isAlive()) {
	    		if (!this.isPowerUsed()) {
		    		if (args.length == 2) {
		    			Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
		    			if (profile != null) {
		    				if (profile.getState().getPlayerState().equals(PlayerState.PREDEAD)) {
		    					this.getGame().getChatManager().sendPrivateMessage("Joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " ressuscité avec succès.", (Player) sender);
		    					this.getGame().getChatManager().sendPrivateMessage("Vous avez été ressuscité(e) par une sorcière.", profile.getPlayer());
		    					this.powerUsed = true;
		    					profile.respawn();
		    				} else {
		    					sender.sendMessage(ChatColor.RED + "Le joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.RED + " ne peut pas être ramené à la vie.");
		    				}
		    			} else {
		    				sender.sendMessage(ChatColor.RED + "Le profil du joueur " + args[1] + " est introuvable");
		    				return;
		    			}
		    		} else {
		    			sender.sendMessage(ChatColor.RED + "Usage : /lg use pseudo");
		    			return;
		    		}
	    		} else {
					sender.sendMessage(ChatColor.RED + "Vous ne pouvez plus utiliser votre pouvoir.");
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
		this.powerUsed = false;
	}

	@Override
	public void updateDeath(Player player, Profile deadProfile) {
		if (!deadProfile.getPlayer().equals(player) && !this.isPowerUsed()) {
			String deadPlayer = deadProfile.getPlayer().getName();
			List<String> list = new ArrayList<String>();
			list.add("Le joueur " + ChatColor.WHITE + deadPlayer + ChatColor.YELLOW +  " est mort.");
			list.add("Vous avez 15 secondes pour le ressusciter");
			this.getGame().getChatManager().sendPrivateMessage(list, player);
			
			TextComponent message = new TextComponent("Cliquez sur ce message pour le ressusciter");
			message.setColor(net.md_5.bungee.api.ChatColor.GREEN);
			message.setBold(true);
			String cmd = "/lg use " + deadPlayer;
			message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Ressusciter " + deadPlayer).color(net.md_5.bungee.api.ChatColor.GRAY).italic(true).create()));
			player.spigot().sendMessage(message);
		}
	}

	public boolean isPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(boolean powerUsed) {
		this.powerUsed = powerUsed;
	}
}
