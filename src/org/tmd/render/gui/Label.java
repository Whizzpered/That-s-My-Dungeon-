/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.newdawn.slick.Color;
import org.tmd.main.GameLocale;

import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author Whizzpered
 */
public class Label extends Element {

    public String string;
    public Image icon;
    public Color color;

    public Label(String str, double x, double y, Color color) {
        super(x, y, Main.defaultFont.getWidth(str), Main.defaultFont.getFont().getSize());
        string = str;
        this.color = color;
    }

    public Label(String icon, String str, double x, double y, Color color) {
        super(x, y, Main.defaultFont.getWidth(str), Main.defaultFont.getFont().getSize());
        string = str;
        this.icon = new Image(icon);
        this.color = color;
    }
    
    public String getText() {
        return GameLocale.get(string);
    }

    public void render() {
        int x = 0;
        if (icon != null) {
            icon.draw(getX(), getY() + 2);
            x += (int) icon.width;
        }
        Main.defaultFont.drawString(getText(), (int) getX() + x, (int) getY(), Color.black);
        Main.defaultFont.drawString(getText(), (int) getX() + x, (int) getY() - 2, color);
    }

}
