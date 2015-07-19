/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.lwjgl.opengl.Display;
import org.tmd.main.Main;
import org.tmd.render.Color;

/**
 *
 * @author Whizzpered
 */
public class ToolTip {

    public int width, height;
    public String mess;

    public ToolTip(String text) {
        width = Main.defaultFont.getSize(text).width + 20;
        height = Main.defaultFont.getSize(text).height + 5;
        mess = text;
    }

    public void render() {
        int x = (int) (Mouse.x >= Display.getHeight() / 2 ? Mouse.x : Mouse.x - width);
        int y = (int) (Mouse.y <= Display.getHeight() / 2 ? Mouse.y : Mouse.y - height);
        Frame.defaultFrame.render(x, y, width, height);
        Main.defaultFont.drawString(mess, x+10, y+5, Color.white);
    }
}
