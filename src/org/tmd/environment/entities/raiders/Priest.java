/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import static java.lang.Math.*;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Raider;
import org.tmd.environment.particles.Hit;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Priest extends Raider {

    public Priest(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 200;
        spriteStanding = new Sprite("creatures/priest");
        minimapIcon = new Image("minimap/priest.png");
    }

    @Override
    public void attack(Entity e) {
        if (attackReload == 0 && e != null) {
            double angle = atan2((y - e.y), (x - e.x));
            goTo(e.x + ((attackDistance-5)*cos(angle)), e.y + ((attackDistance-5)*sin(angle)));
            if (sqrt(pow(e.x - x, 2) + pow(e.y - y, 2)) <= attackDistance) {
                dungeon.entities.add(new Bullet(x, y, this, e));
                System.out.println(angle);
                attackReload = attackReloadTime;
            }
        }
    }
}
