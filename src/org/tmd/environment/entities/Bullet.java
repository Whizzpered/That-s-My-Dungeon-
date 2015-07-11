/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

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
        angle = Math.atan2((y - ay), (x - ax));
        this.owner = owner;
        speed = 5;
        hp = 500;
        minimapIcon = new Image("effects/magic_attack.png");
    }

    public Bullet(double x, double y, Entity owner, Entity aim) {
        super(x, y);
        angle = Math.atan2((y - aim.y), (x - aim.x));
        this.owner = owner;
        focus = aim;
        speed = 6;
        hp = 500;
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
            }

        } else {
            for (Entity ent : dungeon.entities) {
                if (ent.faction != owner.faction && !ent.phantom && !ent.dead) {
                    focus.hit(owner.getDMG(), owner);
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
        minimapIcon.draw(x, y, angle);
    }
}
