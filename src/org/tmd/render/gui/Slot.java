/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.tmd.environment.ItemType;
import org.tmd.environment.entities.Item;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Slot extends Button {

    public static Item inHand;
    boolean rb;
    public Item item;
    public ItemType type;

    public Slot(int x, int y) {
        super("", x, y, 64, 64);
    }

    @Override
    public void click() {
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

    public void rclick() {
        item = null;
    }

    @Override
    public void render() {
        Frame.defaultFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        if (item != null) {
            item.renderIcon(x + 4, y + 4);
        }
    }
}
