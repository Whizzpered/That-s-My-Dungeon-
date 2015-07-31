/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.abilities.Ability;
import org.tmd.environment.abilities.Active;
import org.tmd.environment.entities.*;
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
        abils[0] = new Active(thisClass, 300) {
            @Override
            public void cast(int level, Entity ent) {
                dungeon.player.agro = thisClass;
                dungeon.player.focus = thisClass;
                conting = 150;
                cd = cooldown;
            }

            @Override
            public void duration() {
                agro = true;
                dungeon.player.agro = thisClass;
                dungeon.player.focus = thisClass;
            }

            @Override
            public void exduration() {
                agro = false;
                dungeon.player.agro = null;
            }
        };

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
