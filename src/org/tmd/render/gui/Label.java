/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import org.tmd.main.Main;

/**
 *
 * @author Whizzpered
 */
public class Label extends Element {

    String string;
    Color color;

    public Label(String str, double x, double y, Color color) {
        super(x, y, Main.defaultFont.getWidth(str), Main.defaultFont.getFont().getSize());
        string = str;
    }

    public void set(String s) {
        string = s;
    }
    
    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) {
        Main.defaultFont.drawString(string, (int)x, (int)y, color);
    }

}
