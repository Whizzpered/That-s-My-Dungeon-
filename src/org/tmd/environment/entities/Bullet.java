/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

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
    }

    public Bullet(double x, double y, Entity owner, Entity aim) {
        super(x, y);
        angle = Math.atan2((y - aim.y), (x - aim.x));
        this.owner = owner;
        focus = aim;
        speed = 6;
    }

    @Override
    public void tick() {
        move(speed * Math.cos(angle), speed * Math.sin(angle));
        if (focus != null) {
            angle = Math.atan2((y - focus.y), (x - focus.x));
            double dist = Math.sqrt(Math.pow(x - focus.x, 2) + Math.pow(y - focus.y, 2));
            if (dist < 48) {
                dungeon.entities.remove(this);
                focus.hp -= owner.getDMG();
            }

        } else {
            for (Entity ent : dungeon.entities) {
                if (ent.faction != owner.faction && !ent.phantom && !ent.dead) {
                    
                }
            }
        }
    }

}
