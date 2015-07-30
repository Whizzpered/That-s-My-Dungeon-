/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Raider;
import org.tmd.main.Sounds;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Archer extends Raider {

    public Archer(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 400;
        attackDistance = 300;
        spriteStanding = new Sprite("creatures/archer");
        minimapIcon = new Image("minimap/archer.png");
    }

    @Override
    public void attackMethod(Entity e) {
        Sounds.play("bow");
        dungeon.entities.add(new Bullet(x, y, this, e.x, e.y));
    }

}
