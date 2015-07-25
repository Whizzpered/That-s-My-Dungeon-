/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import java.io.Serializable;
import org.tmd.environment.Point;
import org.tmd.environment.entities.Entity;
import org.tmd.main.Counter;
import org.tmd.render.gui.AbilityButton;

/**
 *
 * @author Whizzpered
 */
public abstract class Ability implements Serializable{
    public int cooldown;

    public Ability(int cooldown) {
        this.cooldown = cooldown;
    }
}
