/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities.aoe;

import org.tmd.environment.entities.AreaOfEffect;
import org.tmd.environment.entities.Entity;

/**
 *
 * @author yew_mentzaki
 */
public class PortalToHell extends AreaOfEffect{
    
    Entity by;
    
    public PortalToHell(double x, double y, int level, Entity by) {
        super(x, y, 200 + level * 50, 255);
        isometric = true;
        this.by = by;
    }

    @Override
    public boolean goodTarget(Entity e) {
        return e.faction == 2;
    }

    @Override
    public void cast(Entity e) {
        e.hit(1, by);
    }
    
}
