/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.entities.Entity;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class Regen extends Active {

    public Regen() {
        super(Declaration.dungeon.player, 500);
        cont = true;
    }

    public void cast(int level, Entity by) {
        conting = 200;
    }

    @Override
    public void tick() {
        if (conting > 0) {
            conting--;
            by.regenhp = by.nativeregenhp * 2;
        }
    }
}
