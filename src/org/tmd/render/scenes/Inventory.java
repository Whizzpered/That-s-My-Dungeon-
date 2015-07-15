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
import org.tmd.environment.entities.Item;
import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;
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
        for (int i = 0; i < 4; i++) {
            slots.add(new Slot(Display.getWidth() / 2 + 96 * i - 96 * 2, 96) {

                @Override
                public void render() {
                    y = Display.getHeight() - 32 - 64;
                    super.render();
                }

            });
            slots.get(slots.size() - 1).type = ItemType.values()[i];
        }
        
        slots.get(0).item = new Item("hat", 1) {
                @Override
                public void modificate() {
                    
                }
            };
        
        slots.get(1).item = new Item("arms", 1) {
                @Override
                public void modificate() {
                    
                }
            };
        
        slots.get(2).item = new Item("braces", 1) {
                @Override
                public void modificate() {
                    
                }
            };
        
        slots.get(3).item = new Item("pants", 1) {
                @Override
                public void modificate() {
                    
                }
            };
        
        for (int i = 0; i < 8; i++) {
            slots.add(new Slot(Display.getWidth() / 2 + 96 * i - 96 * 4, 32));
            slots.add(new Slot(Display.getWidth() / 2 + 96 * i - 96 * 4, 128));
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
            Slot.inHand.renderIcon(Mouse.x, Mouse.y);
        }
    }
}
