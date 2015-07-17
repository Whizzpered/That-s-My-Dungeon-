/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.tmd.environment.ItemType;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Item;
import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Mouse;
import org.tmd.render.gui.Slot;

/**
 *
 * @author Whizzpered
 */
public class Inventory extends Scene {

    public Player player;
    public ArrayList<Slot> slots = new ArrayList<Slot>();

    @Override
    public void init() {
        loaded = true;
        for (int i = 0; i < 4; i++) {
            slots.add(new Slot(96 * i - 144, -16) {

                @Override
                public void init() {
                    horisontalAlign = Align.CENTER;
                    verticalAlign = Align.DOWN;
                }
                
            });
            slots.get(slots.size() - 1).type = ItemType.values()[i];
        }

        slots.get(0).item = new Item("hat", 1) {
            @Override
            public void modificate(Entity ent) {

            }
        };

        slots.get(1).item = new Item("arms", 1) {
            @Override
            public void modificate(Entity ent) {

            }
        };

        slots.get(2).item = new Item("braces", 1) {
            @Override
            public void modificate(Entity ent) {

            }
        };

        slots.get(3).item = new Item("pants", 1) {
            @Override
            public void modificate(Entity ent) {

            }
        };

        for (int i = 0; i < 4; i++) {
            slots.get(i).item.type = ItemType.values()[i];
        }

        for (int i = 0; i < 4; i++) {
            slots.add(new Slot(64 + 96 * i, 32){

                @Override
                public void init() {
                    horisontalAlign = Align.CENTER;
                }
                
            });
            slots.add(new Slot(64 + 96 * i, 128){

                @Override
                public void init() {
                    horisontalAlign = Align.CENTER;
                }
                
            });
            slots.add(new Slot(64 + 96 * i, 224){

                @Override
                public void init() {
                    horisontalAlign = Align.CENTER;
                }
                
            });
            slots.add(new Slot(64 + 96 * i, 320){

                @Override
                public void init() {
                    horisontalAlign = Align.CENTER;
                }
                
            });
        }
        gui.addAll(slots);
    }

    @Override
    public void handle() {

    }

    @Override
    public void render() {
        Declaration.dungeon.render();
        Main.g.setColor(new Color(0, 0, 0, 150).slickColor());
        Main.g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        super.render();
        for (int i = 0; i < 4; i++) {
            Declaration.dungeon.player.weared[i] = slots.get(i).item;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            currentScene = Declaration.dungeon;
        }
    }

    @Override
    public void renderGUI() {
        super.renderGUI();
        if (Slot.inHand != null) {
            Slot.inHand.renderIcon(Mouse.x - 32, Mouse.y - 32);
        }
    }
}
