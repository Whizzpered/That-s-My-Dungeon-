/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.newdawn.slick.Color;
import org.tmd.main.Main;

/**
 *
 * @author Whizzpered
 */
public class Link extends Label {

    public String description;
    Color second;

    public Link(String str, double x, double y, Color first, String desc, Color sec) {
        super(str, x, y, first);
        description = desc;
        second = sec;
    }

    public void render() {
        if(hover){
            width = Main.defaultFont.getWidth(description);
            height = 20;
            Main.defaultFont.drawString(description, (int)getX(), (int)getY() - 15, second);
        } else {
            width = Main.defaultFont.getWidth(string);
            height = 20;
            Main.defaultFont.drawString(string, (int)getX(), (int)getY() - 15, color);
        }
    }
}
