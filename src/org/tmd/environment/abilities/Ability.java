/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.Point;
import org.tmd.environment.entities.Entity;
import org.tmd.main.Counter;
import org.tmd.render.gui.AbilityButton;

/**
 *
 * @author Whizzpered
 */
public class Ability {

    public Counter cd;
    AbilityButton but;
    boolean active;
    public double angle;

    public Ability(AbilityButton own, int cooldown) {
        but = own;
        this.cd = new Counter(cooldown);
    }

    public void cast(int level, Entity by, Point target) {

    }

    public void cast(int level, Entity by) {

    }
    
    public void tick() {
        if(!cd.is()){
            cd.tick();
        }
    }
    
    public void render() {
        
    }
}
