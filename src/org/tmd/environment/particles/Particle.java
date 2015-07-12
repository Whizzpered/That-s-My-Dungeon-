/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.particles;

import java.io.Serializable;

/**
 *
 * @author yew_mentzaki
 */
public class Particle implements Serializable{
    public float x, y;
    public int timer = 256;

    public Particle(double x, double y) {
        this.x = (float)x;
        this.y = (float)y;
    }
    
    public void tick(){
        timer--;
    }
    
    public void renderFloor(){
        
    }
    
    public void renderEntity(){
        
    }
    
}
