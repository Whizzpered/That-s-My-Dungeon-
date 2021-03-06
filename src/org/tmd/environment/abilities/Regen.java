/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.items.Effect;
import org.tmd.environment.entities.items.Modificator;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Regen extends Active {
    
    public Regen() {
        super(Declaration.dungeon.player, 20);
    }

    public void cast(int level, Entity by) {
        if(level > 2){
            by.hp += by.getMaxHP()/3;
        }
        conting = 200;
        by.effects.add(new Effect(conting, level, by) {
            
            @Override
            public void apply() {
                target.regenhp += 6f + 2f * coefficient;
            }
            
            @Override
            public void unapply() {
                target.regenhp -= 6f + 2f * coefficient;
            }
        });
    }
}
