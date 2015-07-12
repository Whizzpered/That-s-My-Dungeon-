/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import org.tmd.render.Color;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;

/**
 *
 * @author yew_mentzaki
 */
public class Button extends Element {

    public String text;

    public Button(String text, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.text = text;
    }

    public String getText() {
        return GameLocale.get(text);
    }

    @Override
    public void render() {
        if (!visible) {
            return;
        }
        Frame.defaultFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 2, (int) getY() + (int) height / 2 - 20 + (hover ? 1 : -1), Color.black);
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 2, (int) getY() + (int) height / 2 - 22 + (hover ? 1 : -1), Color.white);
    }

}
