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
        back = new Image(s + "/back.png");
        front = new Image(s + "/front.png");
        left = new Image(s + "/left.png");
        if (Textures.image(s + "/right.png") != null) {
            right = new Image(s + "/right.png");
        } else {
            right = left;
        }
    }

    public void render(Side side, double x, double y) {
        if (side == Side.FRONT) {
            front.draw(x - front.width, y - front.height * 2, front.width * 2, front.height * 2);
        }
        if (side == Side.BACK) {
            back.draw(x - back.width, y - back.height * 2, back.width * 2, back.height * 2);
        }
        if (side == Side.RIGHT) {
            right.draw(x - right.width, y - right.height * 2, right.width * 2, right.height * 2);
        }
        if (side == Side.LEFT) {
            left.draw(x - left.width, y - left.height * 2, left.width * 2, left.height * 2);
        }
    }
}
