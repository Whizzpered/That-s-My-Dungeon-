/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import org.newdawn.slick.Graphics;
import org.tmd.render.Animation;
import org.tmd.render.Image;
import org.tmd.render.Textures;

/**
 *
 * @author Whizzpered
 */
public class Pointer {
    
    public double x, y;
    public boolean done;
    Image luzhica;
    Animation anim;

    public Pointer() {
        done = true;
        luzhica = new Image("effects/7.png");
        //anim = Textures.animation("particles/flag");
        //anim.setLooping(false);
        //anim.setSpeed(3);
    }

    public void set(double x, double y) {
        done = false;
        this.x = x;
        this.y = y;
        //anim.restart();
    }

    public void render() {
        if (!done && luzhica !=null) {
            //anim.draw((int) x - 48, (int) y-44);
            luzhica.draw(x, y);
        }
    }
}
