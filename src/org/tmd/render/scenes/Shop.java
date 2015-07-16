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
import org.tmd.environment.entities.Item;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.render.gui.Mouse;
import org.tmd.render.gui.Slot;
import org.tmd.render.gui.StoreSlot;
import static org.tmd.render.scenes.Scene.currentScene;

/**
 *
 * @author Whizzpered
 */
public class Shop extends Scene {

    public ArrayList<StoreSlot> slots = new ArrayList<StoreSlot>();
    public boolean escape = false;
    public String key = "S";

    @Override
    public void init() {

        for (int i = 0; i < 4; i++) {
            slots.add(new StoreSlot(32 + i * 96, 32));
        }

        slots.get(0).item = new Item("hat", Declaration.dungeon.wave) {
            @Override
            public void modificate(Entity cr) {
                cr.maxhp += Declaration.dungeon.player.level * 10;
            }
        };
        slots.get(0).item.price = Declaration.dungeon.wave * 2;

        slots.get(1).item = new Item("arms", Declaration.dungeon.wave) {
            @Override
            public void modificate(Entity cr) {
                cr.maxhp += Declaration.dungeon.player.level * 10;
            }
        };
        slots.get(1).item.price = Declaration.dungeon.wave * 2;

        slots.get(2).item = new Item("braces", Declaration.dungeon.wave) {
            @Override
            public void modificate(Entity cr) {
                cr.attackDamage += Declaration.dungeon.player.level * 3;
            }
        };
        slots.get(2).item.price = Declaration.dungeon.wave * 2;

        slots.get(3).item = new Item("pants", Declaration.dungeon.wave) {
            @Override
            public void modificate(Entity cr) {
                cr.attackDamage += Declaration.dungeon.player.level * 3;
            }
        };
        slots.get(3).item.price = Declaration.dungeon.wave * 2;

        gui.addAll(slots);
        Declaration.inventory.init();
    }

    @Override
    public void render() {
        Declaration.dungeon.render();
        Main.g.setColor(new Color(0, 0, 0, 150).slickColor());
        Main.g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        super.render();
        for (int i = 0; i < 4; i++) {
            Declaration.dungeon.player.weared[i] = Declaration.inventory.slots.get(i).item;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            currentScene = Declaration.dungeon;
        }
    }

    @Override
    public boolean handleGUI() {
        super.handleGUI();
        return Declaration.inventory.handleGUI();
    }

    @Override
    public void renderGUI() {
        super.renderGUI();
        Declaration.inventory.renderGUI();
        if (Slot.inHand != null) {
            Slot.inHand.renderIcon(Mouse.x, Mouse.y);
        }
    }
}
