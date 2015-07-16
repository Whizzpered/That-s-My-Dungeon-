/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.Main;

/**
 *
 * @author Whizzpered
 */
public class StoreSlot extends Slot {

    public StoreSlot(int x, int y) {
        super(x, y);
    }

    @Override
    public void click() {
        if (Slot.inHand == null && Declaration.dungeon.player.money >= item.price) {
            Slot.inHand = item;
            Declaration.dungeon.player.money -= item.price;
        }
        System.out.println(Declaration.dungeon.player.money);
    }

    @Override
    public void rclick() {

    }

    @Override
    public void render() {
        super.render();
        if (item != null) {
            Main.g.setColor(Color.white);
            Main.defaultFont.drawString(item.price + "", (int) (x - 4 + width / 2), (int) (y + 32), org.tmd.render.Color.white);
        }
    }
}
