package org.tmd.environment.entities.items;

import java.io.Serializable;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import org.tmd.environment.entities.Coin;
import org.tmd.environment.entities.Entity;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Item extends Coin implements Serializable {

    public int lvl, price;
    public ItemType type;
    public Sprite sprite;
    public Image icon;
    public String name;
    public static Image glow = new Image("effects/light.png");
    public ArrayList<Modificator> modificators = new ArrayList<Modificator>();
    public ArrayList<Float> modificatorsvalue = new ArrayList<Float>();

    public Item(String name, int level) {
        super(0, 0);
        this.name = name;
        lvl = level;
        sprite = new Sprite("items/" + name);
        icon = new Image("items/" + name + "/icon.png");
        modificators.add(Modificator.ARMOR);
        modificatorsvalue.add((float) (level + Main.RANDOM.nextInt(3)));
        boolean hp = false;
        boolean damage = false;
        boolean attackspeed = false;
        boolean regenhp = false;
        for (int i = 0; i < Main.RANDOM.nextInt(4); i++) {
            while (true) {
                switch (Main.RANDOM.nextInt(4)) {
                    case 0:
                        if (hp) {
                            continue;
                        }
                        hp = true;
                        modificators.add(Modificator.HP);
                        modificatorsvalue.add((float) level);
                        break;
                    case 1:
                        if (damage) {
                            continue;
                        }
                        damage = true;
                        modificators.add(Modificator.DAMAGE);
                        modificatorsvalue.add((float) level);
                        break;
                    case 2:
                        if (attackspeed) {
                            continue;
                        }
                        attackspeed = true;
                        modificators.add(Modificator.ATTACKSPEED);
                        modificatorsvalue.add((float) level);
                        break;
                    case 3:
                        if (regenhp) {
                            continue;
                        }
                        regenhp = true;
                        modificators.add(Modificator.REGENHP);
                        modificatorsvalue.add((float) level);
                        break;
                    default:
                }
                break;
            }
        }
    }

    public void drop(Entity owner) {
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
