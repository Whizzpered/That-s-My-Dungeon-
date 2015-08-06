/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.Point;
import org.tmd.environment.abilities.Active;
import org.tmd.environment.abilities.Passive;
import org.tmd.environment.abilities.Target;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Raider;
import org.tmd.environment.entities.items.Modificator;
import org.tmd.main.Sounds;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Archer extends Raider {

    public Archer(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 400;
        attackDistance = 300;
        spriteStanding = new Sprite("creatures/archer");
        minimapIcon = new Image("minimap/archer.png");
    }

    @Override
    public void initAbilities() {
        abils[0] = new Target(thisClass, 400) {
            @Override
            public void cast(int level, Point target) {
                cd = cooldown;
                for (int i = 0; i < 3; i++) {
                    dungeon.entities.add(new Bullet(x, y + 32 * (i - 1), thisClass, target.x, target.y));
                }
            }

        };
        if (level > 2) {
            abils[1] = new Active(thisClass, 600) {
                @Override
                public void cast(int level, Entity ent) {
                    focus.modificatorTypes.add(Modificator.ARMOR);
                    focus.modificators.add(-1f - level);
                }
            };
        }
        if (level > 5) {
            abils[2] = new Passive(thisClass) {
                @Override
                public void cast(int level, Entity ent) {
                    by.modificatorTypes.add(Modificator.ARMOR);
                    by.modificators.add(3f + level * 2f);
                    by.modificatorTypes.add(Modificator.DAMAGE);
                    by.modificators.add(4f + level * 2f);
                }
            };
        }
    }

    @Override
    public void abilities() {
        if (abils[0].isReady()) {
            ((Target) abils[0]).cast(level, new Point(dungeon.player.x, dungeon.player.y));
        }
    }

    @Override
    public void attackMethod(Entity e) {
        Sounds.play("bow");
        dungeon.entities.add(new Bullet(x, y, this, e.x, e.y));
    }

}
