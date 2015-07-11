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
public class BloodPool extends Particle{

    int t;
    static Image blood[] = new Image[]{new Image("effects/blood_0.png"), new Image("effects/blood_1.png"), new Image("effects/blood_2.png")};
    
    public BloodPool(double x, double y) {
        super(x, y);
        t = Main.RANDOM.nextInt(3);
        timer = 1000;
    }

    @Override
    public void renderFloor() {
        short mul = 2;
        if(timer < 50){
            mul = (short)(timer / 25);
        }
        blood[t].draw(x, y, blood[t].width * mul, blood[t].height * mul, 0);
    }
    
}
