/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.environment.Condition;
import static org.tmd.environment.Condition.*;
import org.tmd.render.Animation;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Raider extends Entity {

    Animation ghost = new Animation("creatures/ghost");
    int deathtimer = 1000;
    boolean hasMoney = true;
    public Condition condition;

    public Raider(double x, double y, int level) {
        super(x, y);
        this.level = level;
        spriteStanding = new Sprite("creatures/pries");
        minimapIcon = new Image("minimap/warrior.png");
        name = "raider";
        width = 96;
    }

    @Override
    public void alive() {
        ai();
    }

    public void ai() {
        if(condition==JOINED){
            
        }
    }
    
    @Override
    public void dead() {
        condition = DEAD;
        if (hasMoney) {
            hasMoney = false;
            for (int i = 0; i < 4; i++) {
                dungeon.entities.add(new Coin(x, y));
            }
        }
        deathtimer--;
        if (deathtimer == 0) {
            dungeon.entities.remove(this);
            for (int i = 0; i < 4; i++) {
                dungeon.entities.add(new Expa(x, y));
            }
        }
    }

    @Override
    public void render() {
        if (!dead) {
            super.render();
        } else {
            int i = 0;
            if (deathtimer < 100) {
                i = 6 - deathtimer / 14;
                if (i > 6) {
                    i = 6;
                }
                if (i < 0) {
                    i = 0;
                }
            }
            Image g = ghost.images[i];
            g.draw(x - g.width, y - g.height * 2, g.width * 2, g.height * 2);
        }
    }

}
