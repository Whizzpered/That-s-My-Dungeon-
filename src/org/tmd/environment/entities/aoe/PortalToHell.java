/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities.aoe;

import org.tmd.environment.entities.AreaOfEffect;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.particles.FloatingText;
import org.tmd.environment.particles.Hell;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;

/**
 *
 * @author yew_mentzaki
 */
public class PortalToHell extends AreaOfEffect {

    Entity by;

    public PortalToHell(double x, double y, int level, Entity by) {
        super(x, y, 200 + level * 50, 255, 5);
        isometric = true;
        this.by = by;
    }

    @Override
    public void tick() {
        super.tick();
        if (Main.RANDOM.nextInt(10) == 0) {
            double a = Main.RANDOM.nextDouble() * Math.PI * 2;
            double d = Main.RANDOM.nextInt((int) distance);
            dungeon.addParticle(new Hell(x + Math.cos(a) * d, y + Math.sin(a) * d));
        }
    }

    @Override
    public boolean goodTarget(Entity e) {
        return e.faction == 2;
    }

    @Override
    public void cast(Entity e) {
        e.hp -= 0.75 + 0.5 * level;
        Declaration.dungeon.addParticle(new FloatingText((int) x, (int) y - 35, "- " + (int) 0.75 + 0.5 * level, Color.orange));
    }

    @Override
    public void render() {

    }
}
