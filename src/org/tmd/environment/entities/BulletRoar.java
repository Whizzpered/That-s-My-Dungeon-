/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.environment.particles.Hit;
import org.tmd.render.Image;

/**
 *
 * @author Whizzpered
 */
public class BulletRoar extends Bullet {

    int level;
    public BulletRoar(int level, double x, double y, Entity owner, double ax, double ay) {
        super(x, y, owner, ax, ay);
        this.level = level;
        minimapIcon = new Image("effects/sonic_wave.png");
        speed = 5;
    }

    @Override
    public void render() {
        minimapIcon.draw(x, y - 48, minimapIcon.width * 2, minimapIcon.height * 2, angle);
    }

    @Override
    public void move(double x, double y) {
        this.x += x;
        this.y += y;
        if (dungeon.terrain.get(this.x, this.y).top != null) {
            hp = 0;
        }
    }
}
