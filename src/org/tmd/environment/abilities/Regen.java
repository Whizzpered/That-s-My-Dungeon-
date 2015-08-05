/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.items.Modificator;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Regen extends Active {

    public Regen() {
        super(Declaration.dungeon.player, 500);
    }

    public void cast(int level, Entity by) {
        conting = 200;
        index = by.effects.size();
        by.effTypes.add(Modificator.REGENHP);
        by.effects.add(0.05f + 0.03f * level);
    }

    @Override
    public void exduration() {
        by.effects.remove(index);
        by.effTypes.remove(index);
    }
}
