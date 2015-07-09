/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.render.Animation;

/**
 *
 * @author yew_mentzaki
 */
public class Expa extends Coin{

    public Expa(double x, double y) {
        super(x, y);
        coin = new Animation("effects/exp");
    }
    
}
