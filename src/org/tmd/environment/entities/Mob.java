/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.environment.Point;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Mob extends Entity {

    public double range;
    public int sx, sy;

    public Mob(double x, double y) {
        super(x, y);
        sx = (int) x;
        sy = (int) y;
        spriteStanding = new Sprite("creatures/mob");
        minimapIcon = new Image("minimap/monkey.png");
        //attackType = "hit_clutches";
        range = 400;
        name = "monkey";
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
                        return;
                    }
                }
            }
            goTo(spawn.x, spawn.y);
        } else {
            if (!focus.dead) {
                goTo(focus.x, focus.y);
            } else {
                focus = null;
                goTo(sx, sy);
            }
        }
    }
}
                attack(focus);
