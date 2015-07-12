/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import java.io.Serializable;
import org.newdawn.slick.Graphics;
import org.tmd.main.Declaration;
import org.tmd.render.Animation;
import org.tmd.render.Image;
import org.tmd.render.Textures;

/**
 *
 * @author Whizzpered
 */
public class Pointer implements Serializable {

    public double x, y;
    static Animation anim
            = anim = new Animation("effects/pointer");


    public Pointer() {
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void render() {
        if (!Declaration.dungeon.player.standing) {
            anim.get().draw(x - anim.get().width, y - anim.get().height, anim.get().width * 2, anim.get().height * 2);
        }
    }
}
