package me.Verveine.LGUHC;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Verveine.LGUHC.Commands.TestCommands;
import me.Verveine.LGUHC.Game.GameLG;
import me.Verveine.LGUHC.Runnables.RunnableUpdate;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	private GameLG game;
	
	
	@Override
	public void onEnable() {
		this.game = new GameLG(this);
		registerCmds();
		PluginManager pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(this, this);
		
		new RunnableUpdate(this).runTaskTimer(this, 0, 20);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerCmds() {
		this.getCommand("lgtest").setExecutor(new TestCommands(this));
	}

	public GameLG getGame() {
		return game;
	}

	public void setGame(GameLG game) {
		this.game = game;
	}
	
	// Handlers (temp)
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Bukkit.broadcastMessage(ChatColor.GOLD + "Player joined");
		game.updateProfiles(event.getPlayer());
	}
}
