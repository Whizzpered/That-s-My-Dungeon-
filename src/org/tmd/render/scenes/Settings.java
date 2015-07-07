/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.gui.*;

/**
 *
 * @author Whizzpered
 */
public class Settings extends Scene {

    double a;
    Image background = new Image("gui/background.jpg");

    public ArrayList<ValueButton> buttons() {
        ArrayList<ValueButton> u = new ArrayList<ValueButton>();

        for (Element el : gui) {
            if (el instanceof ValueButton) {
                u.add((ValueButton) el);
            }
        }
        return u;
    }

    TrackBar musicButton = new TrackBar("music", 0, 230, 300, 50) {

        @Override
        public void click() {

        }

    };

    TrackBar soundButton = new TrackBar("sound", 0, 300, 300, 50) {

        @Override
        public void click() {

        }

    };

    Button applyButton = new Button("apply", 0, 440, 300, 50) {

        @Override
        public void click() {
            MainMenu.currentScene = Declaration.mainMenu;
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
        gui.add(musicButton);
        gui.add(soundButton);
        gui.add(applyButton);

        gui.add(new ValueButton("inventory", 0, 240, 300, 50) {

            @Override
            public void init() {
                horisontalAlign = Align.CENTER;
            }

            @Override
            public void changeSet() {
                System.out.println("New key: "+this.value);
            }
        });

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

    }

}
