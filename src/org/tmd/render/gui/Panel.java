/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;

/**
 *
 * @author yew_mentzaki
 */
public class Panel extends Element {

    private ArrayList<Element> elements = new ArrayList<Element>();

    public Panel(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
    public void add(Element e){
        elements.add(e);
    }

    @Override
    public void render() {
        if (!visible) {
            return;
        }
        Frame.defaultFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        for (Element e : elements) {
            e.render();
        }
    }

    @Override
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
                for (Element e : elements){
                    e.handle();
                }
                return true;
            }
        }
        return false;
    }

}
