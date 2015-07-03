/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import org.lwjgl.opengl.Display;

/**
 *
 * @author yew_mentzaki
 */
public class Mouse {
    static double x, y;
    static boolean left, middle, right;
    static boolean leftReleased, middleReleased, rightReleased;
    public static void update(){
        x = org.lwjgl.input.Mouse.getX();
        y = Display.getHeight() - org.lwjgl.input.Mouse.getY();
        leftReleased = left & !org.lwjgl.input.Mouse.isButtonDown(0);
        middleReleased = middle & !org.lwjgl.input.Mouse.isButtonDown(2);
        rightReleased = right & !org.lwjgl.input.Mouse.isButtonDown(1);
        left = org.lwjgl.input.Mouse.isButtonDown(0);
        middle = org.lwjgl.input.Mouse.isButtonDown(2);
        right = org.lwjgl.input.Mouse.isButtonDown(1);
    }
}
