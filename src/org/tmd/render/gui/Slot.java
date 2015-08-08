/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.tmd.environment.entities.items.ItemType;
import org.tmd.environment.entities.items.Item;
import org.tmd.environment.entities.items.Modificator;
import org.tmd.main.Declaration;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.render.scenes.Scene;

/**
 *
 * @author Whizzpered
 */
public class Slot extends Button {

    public static Item inHand;
    public boolean rb;
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
        return super.handle();
    }

    public void rclick() {
        item = null;
        descrip = hover;
    }

    @Override
    public void render() {
        Frame.defaultFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        if (item != null) {
            item.renderIcon(getX() + 8, getY() + 8);
            if (descrip) {
                String itemType = item.type.name().toLowerCase();
                String description = "";
                for (Modificator mod : item.modificators) {
                    description += GameLocale.get(mod.name()) + item.modificatorsvalue.get(item.modificators.indexOf(mod)) + '\n';
                }
                Scene.currentScene.currentTip = new ToolTip(GameLocale.get(itemType) + '\n' + '\n' + description);
            }
        }
        if (type != null) {
            Main.defaultFont.drawString(type.name(), (int) (getX() + width / 3), (int) getY(), Color.yellow);
        }
    }

}
