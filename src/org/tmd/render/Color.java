/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render;

import java.io.Serializable;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author yew_mentzaki
 */
public class Color implements Serializable{
    public static final Color transparent = new Color(0, 0, 0, 0);
    public static final Color white = new Color(255, 255, 255);
    public static final Color yellow = new Color(255, 255, 0);
    public static final Color red = new Color(255, 0, 0);
    public static final Color blue = new Color(0, 0, 255);
    public static final Color green = new Color(0, 255, 0);
    public static final Color black = new Color(0, 0, 0);
    public static final Color gray = new Color(128, 128, 128);
    public static final Color cyan = new Color(0, 255, 255);
    public static final Color darkGray = new Color(64, 64, 64);
    public static final Color lightGray = new Color(192, 192, 192);
    public static final Color pink = new Color(255, 0, 255);
    public static final Color orange = new Color(255, 128, 0);
    public static final Color magenta = new Color(128, 0, 128);
    public float r, g, b, a = 1;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1f;
    }
    
    public Color(int r, int g, int b, int a) {
        this.r = (float)r/255f;
        this.g = (float)g/255f;
        this.b = (float)b/255f;
        this.a = (float)a/255f;
    }
    
    public Color(int r, int g, int b) {
        this.r = (float)r/255f;
        this.g = (float)g/255f;
        this.b = (float)b/255f;
        this.a = 1f;
    }
    
    public void bind(){
        GL11.glColor4f(r, g, b, a);
    }
    
    public org.newdawn.slick.Color slickColor(){
        return new org.newdawn.slick.Color(r, g, b, a);
    }
}
