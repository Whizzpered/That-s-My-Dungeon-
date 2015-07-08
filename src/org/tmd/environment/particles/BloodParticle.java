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
public class BloodParticle extends Particle {

    float vx, vy, a;
    static Image blood = new Image("effects/blood_particle.png");

    public BloodParticle(double x, double y) {
        super(x + (Main.RANDOM.nextInt(21) - 10), y + (Main.RANDOM.nextInt(21) - 15));
        vx = (Main.RANDOM.nextInt(11) - 5) / 3f;
        vy = (Main.RANDOM.nextInt(5) - 5) / 3f;
        a = (float) Math.atan2(vy, vx);
    }

    @Override
    public void tick() {
        super.tick();
        x += vx;
        y += vy;
        vy += 0.02f;
        a = (float) Math.atan2(vy, vx);
        if (vy > Main.RANDOM.nextInt(5) + 2) {
            timer = 0;
        }
        if (timer == 0){
            Declaration.dungeon.addParticle(new BloodPool(x, y));
        }
    }

    @Override
    public void renderEntity() {
        blood.draw(x, y, blood.width * 2, blood.height * 2, a);
    }

}
