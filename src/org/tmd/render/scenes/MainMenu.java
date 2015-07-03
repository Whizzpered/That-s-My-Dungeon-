/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;

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

}
