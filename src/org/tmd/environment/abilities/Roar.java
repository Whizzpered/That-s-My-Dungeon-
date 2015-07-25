/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.Point;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;

/**
 *
 * @author yew_mentzaki
 */
public class Roar extends Target{

    public Roar() {
        super(1000);
    }

    @Override
    public void cast(int level, Entity by, Point target) {
        by.dungeon.entities.add(new Bullet(by.x, by.y, by, target.x, target.y));
    }
    
}
