/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.Point;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.particles.RoarWave;
import org.tmd.environment.particles.SuperRoarWave;
import org.tmd.main.Declaration;
import org.tmd.main.Sounds;

/**
 *
 * @author yew_mentzaki
 */
public class Roar extends Target {

    double damage = 15;
    double deltaDamage = 5;
    double distance = 250;
    double deltaDistance = 50;

    public Roar() {
        super(Declaration.dungeon.player, 20);
    }

    @Override
    public void cast(int level, Point target) {
        double a = Math.atan2(target.y - by.y, target.x - by.x);
        double d = distance + level * deltaDamage;
        double x = by.x + Math.cos(a) * d;
        double y = by.y + Math.sin(a) * d;
        if (level > 2) {
            by.dungeon.addParticle(new SuperRoarWave(by.x, by.y - 65, target.x, target.y));
            for (float i = 0; i < Math.PI * 2; i += Math.PI / 4) {

                by.dungeon.addParticle(new RoarWave(by.x, by.y - 65, by.x + Math.cos(i) * 10, by.y - 65 + Math.sin(i) * 10));
            }
        } else {
            by.dungeon.addParticle(new RoarWave(by.x, by.y - 65, target.x, target.y));
        }
        for (Entity e : by.dungeon.getEntities()) {
            if (e.faction == 2) {
                if (Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2)) <= d
                        && Math.sqrt(Math.pow(by.x - e.x, 2) + Math.pow(by.y - e.y, 2)) <= d) {
                    e.hit(damage + deltaDamage * level, by);
                }
                if (level > 2) {
                    if (Math.sqrt(Math.pow(by.x - e.x, 2) + Math.pow(by.y - e.y, 2)) <= d) {
                        e.hit(damage + deltaDamage * level, by);
                    }
                }
            }
        }
        by.dungeon.quake = 150;
        Sounds.play("roar");
    }

}
