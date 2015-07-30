/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.Point;
import org.tmd.environment.entities.Entity;

/**
 *
 * @author Whizzpered
 */
public abstract class Target extends Ability {

    public Target(Entity owner, int cooldown) {
        super(owner, cooldown);
    }

    public abstract void cast(int level, Point target);
}
