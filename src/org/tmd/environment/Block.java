/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class Block {
    
    public static Block[] blocks = new Block[256];

    char symbol;
    Image wall;
    Image top;
    boolean solid;
    boolean enemyZone;
    boolean restZone;

    public Block(char symbol, String wall, String top, boolean solid, boolean enemyZone, boolean restZone) {
        this.symbol = symbol;
        this.wall = new Image(wall);
        this.top = new Image(top);
        this.solid = solid;
        this.enemyZone = enemyZone;
        this.restZone = restZone;
    }
}
