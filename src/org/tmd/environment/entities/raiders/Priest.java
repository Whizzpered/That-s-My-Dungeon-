/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.entities.Raider;

/**
 *
 * @author Whizzpered
 */
public class Priest extends Raider{
    
    public Priest(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 200;
    }
}
