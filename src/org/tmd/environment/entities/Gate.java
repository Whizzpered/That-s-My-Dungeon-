/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class Gate extends Entity{

    public Gate(double x, double y) {
        super(x, y);
        phantom = true;
    }
    
    Image open = new Image("creatures/door/open.png");
    Image close = new Image("creatures/door/close.png");
    

    @Override
    public void render() {
        open.draw(x, y-150, open.width * 2, open.height * 2);
    }

    @Override
    public int getRenderQueuePriority() {
        return 0;
    }
}
