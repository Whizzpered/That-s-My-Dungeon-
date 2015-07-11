/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.entities.*;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Warrior extends Raider {

    public Warrior(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 96;
        spriteStanding = new Sprite("creatures/warrior");
        minimapIcon = new Image("minimap/warrior.png");
    }
}
