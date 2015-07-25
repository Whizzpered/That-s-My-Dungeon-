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
        tx = track.width / 2;
    }

    public void valueChanged() {

    }

    @Override
    public void hover() {
        if (Mouse.left) {
            if (Math.abs(Mouse.x - getX() - width / 2) <= (width - tw / 2 - 5) / 2) {
                tx = Mouse.x - getX();
            }
            if (tx < track.width / 2) {
                tx = track.width / 2;
            }
            if (tx > width - track.width / 2) {
                tx = width - track.width / 2;
            }
            valueChanged();
        }
        value = (float) ((tx - track.width / 2) / (width - track.width));
    }

    @Override
    public void render() {
        Frame.defaultFrame.render(getX(), getY() + 10, width, height - 20);
        track.draw(getX() + tx - tw / 2, getY());
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 2, (int) getY() - 28, Color.black);
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 2, (int) getY() - 30, Color.white);
    }
}
