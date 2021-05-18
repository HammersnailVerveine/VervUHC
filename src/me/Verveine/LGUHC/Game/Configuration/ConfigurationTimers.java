package me.Verveine.LGUHC.Game.Configuration;

public class ConfigurationTimers {
	private int timerRoles;
	private int timerLoups;
	private int timerPvP;
	private int timerBorder;
	private int timerMinage;
	
	public ConfigurationTimers() {
		setTimerRoles(0);
		setTimerLoups(0);
		setTimerPvP(0);
		setTimerBorder(0);
		setTimerMinage(0);
	}

	public int getTimerRoles() {
		return timerRoles;
	}

	public void setTimerRoles(int timerRoles) {
		this.timerRoles = timerRoles;
	}

	public int getTimerLoups() {
		return timerLoups;
	}

	public void setTimerLoups(int timerLoups) {
		this.timerLoups = timerLoups;
	}

	public int getTimerPvP() {
		return timerPvP;
	}

	public void setTimerPvP(int timerPvP) {
		this.timerPvP = timerPvP;
	}

	public int getTimerBorder() {
		return timerBorder;
	}

	public void setTimerBorder(int timerBorder) {
		this.timerBorder = timerBorder;
	}

	public int getTimerMinage() {
		return timerMinage;
	}

	public void setTimerMinage(int timerMinage) {
		this.timerMinage = timerMinage;
	}
}
