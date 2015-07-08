/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import static java.lang.Math.*;
import org.tmd.environment.particles.Hit;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Raider extends Entity {

    public Raider(double x, double y) {
        super(x, y);
        spriteStanding = new Sprite("creatures/pries");
        minimapIcon = new Image("minimap/warrior.png");
        name = "raider";
    }

    @Override
    public void tick() {
        goTo(dungeon.player.x, dungeon.player.y);
        attack(dungeon.player);
        super.tick();
    }
    
    

}
