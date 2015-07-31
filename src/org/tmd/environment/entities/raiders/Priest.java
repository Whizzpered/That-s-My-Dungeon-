/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.abilities.Active;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Raider;
import org.tmd.environment.particles.FloatingText;
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
