/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.abilities.Active;
import org.tmd.environment.abilities.Passive;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Pet;
import org.tmd.environment.entities.Raider;
import org.tmd.environment.entities.items.Modificator;
import org.tmd.environment.particles.FloatingText;
import org.tmd.main.Main;
import org.tmd.main.Sounds;
import org.tmd.render.Color;
import org.tmd.render.Image;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Priest extends Raider {

    public Priest(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 400;
        attackDistance = 300;
        spriteStanding = new Sprite("creatures/priest");
        minimapIcon = new Image("minimap/priest.png");
    }

    @Override
    public void initAbilities() {
        abils[0] = new Active(thisClass, 200) {
            public void heal(Entity r) {
                r.hp += level * 10;
                if (r.hp > r.maxhp) {
                    r.hp = r.maxhp;
                }
                this.cd = this.cooldown;
                dungeon.addParticle(new FloatingText((int) r.x, (int) r.y - 35, "+ " + (int) level * 10, Color.green));
            }

            public void cast(int level, Entity owner) {
                for (Raider r : dungeon.getRaiders()) {
                    if (r instanceof Warrior) {
                        if (dungeon.player.focus == r && r.hp < r.maxhp) {
                            heal(r);
                            return;
                        } else {
                            if (r.hp < r.maxhp) {
                                heal(r);
                                return;
                            }
                        }
                    }
                }
                for (Raider r : dungeon.getRaiders()) {
                    if (r.hp < r.maxhp) {
                        heal(r);
                        return;
                    }
                }
            }
        };
        if (level > 2) {
            abils[1] = new Passive(thisClass) {
                @Override
                public void cast(int level, Entity by) {
                    for (Entity ent : dungeon.getEntities()) {
                        if (ent instanceof Raider
                                || (ent instanceof Pet && ((Pet) ent).owner instanceof Raider)) {
                            if (ent.hp < ent.getMaxHP()) {
                                ent.hp += 0.02 + level / 100;
                            }
                        }
                    }
                }

                public void tick() {
                    cast(thisClass.level, thisClass);
                }
            };
        }
        if (level > 4) {
            abils[2] = new Active(thisClass, 900) {
                @Override
                public void cast(int level, Entity ent) {
                    switch (Main.RANDOM.nextInt(3)) {
                        case (0):
                            dungeon.entities.add(new Warrior(dungeon.raidersRespawnPoint.x, dungeon.raidersRespawnPoint.y, dungeon.wave));
                            break;
                        case (1):
                            dungeon.entities.add(new Assasin(dungeon.raidersRespawnPoint.x, dungeon.raidersRespawnPoint.y, dungeon.wave));
                            break;
                        case (2):
                            dungeon.entities.add(new Archer(dungeon.raidersRespawnPoint.x, dungeon.raidersRespawnPoint.y, dungeon.wave));
                            break;
                        case (3):
                            dungeon.entities.add(new Priest(dungeon.raidersRespawnPoint.x, dungeon.raidersRespawnPoint.y, dungeon.wave));
                            break;
                    }
                }
            };
        }
    }

    @Override
    public void abilities() {
        if (abils[0].isReady()) {
            ((Active) abils[0]).cast(level, thisClass);
        }
    }

    @Override
    public void attackMethod(Entity e) {
        Sounds.play("magic");
        dungeon.entities.add(new Bullet(x, y, this, e));
    }

}
