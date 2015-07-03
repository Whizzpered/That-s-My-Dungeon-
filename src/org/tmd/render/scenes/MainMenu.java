/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import static java.lang.Math.*;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Frame;

/**
 *
 * @author yew_mentzaki
 */
public class MainMenu extends Scene {

    Button newGameButton = new Button("newgame", 0, 300, 300, 50) {

        @Override
        public void click() {
            currentScene = new Dungeon();
        }

    };

    Button settingsButton = new Button("settings", 0, 370, 300, 50) {

        @Override
        public void click() {
        }

    };

    Button exitButton = new Button("exit", 0, 440, 300, 50) {

        @Override
        public void click() {
            Main.exit();
        }

    };
    
    Button ru_RU = new Button("ru_RU", 0, 300, 300, 50) {

        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
        }
        
        @Override
        public void click() {
            GameLocale.setCurrentLocale(text);
        }

    };
    
        Button en_US = new Button("en_US", 0, 370, 300, 50) {

        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
        }
        
        @Override
        public void click() {
            GameLocale.setCurrentLocale(text);
        }

    };
    
        Button de_DE = new Button("de_DE", 0, 440, 300, 50) {

        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
        }
        
        @Override
        public void click() {
            GameLocale.setCurrentLocale(text);
        }

    };
    
    @Override
    public void init() {
        gui.add(newGameButton);
        gui.add(settingsButton);
        gui.add(exitButton);
        
        gui.add(ru_RU);
        gui.add(en_US);
        gui.add(de_DE);
    }
    
    double a;

    @Override
    public void render() {
        a += 0.02;
        Frame.defaultFrame.render(400, 200, abs(200*sin(a)) + 100,  abs(200*cos(a)) + 100);
    }
    
}
