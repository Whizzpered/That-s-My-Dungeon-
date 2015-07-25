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
public class Priest extends Raider {

    public Priest(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 400;
        attackDistance = 300;
        spriteStanding = new Sprite("creatures/priest");
        minimapIcon = new Image("minimap/priest.png");
    }

    @Override
    public void attackMethod(Entity e) {
        Sounds.play("magic");
        dungeon.entities.add(new Bullet(x, y, this, e));
    }

}
