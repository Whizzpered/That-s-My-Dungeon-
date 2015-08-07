/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.abilities.Active;
import org.tmd.environment.entities.*;
import org.tmd.environment.entities.items.Effect;
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
        abils[0] = new Active(thisClass, 18) {
            @Override
            public void cast(int level, Entity ent) {
                dungeon.player.agro = thisClass;
                dungeon.player.focus = thisClass;
                conting = 10;
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
            effects.add(new Effect(thisClass, level, this) {

                @Override
                public void apply() {
                    this.owner.regenhp += 0.05f + 0.03f * coefficient;
                }

                @Override
                public void unapply() {
                }
            });
        }
        if (level > 4) {
            abils[2] = new Active(thisClass, 18) {
                @Override
                public void cast(int level, Entity ent) {
                    conting = 10;
                    this.cd = this.cooldown;
                    by.effects.add(new Effect(conting, level, by) {

                        @Override
                        public void apply() {
                            by.attackDamage += 3f + coefficient * 2f;
                        }

                        @Override
                        public void unapply() {
                            by.regenhp -= 3f + coefficient * 2f;
                        }
                    });
                    by.effects.add(new Effect(conting, level, by) {

                        @Override
                        public void apply() {
                            by.speed += 1f + coefficient;
                        }

                        @Override
                        public void unapply() {
                            by.speed -= 1f + coefficient;
                        }
                    });
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
        if (level > 4 && abils[2] != null && abils[2].isReady() && focus != null) {
            ((Active) abils[2]).cast(level, thisClass);
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
