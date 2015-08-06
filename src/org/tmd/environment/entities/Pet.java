/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import org.tmd.environment.particles.Hit;
import org.tmd.environment.particles.LevelUp;
import org.tmd.environment.particles.SlimeTrace;
import org.tmd.main.Main;
import org.tmd.render.Sprite;

/**
 *
 * @author Whizzpered
 */
public class Pet extends Entity {

    public Entity owner;
    public int comeTimer = 3000;
    public double range;

    public Pet(double x, double y, String sprite, int level, Entity owner) {
        super(x, y);
        spriteStanding = new Sprite("creatures/" + sprite);
        name = sprite;
        this.level = level;
        maxhp = 30;
        deltahp = 20;
        attackDamage = 4;
        attackDeltaDamage = 3;
        clickable = true;
        this.owner = owner;
        faction = 1;
        speed = 1;
        range = 400;
        level = owner.level;
        this.attackDistance = (int) width / 2;
        initAbils();
    }

    public void initAbils() {

    }

    @Override
    public void walk(double x, double y) {
        if (Main.RANDOM.nextInt(10) == 0) {
            dungeon.addParticle(new SlimeTrace(x, y));
        }
        super.walk(x, y);
    }

    @Override
    public void tick() {
        super.tick();
        comeTimer--;
        if (comeTimer <= 0) {
            hp = 0;
        }
        patrool();
    }

    @Override
    public void attack(Entity e) {
        if (attackReload == 0) {
            if (sqrt(pow(e.x - x, 2) + pow(e.y - y, 2)) < attackDistance) {
                dungeon.addParticle(new Hit(attackType, e.x, e.y - 35));
                boolean killed = e.hit(attackDamage, this);
                if (killed) {
                    level++;
                    dungeon.addParticle(new LevelUp(this));
                }
                attackReload = attackReloadTime;
            }
        }
    }

    public void patrool() {
        if (focus == null) {
            for (Entity e : dungeon.getEntities()) {
                if (e instanceof Raider && !e.dead) {
                    double dist = Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2));
                    if (dist <= range) {
                        focus = e;
                        return;
                    }
                }
            }
        } else {
            if (!focus.dead) {
                attack(focus);
                goTo(focus.x, focus.y);
                return;
            } else {
                focus = null;
            }
        }
        goTo(owner.x, owner.y);
    }
}
