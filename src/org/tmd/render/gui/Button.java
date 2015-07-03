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
        Main.g.setColor(Color.darkGray);
        if(hover)Main.g.setColor(Color.gray);
        Main.g.fillRect((float)getX(), (float)getY(), (float)width, (float)height);
        Main.defaultFont.drawStringAtCenter(getText(), (int)getX() + (int)width / 2, (int)getY() + (int)height / 2 - 20, Color.white);
    }
    
}
