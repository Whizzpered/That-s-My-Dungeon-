
package org.tmd.environment.entities;

import java.io.Serializable;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import org.tmd.environment.ItemType;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public abstract class Item extends Coin implements Serializable {

    public int lvl, price;
    public ItemType type;
    public Sprite sprite;
    public Image icon;
    public String name;
    public static Image glow = new Image("effects/light.png");
    
    public Item(String name, int level) {
        super(0, 0);
        this.name = name;
        lvl = level;
        sprite = new Sprite("items/" + name);
        icon = new Image("items/" + name + "/icon.png");
    }

    public abstract void modificate();
    
    public void drop(Entity owner){
        this.x = owner.x;
        this.y = owner.y;
        this.dungeon = owner.dungeon;
        dungeon.entities.add(this);
    }

    public void render(Entity owner) {
        sprite.render(owner.side, owner.x, owner.y);
    }

    @Override
    public void render() {
        glow.draw(x - 64, y - 64);
        icon.draw(x - icon.width, y - icon.height, icon.width * 2, icon.height * 2);
    }

    public void renderIcon(double x, double y) {
        icon.draw(x, y);
    }
}
