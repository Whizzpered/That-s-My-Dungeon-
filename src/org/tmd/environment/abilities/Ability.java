/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import java.io.Serializable;
import org.tmd.environment.entities.Entity;

/**
 *
 * @author Whizzpered
 */
public abstract class Ability implements Serializable {

    public int cooldown, conting, cd;
    public boolean cont;
    public Entity by;

    public boolean isReady() {
        return (cd <= 0);
    }

    public Ability(Entity owner, int cooldown) {
        this.cooldown = cooldown;
        this.by = owner;
    }

    public void tick() {
        if (cd > 0) {
            cd--;
        }
    }
}
