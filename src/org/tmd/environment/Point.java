/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import java.io.Serializable;

/**
 *
 * @author yew_mentzaki
 */
public class Point implements Serializable{

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }
    
    public double x, y;
}
