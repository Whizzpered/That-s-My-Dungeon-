/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import org.tmd.environment.particles.SlimeTrace;
import org.tmd.main.Main;

/**
 *
 * @author Whizzpered
 */
public class Slime extends Pet {

    public Slime(double x, double y, int level, Entity owner) {
        super(x, y, "slime", level, owner);
        this.level = level;
        clickable = true;
        this.owner = owner;
        faction = 1;
        range = 400;
        level = owner.level;
        initAbils();
    }

    @Override
    public void walk(double x, double y) {
        if (Main.RANDOM.nextInt(10) == 0) {
            dungeon.addParticle(new SlimeTrace(x, y));
        }
        super.walk(x, y);
    }
}
