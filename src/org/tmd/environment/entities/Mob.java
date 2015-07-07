/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Mob extends Entity {

    public double range;

    public Mob(double x, double y) {
        super(x, y);
        spriteStanding = new Sprite("creatures/mob");
        minimapIcon = new Image("minimap/monkey.png");
        range = 200;
    }

    @Override
    public void tick() {
        super.tick();
        patrool();
    }

    public void patrool() {
        if (focus == null) {
            for (Entity e : dungeon.getEntities()) {
                if (e instanceof Raider && !e.dead) {
                    double dist = Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2));
                    if (dist <= range) {
                        focus = e;
                        break;
                    }
                }
            }
        } else {
            if (!focus.dead) {
                goTo(focus.x, focus.y);
                double dist = Math.sqrt(Math.pow(x - focus.x, 2) + Math.pow(y - focus.y, 2));
                if(dist<=90){
                    focus.hit(5,this);
                }
            } else {
                focus = null;
            }
        }
    }
}
