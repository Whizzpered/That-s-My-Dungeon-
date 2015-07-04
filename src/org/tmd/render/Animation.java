/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render;

import java.util.ArrayList;

/**
 *
 * @author yew_mentzaki
 */
public class Animation {
    public final String name;
    public final Image[] frames;
    private long lastTimeOfDraw;
    public int delay;

    public Animation(String name, int delay) {
        this.name = name;
        this.delay = delay;
        this.frames = Textures.animation(name).frames;
    }
    
    Animation(String name, ArrayList<Textures.Tex> frames){
        this.name = name;
        this.frames = new Image[frames.size()];
        int i = 0;
        for(Textures.Tex t : frames){
            this.frames[i] = new Image(t);
        }
    }
    
    
    
}
