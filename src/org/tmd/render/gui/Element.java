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
public class Element {

    public Element(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        init();
    }

    public Element parent = null;
    public Align horisontalAlign = Align.NONE, verticalAlign = Align.NONE;
    public double x, y, width, height;
    public boolean visible = true, enabled = true;
    public boolean hover = false, click = false;

    public double getX() {
        double x = this.x;
        double dw = Display.getWidth(), dh = Display.getHeight();
        if (parent != null) {
            x += parent.getX();
            dw = parent.width;
            dh = parent.height;
        }
        if (horisontalAlign == Align.LEFT) {
            return x;
        }
        if (horisontalAlign == Align.RIGHT) {
            return dw - width + x;
        }
        if (horisontalAlign == Align.CENTER) {
            return (dw - width) / 2 + x;
        }
        return x;
    }
    
    public void init(){
        
    }

    public double getY() {
        double y = this.y;
        double dw = Display.getWidth(), dh = Display.getHeight();
        if (parent != null) {
            y += parent.getY();
            dw = parent.width;
            dh = parent.height;
        }
        if (verticalAlign == Align.TOP) {
            return y;
        }
        if (verticalAlign == Align.DOWN) {
            return dh - height + y;
        }
        if (verticalAlign == Align.CENTER) {
            return (dh - height) / 2 + y;
        }
        return y;
    }

    public void render() {

    }

    public void click() {

    }

    public void hover() {

    }

    public boolean handle() {
        hover = false;
        click = false;
        if (visible) {
            double x = getX();
            double y = getY();
            if (x <= Mouse.x && y <= Mouse.y && x + width > Mouse.x && y + height > Mouse.y) {
                hover();
                hover = true;
                if (enabled && Mouse.leftReleased) {
                    click();
                    click = true;
                }
                return true;
            }
        }
        return false;
    }
}
