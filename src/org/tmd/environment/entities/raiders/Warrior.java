/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import org.tmd.environment.entities.*;
import org.tmd.main.Counter;

/**
 *
 * @author Whizzpered
 */
public class Warrior extends Raider {

    public Warrior(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 96;
        counter = new Counter(300);
    }
}
