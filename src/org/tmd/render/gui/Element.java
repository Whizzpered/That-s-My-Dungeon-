/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import java.io.Serializable;
import org.lwjgl.opengl.Display;
import org.tmd.main.Declaration;
import org.tmd.render.scenes.Scene;

/**
 *
 * @author yew_mentzaki
 */
public class Element implements Serializable {

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
    public boolean visible = true, enabled = true, descrip = false;
    public boolean hover = false, click = false;

    public double getX() {
        double x = this.x;
        double px = 0;
        double dw = Display.getWidth(), dh = Display.getHeight();
        if (parent != null) {
            px = parent.getX();
            dw = parent.width;
            dh = parent.height;
        }
        if (horisontalAlign == Align.LEFT) {
            return px + x;
        }
        if (horisontalAlign == Align.RIGHT) {
            return px + dw - width + x;
        }
        if (horisontalAlign == Align.CENTER) {
            return px + (dw - width) / 2 + x;
        }
        return px + x;
    }

    public void init() {

    }

    public double getY() {
        double y = this.y;
        double py = 0;
        double dw = Display.getWidth(), dh = Display.getHeight();
        if (parent != null) {
            py = parent.getY();
            dw = parent.width;
            dh = parent.height;
        }
        if (verticalAlign == Align.TOP) {
            return py + y;
        }
        if (verticalAlign == Align.DOWN) {
            return py + dh - height + y;
        }
        if (verticalAlign == Align.CENTER) {
            return py + (dh - height) / 2 + y;
        }
        return py + y;
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
                if (Declaration.settings.tips) {
                    descrip = true;
                }
                if (enabled && Mouse.leftReleased) {
                    click();
                    click = true;
                }
                return true;
            }
            if (descrip) {
                descrip = false;
                Scene.currentScene.currentTip = null;
            }
        }
        return false;
    }
}
