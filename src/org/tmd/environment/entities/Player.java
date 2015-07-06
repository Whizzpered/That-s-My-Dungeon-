/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import org.tmd.environment.Point;
import org.tmd.render.gui.Mouse;

/**
 *
 * @author yew_mentzaki
 */
public class Player extends Entity {

    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void tick() {
        super.tick();
        
    }

    @Override
    public void goTo(double x, double y){
        super.goTo(x,y);
        System.out.println(shearable(new Point((int)this.x,(int)this.y), new Point((int)x,(int)y)));
    }
    
    @Override
    public void handle() {
        if (Mouse.left) {
            goTo(Mouse.x - dungeon.cam.x, Mouse.y - dungeon.cam.y);
        }
    }

}
