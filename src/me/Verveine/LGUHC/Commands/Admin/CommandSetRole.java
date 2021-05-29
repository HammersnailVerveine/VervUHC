package me.Verveine.LGUHC.Commands.Admin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
									profile.setRole((Role) object);
									sender.sendMessage("Le role du joueur " + ChatColor.WHITE + profile.getPlayer().getName() + ChatColor.GOLD + " est désormais " + profile.getRole().getColor() + profile.getRole().getName());
									this.getGame().getChatManager().sendPrivateMessage("Votre rôle a été changé par un administrateur", profile.getPlayer());
									this.getGame().getChatManager().sendProfileRole(profile);
									role.obtain(profile.getPlayer());
									return;
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
