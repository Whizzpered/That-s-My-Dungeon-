/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class Frame {
    
    public static Frame defaultFrame = new Frame("frame");

    Image center;
    Image angle;
    Image line;

    public Frame(String frame) {
        center = new Image("gui/" + frame + "/center");
        angle = new Image("gui/" + frame + "/angle");
        line = new Image("gui/" + frame + "/line");
    }
    
    public void render() {
        
    }

}
