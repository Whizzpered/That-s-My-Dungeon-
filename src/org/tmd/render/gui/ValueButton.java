/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.Main;

/**
 *
 * @author Whizzpered
 */
public class ValueButton extends Button {
    
    public String value = " ";
    public boolean pressed = false;
    public ArrayList<ValueButton> buttons;
    
    public ValueButton(String text, double x, double y, double width, double height) {
        super(text, x, y, width, height);
        buttons = Declaration.settings.buttons();
    }

    
    @Override
    public void click() {
        
        if (!pressed) {
            pressed = true;
            for (ValueButton b : buttons) {
                if (b != this) {
                    ValueButton vb = b;
                    if (vb.pressed) {
                        vb.pressed = false;
                        vb.setValue("-");
                    }
                }
            }
        }
    }
    
    public void setValue(String s) {
        value = s;
        if (!value.equals("-")) {
            for (ValueButton b : buttons) {
                if (b != this) {
                    if (b.value.equals(value) && !b.value.equals("-")) {
                        b.setValue("-");
                    }
                }
            }
        }
    }
    
    public void changeSet(){
        
    } 
    
    @Override
    public void render() {
        if (!visible) {
            return;
        }
        Frame.defaultFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 4, (int) getY() + (int) height / 2 - 20 + (hover ? 1 : -1), Color.black);
        Main.defaultFont.drawStringAtCenter(getText(), (int) getX() + (int) width / 4, (int) getY() + (int) height / 2 - 22 + (hover ? 1 : -1), Color.white);
        if (pressed) {
            if (Keyboard.next()) {
                setValue(Keyboard.getKeyName(Keyboard.getEventKey()));
                pressed = false;
                changeSet();
            }
        }
        Main.defaultFont.drawStringAtCenter(value, (int)(getX()+width - Main.defaultFont.getWidth(value)), (int)getY() + 13, Color.white);
    }
}
