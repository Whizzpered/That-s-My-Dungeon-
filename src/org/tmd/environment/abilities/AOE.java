/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.abilities;

import org.tmd.environment.Point;
import org.tmd.environment.entities.BulletRoar;
import org.tmd.environment.entities.aoe.PortalToHell;
import org.tmd.main.Declaration;
import org.tmd.main.Sounds;

/**
 *
 * @author Whizzpered
 */
public class AOE extends Target{

    public AOE() {
        super(Declaration.dungeon.player, 20);
    }

    @Override
    public void cast(int level, Point target) {
        by.dungeon.entities.add(new PortalToHell(target.x, target.y, level, by));
    }
}
