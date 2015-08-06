/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.abilities.Active;
import org.tmd.environment.abilities.Passive;
import org.tmd.environment.entities.*;
import org.tmd.environment.entities.items.Modificator;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Warrior extends Raider {

    boolean agro;
    Image agronomic = null;

    public Warrior(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 400;
        attackDistance = 96;
        spriteStanding = new Sprite("creatures/warrior");
        minimapIcon = new Image("minimap/warrior.png");
    }

    @Override
    public void initAbilities() {
        abils[0] = new Active(thisClass, 900) {
            @Override
            public void cast(int level, Entity ent) {
                dungeon.player.agro = thisClass;
                dungeon.player.focus = thisClass;
                conting = 150;
                this.cd = this.cooldown;
            }

            @Override
            public void duration() {
                if (dead) {
                    agro = false;
                    dungeon.player.agro = null;
                }
            }

            @Override
            public void exduration() {
                agro = false;
                dungeon.player.agro = null;
            }
        };

        if (level > 2) {
            abils[1] = new Passive(thisClass) {
                @Override
                public void cast(int level, Entity ent) {
                    by.effTypes.add(Modificator.REGENHP);
                    by.effects.add(0.03f + level * 0.03f);
                    by.effTypes.add(Modificator.ARMOR);
                    by.effects.add(2f + level * 2f);
                }
            };
        }
        if (level > 4) {
            abils[2] = new Active(thisClass, 900) {
                @Override
                public void cast(int level, Entity ent) {
                    conting = 150;
                    this.cd = this.cooldown;
                    by.effTypes.add(Modificator.DAMAGE);
                    by.effects.add(3f + level * 2f);
                    by.effTypes.add(Modificator.MOVESPEED);
                    by.effects.add(1f + level);
                }

                public void exduration() {
                    //тут надо удалить эффекты выше
                }
            };
        }

    }

    @Override
    public void abilities() {
        if (abils[0].isReady()) {
            double dist = Math.sqrt(Math.pow(x - dungeon.player.x, 2) + Math.pow(y - dungeon.player.y, 2));
            if (dist < 500 && dungeon.player.agro == null) {
                ((Active) (abils[0])).cast(level, thisClass);
            }
        }
    }

    @Override
    public void dead() {
        super.dead();
        agro = false;
        dungeon.player.agro = null;
    }

    @Override
    public void render() {
        if (agronomic == null) {
            agronomic = new Image("effects/agro.png").getNxCopy(2f);
        }
        super.render();
        if (agro) {
            agronomic.draw(x, y - height * 2);
        }
    }
}
