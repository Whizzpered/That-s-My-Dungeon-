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
public class Bullet extends Entity {

    public Entity owner;
    public double angle;

    public Bullet(double x, double y, Entity owner, double ax, double ay) {     //aimx, aimy
        super(x, y);
        angle = Math.atan2((ay - y), (ax - x));
        this.owner = owner;
        speed = 6;
        hp = 500;
        maxhp = Double.MAX_VALUE;
        phantom = true;
        minimapIcon = new Image("effects/arrow.png");
    }

    public Bullet(double x, double y, Entity owner, Entity aim) {
        super(x, y);
        angle = Math.atan2((aim.y - y), (aim.x - x));
        this.owner = owner;
        focus = aim;
        speed = 4;
        hp = 500;
        maxhp = Double.MAX_VALUE;
        phantom = true;
        minimapIcon = new Image("effects/magic_attack.png");
    }

    @Override
    public void tick() {
        move(speed * Math.cos(angle), speed * Math.sin(angle));
        if (focus != null) {
            angle = Math.atan2((focus.y - y), (focus.x - x));
            double dist = Math.sqrt(Math.pow(x - focus.x, 2) + Math.pow(y - focus.y, 2));
            if (dist < 48) {
                dungeon.entities.remove(this);
                focus.hit(owner.getDMG(), owner);
                hp = 0;
                dungeon.addParticle(new Hit("hit_magic_attack", x, y - 48));
            }

        } else {
            for (Entity ent : dungeon.entities) {
                double dist = Math.sqrt(Math.pow(x - ent.x, 2) + Math.pow(y - ent.y, 2));
                if (dist < ent.size && ent.faction != owner.faction && !ent.phantom && !ent.dead) {
                    ent.hit(owner.getDMG(), owner);
                    hp = 0;
                    dungeon.addParticle(new Hit("hit_arrow", x, y - 48));
                }
            }
        }
        hp--;
        if (hp <= 0) {
            dungeon.entities.remove(this);
        }
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
