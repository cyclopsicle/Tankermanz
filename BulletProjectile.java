package tankermanz;

public class BulletProjectile extends Projectile {


	public BulletProjectile (double x, double y, int power, double angle){
						super(x, y, power, angle);
		radius=3;
		damage = 10;
		explosion = 25;
		projectileID = BULLET_PROJECTILE;
	}

}
