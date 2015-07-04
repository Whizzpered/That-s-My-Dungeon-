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
public class DevelopersName extends Label {

    public String description;
    Color second;

    public DevelopersName(String str, double x, double y, Color first, String desc, Color sec) {
        super(str, x, y, first);
        description = desc;
        second = sec;
    }

    @Override
    public void render(Graphics g) {
        if(hover){
            Main.defaultFont.drawString(description, (int)x, (int)y, second);
        } else {
            Main.defaultFont.drawString(string, (int)x, (int)y, color);
        }
    }
}
