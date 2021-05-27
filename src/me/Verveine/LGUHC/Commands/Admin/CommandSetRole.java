package me.Verveine.LGUHC.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Commands.PluginCommand;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationRole;
import me.Verveine.LGUHC.Players.Profile;
import me.Verveine.LGUHC.Players.Role;
import net.md_5.bungee.api.ChatColor;

public class CommandSetRole extends PluginCommand {

	public CommandSetRole(Main main) {
		super(main);
		this.labels.add("setrole");
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 2) {
			Profile profile = this.getGame().getProfilesManager().getProfileFromName(args[1]);
			if (profile != null) {
				String entry = "";
				for (int i = 2 ; i < args.length ; i ++) {
					entry += args[i];
				}
				entry = entry.toLowerCase();
				for (ConfigurationRole configRole : this.getGame().getConfigurationsManager().getConfigurationRoles()) {
					String roleName = configRole.getRole().getName().toString().toLowerCase().replaceAll(" ", "");
					String roleNameAbridged = "";
					
					for (String str : configRole.getRole().getName().toString().toLowerCase().split(" ")) {
						roleNameAbridged += str.toCharArray()[0];
					}
					
					if (roleName.equals(entry) || roleNameAbridged.equals(entry)) {
						Role role = null;
						try {
							role = (Role) configRole.getRole().clone();
						} catch (CloneNotSupportedException e) {
							this.getGame().getChatManager().sendSystemMessage("erreur clone CommmandSetRole");
							e.printStackTrace();
							return;
						}
						
						if (role != null) {
							profile.setRole(role);
							this.getGame().getChatManager().sendSystemMessage("Le role du joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " est désormais " + profile.getRole().getColor() + profile.getRole().getName());
							this.getGame().getChatManager().sendPrivateMessage("Votre rôle a été changé par un administrateur", profile.getPlayer());
							this.getGame().getChatManager().sendProfileRole(profile);
							return;
						} else {
							sender.sendMessage(ChatColor.GOLD + "null role");
							return;
						}
					}
				}
				
				entry = "";
				for (int i = 2 ; i < args.length ; i ++) {
					entry += args[i] + " ";
				}
				sender.sendMessage(ChatColor.GOLD + "Rôle inconnu : " + ChatColor.WHITE + entry);
				return; 
			} else {
				sender.sendMessage(ChatColor.GOLD + "Le profil du joueur " + args[1] + " est introuvable");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.GOLD + "Usage : /alg setrole pseudo role");
			return;
		}
	}
}
