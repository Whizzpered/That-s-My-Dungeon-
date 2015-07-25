/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.Point;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.BulletRoar;
import org.tmd.environment.entities.Entity;
import org.tmd.main.Sounds;

/**
 *
 * @author yew_mentzaki
 */
public class Roar extends Target{

    public Roar() {
        super(100);
    }

    @Override
    public void cast(int level, Entity by, Point target) {
        by.dungeon.entities.add(new BulletRoar(level, by.x, by.y, by, target.x, target.y));
        by.dungeon.quake = 150;
        Sounds.play("roar");
    }
    
}
