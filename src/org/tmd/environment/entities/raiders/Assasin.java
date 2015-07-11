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
public class Assasin extends Raider{
    public Assasin(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 96;
    }
}
