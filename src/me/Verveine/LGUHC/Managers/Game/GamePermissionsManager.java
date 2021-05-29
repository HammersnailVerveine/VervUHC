package me.Verveine.LGUHC.Managers.Game;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public class GamePermissionsManager extends InternalManager {
	private boolean limiteMinage;
	private boolean wolfList;
	private boolean pvpHit;
	
	public GamePermissionsManager(Main main, GameLG game) {
		super(main);
		game.setGamePermissionsManager(this);
		this.setWolfList(false);
		this.setPvpHit(false);
		this.setLimiteMinage(false);
	}

	public boolean isWolfList() {
		return wolfList;
	}

	public void setWolfList(boolean wolfList) {
		this.wolfList = wolfList;
	}

	public boolean isPvpHit() {
		return pvpHit;
	}

	public void setPvpHit(boolean pvpHit) {
		this.pvpHit = pvpHit;
	}

	public boolean isLimiteMinage() {
		return limiteMinage;
	}

	public void setLimiteMinage(boolean limiteMinage) {
		this.limiteMinage = limiteMinage;
	}
}
