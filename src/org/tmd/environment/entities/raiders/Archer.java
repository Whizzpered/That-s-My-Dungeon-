/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Raider;

/**
 *
 * @author Whizzpered
 */
public class Archer extends Raider{
    public Archer(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 200;
    }

    @Override
    public void attack(Entity e) {
        if (attackReload == 0 && e != null) {
            double angle = atan2((e.y - y), (e.x - x));
            goTo(e.x + ((attackDistance-5)*cos(angle)), e.y + ((attackDistance-5)*sin(angle)));
            if (sqrt(pow(e.x - x, 2) + pow(e.y - y, 2)) <= attackDistance) {
                dungeon.entities.add(new Bullet(x, y, this, e));
                System.out.println("ATTACKED" + angle);
                attackReload = attackReloadTime;
            }
        }
    }
}
