/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;
import org.tmd.environment.entities.Entity;
import org.tmd.render.Color;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Link;
import static org.tmd.render.scenes.Scene.currentScene;

/**
 *
 * @author yew_mentzaki
 */
public class MainMenu extends Scene {

    Image background = new Image("gui/background.jpg");
    Image logo = (Main.RANDOM.nextInt(10) != 0) ? new Image("gui/small_logo.png") : new Image("gui/small_logo_pesakh.png");

    Button continueButton = new Button("continue", 0, 200, 300, 50) {

        @Override
        public void init() {
        }

        @Override
        public boolean handle() {
            visible = (Declaration.dungeon != null && Declaration.dungeon.player.hp > 0);
            return super.handle();
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
            currentScene = Declaration.settings;
        }

    };

    public MainMenu() {
    }

    Button exitButton = new Button("exit", 0, 440, 300, 50) {

        @Override
        public void click() {
            Main.exit();
        }

    };

    Link whizzpered = new Link("Whizzpered", -5, -75, Color.white, "Founder, programmer", new Color(158, 185, 234)) {
        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
            verticalAlign = Align.DOWN;
        }
    };
    Link yew_mentzaki = new Link("Yew_Mentzaki", -5, -50, Color.white, "Programmer, designer", new Color(158, 185, 234)) {
        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
            verticalAlign = Align.DOWN;
        }
    };
    Link version = new Link(Main.version, -5, 0, new Color(158, 185, 234), "Copyright © Stormforces, 2015. All rights reserved.", Color.white) {
        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
            verticalAlign = Align.DOWN;
        }
    };

    @Override
    public void init() {
        gui.add(continueButton);
        gui.add(newGameButton);
        gui.add(settingsButton);
        gui.add(exitButton);
        gui.add(whizzpered);
        gui.add(yew_mentzaki);
        gui.add(version);
    }

    public void buttons() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if (pressed && Declaration.dungeon != null) {
                pressed = false;
                currentScene = Declaration.dungeon;
            }
        } else {
            pressed = true;
        }
    }

    @Override
    public void render() {
        if (Declaration.dungeon != null) {
            Declaration.dungeon.staticRender();;
            Main.g.setColor(new Color(0, 0, 0, 150).slickColor());
            Main.g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        } else {
            background.draw(0, 0, Display.getWidth(), Display.getHeight());
        }
        logo.draw(Display.getWidth() / 2, logo.height / 2 + 40, 0);
        buttons();
    }

}
