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
    public Point spawn = new Point();

    public Mob(double x, double y) {
        super(x, y);
        spawn.x = (int) x;
        spawn.y = (int) y;
        spriteStanding = new Sprite("creatures/mob");
        minimapIcon = new Image("minimap/monkey.png");
        attackType = "hit_clutches";
        range = 400;
        name = "monkey";
    }

    @Override
    public void tick() {
        super.tick();
        patrool();
    }

    @Override
    public void hit(double damage, Entity from) {
        attack(from);
        super.hit(damage, from); //To change body of generated methods, choose Tools | Templates.
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
        } else {
            if (!focus.dead) {
                attack(focus);
                goTo(focus.x, focus.y);
            } else {
                focus = null;
                goTo(spawn.x, spawn.y);
            }
        }
    }
}
