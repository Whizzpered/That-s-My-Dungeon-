/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
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

    public boolean isMoused() {
        return ((Mouse.x - this.x < this.width / 2) && (Mouse.y - this.y < this.height));
    }

    public DevelopersName(String str, double x, double y, Color first, String desc, Color sec) {
        super(str, x, y, first);
        description = desc;
        second = sec;
    }

    @Override
    public void render(Graphics g) {
        if(hover){
            Main.defaultFont.drawString(description, (int)getX(), (int)getY(), second);
        } else {
            Main.defaultFont.drawString(string, (int)getX(), (int)getY(), color);
        }
    }
}
