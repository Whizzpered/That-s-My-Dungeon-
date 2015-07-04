/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;
import org.tmd.render.Side;

/**
 *
 * @author Whizzpered
 */
public class Sprite {

    public Image back = null, front = null, left = null, right = null;

    public Sprite(String s) {
        back = new Image(s + "back.png").getNxCopy(2f);
        front = new Image(s + "front.png").getNxCopy(2f);
        left = new Image(s + "left.png").getNxCopy(2f);
        if (Textures.image(s + "right.png") != null) {
            right = new Image(s + "right.png").getNxCopy(2f);
        } else {
            right = left.getFlipped(true, false);
        }
    }

    public void render(Graphics g, Side side, int x, int y) {
        if (side == Side.FRONT) {
            front.draw(x, y);
        }
        if (side == Side.BACK) {
            back.draw(x, y);
        }
        if (side == Side.RIGHT) {
            right.draw(x, y);
        }
        if (side == Side.LEFT) {
            left.draw(x, y);
        }
    }
}
