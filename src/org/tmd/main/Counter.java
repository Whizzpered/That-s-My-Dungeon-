/* Copyright (C) 2015, SHeart.  All rights reserved.
 * ______________________________________________________________________________
 * This program is proprietary software: decompiling, reverse engineering and
 * sharing of that code are denied.
 */
package org.tmd.main;

import java.io.Serializable;

/**
 *
 * @author Whizzpered
 */
public class Counter implements Serializable {

    public int period, tick;

    public Counter(int period) {
        this.period = period;
    }

    public void start() {
        tick = period;
    }

    public int get() {
        return tick;
    }

    public boolean is() {
        if (tick == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void tick() {
        if (tick > 0) {
            tick--;
        }
    }

}
