/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.particles;

import org.newdawn.slick.Color;
import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class SlimeTrace extends Particle {

    int t;
    static Image[] slime = new Image[]{
        new Image("effects/slime/0.png"),
        new Image("effects/slime/1.png"),
        new Image("effects/slime/2.png"),
        new Image("effects/slime/3.png")};

    public SlimeTrace(double x, double y) {
        super(x, y);
        t = Main.RANDOM.nextInt(4);
        timer = 3000;
    }
    
    //Найди схуяли не рендерит.
    
    @Override
    public void renderFloor() {
        if (timer < 512) {
            slime[t].a = (float)timer / 512f;
        } else {
            slime[t].a = 1;
        }
        slime[t].draw(x, y);
    }

}
