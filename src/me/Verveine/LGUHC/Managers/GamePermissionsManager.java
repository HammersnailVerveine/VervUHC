package me.Verveine.LGUHC.Managers;

import me.Verveine.LGUHC.Main;
import me.Verveine.LGUHC.Game.GameLG;

public class GamePermissionsManager extends InternalManager {
	private boolean mineBlocks;
	private boolean wolfList;
	private boolean pvpHit;
	
	public GamePermissionsManager(Main main, GameLG game) {
		super(main, game);
		this.setWolfList(false);
		this.setPvpHit(false);
		this.setMineBlocks(false);
	}

	public boolean isMineBlocks() {
		return mineBlocks;
	}

	public void setMineBlocks(boolean mineBlocks) {
		this.mineBlocks = mineBlocks;
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
}
