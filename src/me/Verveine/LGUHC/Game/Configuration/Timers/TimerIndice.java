package me.Verveine.LGUHC.Game.Configuration.Timers;

import org.bukkit.Material;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Game.Configuration.ConfigurationTimer;
import net.md_5.bungee.api.ChatColor;

public class TimerIndice extends ConfigurationTimer {

	public TimerIndice(Main main) {
		super(main);
		this.name = "Apparition d'un indice";
		scoreboardName = "Indice";
		this.material = Material.REDSTONE;
		this.timer = 60 * 10; // TODO : edit default with config
		this.setItem();
	}

	@Override
	public void run(GameLG game) {
		game.getGameObjectManager().generateIndice();
		game.getChatManager().sendGameMessage(ChatColor.GREEN + "Indice généré");
	}
}
