/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.Condition;
import org.tmd.environment.abilities.Active;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Pet;
import org.tmd.environment.entities.Raider;
import org.tmd.environment.entities.items.Effect;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Assasin extends Raider {

    Pet pet;

    public Assasin(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 400;
        attackDistance = 96;
        spriteStanding = new Sprite("creatures/pries");
        minimapIcon = new Image("minimap/warrior.png");
    }

    @Override
    public void initAbilities() {
        abils[0] = new Active(thisClass, 600) {
            @Override
            public void cast(int level, Entity owner) {
                this.cd = this.cooldown;
                this.conting = 100 + 50 * level;
                by.effects.add(new Effect(conting, level, by) {

                    @Override
                    public void apply() {
                        by.clickable = false;
                    }

                    @Override
                    public void unapply() {
                        by.clickable = true;
                    }
                });
            }
        };
        if (level > 2) {
            abils[1] = new Active(thisClass, 800) {
                @Override
                public void cast(int level, Entity by) {
                    conting = 150;
                    this.cd = this.cooldown;
                    focus.effects.add(new Effect(conting, level, by) {

                        @Override
                        public void apply() {
                            focus.hp -= thisClass.level * 0.5f;
                        }

                        @Override
                        public void unapply() {

                        }
                    });
                }
            };
        }
        if (level > 4) {
            abils[2] = new Active(thisClass, 900) {
                @Override
                public void cast(int level, Entity by) {
                    conting = 330;
                    this.cd = this.cooldown;
                    pet = new Pet(thisClass.x, thisClass.y, "dog", (int)(level / 2), thisClass);
                    dungeon.entities.add(pet);
                }

                @Override
                public void exduration() {
                    dungeon.entities.remove(pet);
                }
            };
        }
    }

    @Override
    public void abilities() {
        if ((condition == Condition.BATTLE || condition == Condition.NOTANKS) && abils[0].isReady()) {
            ((Active) abils[0]).cast(level, thisClass);
        }
        if(level>2 && abils[1].isReady() && focus != null){
            ((Active)abils[1]).cast(level, thisClass);
        }
        if(level > 5 && abils[2].isReady()){
            ((Active)abils[2]).cast(level, thisClass);
        }
    }

    @Override
    public void render() {
        if (clickable && !dead) {
            super.render();
        }
    }
}
