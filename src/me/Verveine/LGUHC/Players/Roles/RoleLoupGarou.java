package me.Verveine.LGUHC.Players.Roles;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Verveine.LGUHC.Enums.Camps;
import me.Verveine.LGUHC.Enums.Tags;
import me.Verveine.LGUHC.Players.Role;

public class RoleLoupGarou extends Role {

	@Override
	public void setDefaults() {
		this.setColor(ChatColor.RED);
		this.setName("Loup Garou");
		this.camps.add(Camps.WOLF);	
		this.tags.add(Tags.WOLF);
		this.appearsOnWolfList = true;
		this.description = "Votre but est de gagner avec les autres joueurs du camp des loups. Pour cela, vous disposez d'un effet force I la nuit, et aurez accès à la liste des loups avec la commande /lg lg.";
	}

	@Override
	public void updateStart(Player player) {
	}

	@Override
	public void updateDay(Player player) {
	}

	@Override
	public void updateNight(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, false, false));
	}

	@Override
	public void resetDay(Player player) {
	}

	@Override
	public void resetNight(Player player) {
	}

	@Override
	public void useCommand(CommandSender sender, Command cmd, String label, String[] args) {
	}
}
