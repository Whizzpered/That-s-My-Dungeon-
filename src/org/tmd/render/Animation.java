/* Copyright (C) 2015, SHeart.  All rights reserved.
 * ______________________________________________________________________________
 * This program is proprietary software: decompiling, reverse engineering and
 * sharing of that code are denied.
 */
package org.tmd.render;

import java.util.ArrayList;
import java.util.Date;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author yew_mentzaki
 */
public class Animation {

    private org.tmd.render.Image[] images(ArrayList<Textures.Tex> t) {
        Textures.Tex[] tex = new Textures.Tex[t.size()];
        for (int i = 0; i < t.size(); i++) {
            tex[i] = t.get(i);
        }
        org.tmd.render.Image[] i = new org.tmd.render.Image[tex.length];
        for (int j = 0; j < i.length; j++) {
            i[j] = new org.tmd.render.Image(tex[j]);
        }
        return i;
    }

    private Texture[] textures(ArrayList<Textures.Tex> t) {
        Textures.Tex[] tex = new Textures.Tex[t.size()];
        for (int i = 0; i < t.size(); i++) {
            tex[i] = t.get(i);
        }
        Texture[] i = new Texture[tex.length];
        for (int j = 0; j < i.length; j++) {
            i[j] = tex[j].texture;
        }
        return i;
    }

    public Animation(String name, int delay) {
        this.name = name;
        this.delay = delay;
        images = Textures.animation(name).images;
        textures = Textures.animation(name).textures;
    }

    public Animation(String name) {
        this.name = name;
        images = Textures.animation(name).images;
        textures = Textures.animation(name).textures;
    }

    Animation(String name, ArrayList<Textures.Tex> tex) {
        this.name = name;
        images = images(tex);
        textures = textures(tex);
    }

    public org.tmd.render.Image get() {
        long now = new Date().getTime();
        if (now - last > delay) {
            index++;
            if (index == images.length) {
                index = 0;
            }
            last = now;
        }
        return images[index];
    }

    private int index = 0;
    private long last = 0;
    public int delay = 100;
    public final String name;
    public final org.tmd.render.Image[] images;
    public final Texture[] textures;

}
