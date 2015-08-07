/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Pet;
import org.tmd.environment.entities.Slime;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Summon extends Active {

    public Pet pet;
    
    public Summon() {
        super(Declaration.dungeon.player, 20);
    }

    public void cast(int level, Entity by) {
        pet = new Slime(by.x, by.y, level, by);
        by.dungeon.entities.add(pet);
        conting = 14;
    }

    @Override
    public void exduration() {
        by.dungeon.entities.remove(pet);
    }
}
