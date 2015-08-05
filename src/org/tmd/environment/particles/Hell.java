/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.particles;

import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class Hell extends Particle {

    boolean mirror = Main.RANDOM.nextBoolean();
    boolean anim = Main.RANDOM.nextBoolean();
    static Image[] hell_0 = {
        new Image("effects/hell_0/0.png"),
        new Image("effects/hell_0/1.png"),
        new Image("effects/hell_0/2.png"),
        new Image("effects/hell_0/3.png"),
        new Image("effects/hell_0/4.png"),
        new Image("effects/hell_0/5.png"),
        new Image("effects/hell_0/6.png"),
        new Image("effects/hell_0/7.png"),
        new Image("effects/hell_0/8.png"),
        new Image("effects/hell_0/9.png"),
        new Image("effects/hell_0/10.png"),
        new Image("effects/hell_0/11.png"),
        new Image("effects/hell_0/12.png")
    }, hell_1 = {
        new Image("effects/hell_1/0.png"),
        new Image("effects/hell_1/1.png"),
        new Image("effects/hell_1/2.png"),
        new Image("effects/hell_1/3.png"),
        new Image("effects/hell_1/4.png"),
        new Image("effects/hell_1/5.png"),
        new Image("effects/hell_1/6.png"),
        new Image("effects/hell_1/7.png"),
        new Image("effects/hell_1/8.png"),
        new Image("effects/hell_1/9.png"),
        new Image("effects/hell_1/10.png"),
        new Image("effects/hell_1/11.png"),
        new Image("effects/hell_1/12.png")
    };

    public Hell(double x, double y) {
        super(x, y);
        timer = 260;
    }

    @Override
    public void tick() {
        timer--;
    }

    @Override
    public void renderFloor() {
        Image[] i = anim ? hell_0 : hell_1;
        i[timer/20].draw(x, y, i[timer/20].width * (mirror?1:-1) * 2, i[timer/20].height * 2);
    }

}