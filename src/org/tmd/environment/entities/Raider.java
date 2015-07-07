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
public class Raider extends Entity {

    public Raider(double x, double y) {
        super(x, y);
        spriteStanding = new Sprite("creatures/pries");
        minimapIcon = new Image("minimap/warrior.png");
        goTo(dungeon.playerRespawnPoint.x, dungeon.playerRespawnPoint.y);
    }

}
