/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.entities.Entity;
import org.tmd.render.gui.AbilityButton;

/**
 *
 * @author Whizzpered
 */
public abstract class Active extends Ability {

    public Active(int cooldown) {
        super(cooldown);
    }

    public abstract void cast(int level, Entity ent);
}
