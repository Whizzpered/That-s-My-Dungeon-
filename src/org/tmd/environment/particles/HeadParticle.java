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
public class HeadParticle extends Particle {

    float vx, vy, a;
    static Image head = new Image("effects/raider_head.png");

    public HeadParticle(double x, double y) {
        super(x + (Main.RANDOM.nextInt(21) - 10), y + (Main.RANDOM.nextInt(21) - 15));
        vx = (Main.RANDOM.nextInt(11) - 5) / 3f;
        vy = (Main.RANDOM.nextInt(5) - 5) / 3f;
        a = vx / 8;
    }

    @Override
    public void tick() {
        super.tick();
        x += vx;
        y += vy;
        vy += 0.02f;
        a += vx / 16;
        if (vy > Main.RANDOM.nextInt(5) + 2) {
            vy = -vy / 2;
        }
        if (timer == 0){
            Declaration.dungeon.addParticle(new BloodPool(x, y));
        }
    }

    @Override
    public void renderEntity() {
        head.draw(x, y, head.width * 2, head.height * 2, a);
    }

}
