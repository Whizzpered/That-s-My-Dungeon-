/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import org.newdawn.slick.Color;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;

/**
 *
 * @author yew_mentzaki
 */
public class Button extends Element{
    
    public String text;
    
    public Button(String text, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.text = text;
    }
    
    public String getText(){
        return GameLocale.get(text);
    }
    
    @Override
    public void render() {
        Frame.defaultFrame.render(getX(), getY() + (hover?1:-1), width, height);
        Main.defaultFont.drawStringAtCenter(getText(), (int)getX() + (int)width / 2, (int)getY() + (int)height / 2 - 18, Color.black);
        Main.defaultFont.drawStringAtCenter(getText(), (int)getX() + (int)width / 2, (int)getY() + (int)height / 2 - 20, Color.white);
    }
    
}
