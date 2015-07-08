/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.xfg.XFG;
import org.tmd.xfg.XObject;

/**
 *
 * @author yew_mentzaki
 */
public class Dialog extends Scene{
    
    Image background = new Image("gui/background.jpg");
    private static XFG dialog;
    private static XObject currObject;
    
    public static void set(String dialog){
        Dialog.dialog = new XFG(dialog);
        Dialog.currObject = Dialog.dialog.get(0);
        currentScene = Declaration.dialog;
    }
    
    
    
    @Override
    public void render() {
        if (Declaration.dungeon != null) {
            Declaration.dungeon.render();
            Main.g.setColor(new Color(0, 0, 0, 150));
            Main.g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        } else {
            background.draw(0, 0, Display.getWidth(), Display.getHeight());
        }
    }
}
