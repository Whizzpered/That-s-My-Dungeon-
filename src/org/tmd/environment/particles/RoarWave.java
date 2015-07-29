/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.particles;

import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class RoarWave extends Particle {

    float vx, vy, a;
    static Image sonic_wave = new Image("effects/sonic_wave.png");

    public RoarWave(double x, double y, double tx, double ty) {
        super(x + (Main.RANDOM.nextInt(21) - 10), y + (Main.RANDOM.nextInt(21) - 15));
        a = (float) Math.atan2(ty - y, tx - x);
        vx = (float) (Math.cos(a) * 2);
        vy = (float) (Math.sin(a) * 2);
        timer = 255;
    }

    @Override
    public void tick() {
        super.tick();
        x += vx;
        y += vy;
    }

    @Override
    public void renderEntity() {
        float s = (float)timer / 255f;
        sonic_wave.a = s;
        s = 1.2f - s;
        sonic_wave.draw(x, y, sonic_wave.width * 4 * s, sonic_wave.height * 4 * s, a);
    }

}
