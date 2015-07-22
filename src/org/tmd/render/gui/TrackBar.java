/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import org.tmd.render.Color;
import org.newdawn.slick.Graphics;
import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author Whizzpered
 */
public class TrackBar extends Button {

    public Image track;
    boolean on = false;
    public double tx, tw;
    public float value;

    public TrackBar(String text, double x, double y, double width, double height) {
        super(text, x, y, width, height);
        track = new Image("gui/track.png");
        tw = track.width;
    }

    @Override
    public void hover() {
        if (Mouse.left) {
            if (Math.abs(Mouse.x - getX() - width / 2) <= (width - tw / 2 - 5) / 2) {
                tx = Mouse.x - getX();
            }
        }
        value = (float) ((tx) / width);
    }

    @Override
    public void render() {
        Frame.defaultFrame.render(getX(), getY(), width, height);
        track.draw(getX() + tx - tw / 2, getY());
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 2, (int) getY() - 20, Color.black);
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 2, (int) getY() - 22, Color.white);
    }
}
