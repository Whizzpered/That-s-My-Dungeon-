/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import static java.lang.Math.*;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Frame;

/**
 *
 * @author yew_mentzaki
 */
public class MainMenu extends Scene {

    Image background = new Image("gui/background.jpg");
    Image logo = new Image("gui/small_logo.png");

    Button continueButton = new Button("continue", 0, 200, 300, 50) {

        @Override
        public void init() {
            visible = false;
        }
        
        @Override
        public void click() {
            currentScene = Declaration.dungeon;
        }

    };

    Button newGameButton = new Button("newgame", 0, 300, 300, 50) {

        @Override
        public void click() {
            currentScene = Declaration.dungeon = new Dungeon();
            continueButton.visible = true;
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
        gui.add(continueButton);
        gui.add(newGameButton);
        gui.add(settingsButton);
        gui.add(exitButton);

        gui.add(ru_RU);
        gui.add(en_US);
        gui.add(de_DE);
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
        logo.draw(Display.getWidth() / 2, logo.height / 2 + 40, 0);
    }

}
