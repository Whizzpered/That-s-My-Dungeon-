/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import org.tmd.render.Color;
import org.tmd.main.Declaration;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Label;
import org.tmd.xfg.XFG;
import org.tmd.xfg.XObject;

/**
 *
 * @author yew_mentzaki
 */
public class Dialog extends Scene {

    Image background = new Image("gui/background.jpg");
    private static XFG dialog;
    private static XObject currObject;

    public static void set(String dialog) {
        try {
            Dialog.dialog = new XFG(new File("locale/" + GameLocale.get("locale_name") + "/" + dialog + ".dialog"));
            Dialog.currObject = Dialog.dialog.get(0);
            currentScene = Declaration.dialog;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    Label[] answers = new Label[6];

    @Override
    public void init() {
        for (int i = 0; i < 6; i++) {
        }
    }
    
    

    @Override
    public void render() {
        if (Declaration.dungeon != null) {
            Declaration.dungeon.render();
            Main.g.setColor(new Color(0, 0, 0, 150).slickColor());
            Main.g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        } else {
            background.draw(0, 0, Display.getWidth(), Display.getHeight());
        }
    }
}
