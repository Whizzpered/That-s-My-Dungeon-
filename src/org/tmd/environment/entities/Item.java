/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import java.io.Serializable;
import org.tmd.environment.ItemType;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public abstract class Item implements Serializable {

    public int lvl;
    public ItemType type;
    public Sprite sprite;
    public Image icon;
    public String name;

    public Item(String name, int level) {
        this.name = name;
        lvl = level;
        sprite = new Sprite("items/" + name);
        icon = new Image("items/" + name + "/icon.png");
    }

    public abstract void modificate();

    public void render(Entity owner) {
        sprite.render(owner.side, owner.x, owner.y);
    }

    public void renderIcon(double x, double y) {
        icon.draw(x, y);
    }
}
