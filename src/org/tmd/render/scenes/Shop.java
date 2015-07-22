/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.scenes;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.items.Item;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Mouse;
import org.tmd.render.gui.Slot;
import org.tmd.render.gui.StoreSlot;
import static org.tmd.render.scenes.Scene.currentScene;

/**
 *
 * @author Whizzpered
 */
public class Shop extends Inventory {

    public ArrayList<StoreSlot> storeSlots = new ArrayList<StoreSlot>();
    public boolean escape = false;
    public String key = "S";

    @Override
    public void init() {
        loaded = true;
        for (int i = 0; i < 4; i++) {
            storeSlots.add(new StoreSlot(-(64 + 96 * i), 32) {

                @Override
                public void init() {
                    horisontalAlign = Align.CENTER;
                }
                
            });
        }

        storeSlots.get(0).item = new Item("hat", Declaration.dungeon.wave);
        storeSlots.get(0).item.price = Declaration.dungeon.wave * 2;

        storeSlots.get(1).item = new Item("arms", Declaration.dungeon.wave);
        storeSlots.get(1).item.price = Declaration.dungeon.wave * 2;

        storeSlots.get(2).item = new Item("braces", Declaration.dungeon.wave);
        storeSlots.get(2).item.price = Declaration.dungeon.wave * 2;

        storeSlots.get(3).item = new Item("pants", Declaration.dungeon.wave);
        storeSlots.get(3).item.price = Declaration.dungeon.wave * 2;
        gui.addAll(Declaration.inventory.gui);
        gui.addAll(storeSlots);
    }

    @Override
    public void render() {
        slots = Declaration.inventory.slots;
        super.render(); 
    }
}
