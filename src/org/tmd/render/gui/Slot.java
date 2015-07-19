/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.lwjgl.opengl.Display;
import org.tmd.environment.entities.items.ItemType;
import org.tmd.environment.entities.items.Item;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Slot extends Button {

    public static Item inHand;
    public boolean rb, descrip;
    public Item item;
    public ItemType type;

    public Slot(int x, int y) {
        super("", x, y, 80, 80);
    }

    @Override
    public void click() {
        descrip = false;
        if (Declaration.inventory.slots.indexOf(this) < 4 && inHand != null) {
            if (type != null && inHand.type == type) {
                Item i = item;
                item = inHand;
                inHand = i;
            }
        } else {
            Item i = item;
            item = inHand;
            inHand = i;
        }

    }

    @Override
    public boolean handle() {
        if (super.handle()) {
            descrip = true;
        } else {
            descrip = false;
        }
        return super.handle();
    }

    public void rclick() {
        item = null;
    }

    @Override
    public void render() {
        Frame.defaultFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        if (item != null) {
            item.renderIcon(getX() + 8, getY() + 8);
            if (descrip) {
                Frame.defaultFrame.render((Mouse.x >= Display.getHeight() / 2 ? Mouse.x : Mouse.x - 200),
                        (Mouse.y <= Display.getHeight() / 2 ? Mouse.y : Mouse.y - 200), 200, 200);
            }
        }
    }
    
}
