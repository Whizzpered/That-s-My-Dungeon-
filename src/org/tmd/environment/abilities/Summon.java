/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Pet;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Summon extends Active {

    public Summon() {
        super(Declaration.dungeon.player, 200);
    }

    public void cast(int level, Entity by) {
        by.dungeon.entities.add(new Pet(by.x, by.y, "slime", level, by));
    }
}
