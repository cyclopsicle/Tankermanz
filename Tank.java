package tankermanz;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tank {
	double x;
	double y;
	double fuel;
	double aimAngle;
	double power;
	String name;
	int health;
	int [] weapons;
	int playerID;
	int currentWeapon;
	static String [] weaponNames = {"Bullet", "Big Bullet", "Explosive Bullet", "Spray", "Triple Shot", "Dozen Shot", "Air Strike", "Splitter", "Breaker", "Tracker", "Horizon", "Flower", "Stream", "Sniper", "Nuke", "Armageddon", "Fountain"};
	static String [] weaponDescriptions = {"Bullet", "Big Bullet", "Explosive Bullet", "Spray", "Triple Shot", "Dozen Shot", "Air Strike", "Splitter", "Breaker", "Tracker", "Horizon", "Flower", "Stream", "Sniper", "Nuke", "Armageddon", "Fountain"};
	int team;
	int damageDealt;
	int totalDamageDealt;
	boolean destroyed;
	int tankTops;
	int tankTracks;
	Terrain terrain;
	int tankColor;
	
	//how fast the tank can move
	static final double SPEED = 35.0;
	static final double POWER_SPEED = 100.0;
	static final double CANNONSPEED = 50.0;
	static final int LENGTH = 20;
	static final int HEIGHT = 10;
	static final int HPLENGTH = 25;
	static final int HPHEIGHT = 5;
	public static int MAX_FUEL = 100;
	public static final int MAX_POWER = 300;
	public static int MAX_HEALTH = 300;
	public static final double HIT_RADIUS = 15;
	static final int MAX_SLOPE = 6;
	
	public Tank (Terrain terrain, int x, int playerID, int team, int tankTops, int tankTracks, int tankColor){
		fuel = MAX_FUEL;
		aimAngle = 0;
		power = 100;
		health = MAX_HEALTH;
		this.x = x;
		this.y = terrain.getY(x);
		weapons = new int [] {-1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		this.playerID = playerID;
		currentWeapon = 0;
		this.team = team;
		this.terrain = terrain;
		this.tankTops = tankTops;
		this.tankTracks = tankTracks;
		this.tankColor = tankColor;
		
		
		name = "Player " + String.valueOf(playerID + 1);
	}
	
	public int getCurrentWeapon(){
		return currentWeapon;
	}
	
	public void pickUpSupplyPack(SupplyPack s){
		weapons [s.powerUpID] += s.ammo;
	}
	
	public void changeWeapon(boolean right){
		if (right && currentWeapon == weapons.length - 1){
			currentWeapon = 0;
		}
		else if (right){
			currentWeapon++;
		}
		else if (!right && currentWeapon == 0){
			currentWeapon = weapons.length -1;
		}
		else if (!right){
			currentWeapon--;
		}
		
	}
	

	public boolean canMove(){
	if (Terrain.projectiles.size() == 0 && Terrain.explosions.size() == 0 && this.playerID == Terrain.currentPlayer.playerID){
		return true;
	}
	else
		return false;
}

	public void moveTankAngle (int elapsedTime, boolean CW){
		if (CW){
			if (aimAngle + CANNONSPEED*elapsedTime/Terrain.SECONDS < 360)
				aimAngle += CANNONSPEED*elapsedTime/Terrain.SECONDS;
			else
				aimAngle = 1;
		}
		else if (!CW){
			if (aimAngle - CANNONSPEED*elapsedTime/Terrain.SECONDS > 0)
				aimAngle -= CANNONSPEED*elapsedTime/Terrain.SECONDS;
			else
				aimAngle = 360;
			System.out.println(aimAngle);
		}

		System.out.println(aimAngle);
	}

	public void dropTank (){
		y = terrain.getY((int)x);
	}
	
	private boolean canMoveLeft(){
		if (x - LENGTH/2 > 0 && fuel > 0 && terrain.slope(x-1) > -1*MAX_SLOPE){
			return true;
		}
		else
			return false;
	}
	
	private boolean canMoveRight(){
		if (this.x + LENGTH/2 < Terrain.LENGTH && fuel > 0 && terrain.slope(x+1) < MAX_SLOPE){
			return true;
		}
		else
			return false;
	}

	public void moveTank (int elapsedTime, boolean left){
		dropTank();
		
		if (left && canMoveLeft()){
			x -= SPEED*elapsedTime/Terrain.SECONDS;
			fuel -= (SPEED * 0.5* elapsedTime)/Terrain.SECONDS;
		}
		else if (!left && canMoveRight()){
			x += SPEED*elapsedTime/Terrain.SECONDS;
			fuel -= (SPEED * 0.5* elapsedTime)/Terrain.SECONDS;
		}
		System.out.println("fuel: "+ fuel);

	}

	public void changePower (int elapsedTime, boolean increase){
		if (increase && power < 100){
			power+= POWER_SPEED* elapsedTime/Terrain.SECONDS;
		}
		else if (!increase && power > 0)
			power-= POWER_SPEED* elapsedTime/Terrain.SECONDS;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;		
	}

	public int getCurrentWeaponAmmo() {
		return weapons[currentWeapon];
	}
	
	public int getPower (){
		return (int)power;
	}

	public void setDamageDealt(int damageDealt) {
		this.damageDealt = damageDealt;
	}

	public int getDamageDealt (){
		return damageDealt;
	}

	public void increaseDamageDealt(int damageDealt) {
		this.damageDealt += damageDealt;
		totalDamageDealt+=damageDealt;
	}

	public void dealDamage(int damage) {
		if (health - damage <= 0){
			health = 0;
			destroyed = true;
		}
		else
			health -= damage;
	}
}

