/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render;
/**
 *
 * @author Whizzpered
 */
public class Sprite {

    public Image back = null, front = null, left = null, right = null;

    public Sprite(String s) {
        back = new Image(s + "/back.png");
        back.getNxCopy(2f);
        front = new Image(s + "/front.png");
        front.getNxCopy(2f);
        left = new Image(s + "/left.png");
        left.getNxCopy(2f);
        if (Textures.image(s + "/right.png") != null) {
            right = new Image(s + "/right.png");
            right.getNxCopy(2f);
        } else {
            right = left;
            right.getFlipped(true, false);
        }
    }

    public void render(Side side, double x, double y) {
        if (side == Side.FRONT) {
            front.draw(x - front.width, y - front.height * 2, front.width * 2, front.height * 2);
        }
        if (side == Side.BACK) {
            back.draw(x - back.width, y - back.height * 2, front.width * 2, front.height * 2);
        }
        if (side == Side.RIGHT) {
            right.draw(x - right.width, y - right.height * 2, front.width * 2, front.height * 2);
        }
        if (side == Side.LEFT) {
            left.draw(x - left.width, y - left.height * 2, front.width * 2, front.height * 2);
        }
    }
}
