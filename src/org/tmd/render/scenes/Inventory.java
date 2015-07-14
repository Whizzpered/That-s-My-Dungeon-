/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.tmd.environment.ItemType;
import static org.tmd.environment.ItemType.*;
import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;
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
            slots.add(new Slot(64 * i - 96, 96) {

                @Override
                public void render() {
                    y = Display.getHeight() - 32 - 64;
                    super.render();
                }

            });
            slots.get(slots.size() - 1).type = ItemType.values()[i];
        }
        for (int i = 0; i < 8; i++) {
            slots.add(new Slot(64 * i - 224, 32));
            slots.add(new Slot(64 * i - 224, 96));
        }
        gui.addAll(slots);
    }

    @Override
    public void tick() {
        for (int i = 0; i < 4; i++) {
            Declaration.dungeon.player.weared[i] = slots.get(i).item;
        }
    }
    
    @Override
    public void render() {
        super.render();
        if (Slot.inHand != null) {
            Slot.inHand.renderIcon(Mouse.x, Display.getHeight() - Mouse.y);
        }
    }
}
