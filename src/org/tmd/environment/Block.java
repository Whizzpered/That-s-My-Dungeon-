/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import static java.lang.Math.PI;
import org.tmd.render.Image;
import org.tmd.render.scenes.Dungeon;

/**
 *
 * @author yew_mentzaki
 */
public class Block {

    public static final double BLOCK_WIDTH = 96, BLOCK_HEIGHT = 96;

    public static Block[] blocks = new Block[Byte.MAX_VALUE];

    public final int index;
    public final char symbol;
    public final Image wall;
    public final Image top;
    public final Image border;
    public final Image borderAngle;
    public final Image borderAngleInside;
    public final boolean solid;
    public final boolean enemyZone;
    public final boolean restZone;

    private static boolean r(int a, int b) {
        return (a & b) != b;
    }

    public Block(int index, char symbol, String wall, boolean solid, boolean enemyZone, boolean restZone) {
        this.index = index;
        this.symbol = symbol;
        if (wall != null) {
            this.wall = new Image("tiles/" + wall);
        } else {
            this.wall = null;
        }
        this.top = null;
        this.border = null;
        this.borderAngle = null;
        this.borderAngleInside = null;
        this.solid = solid;
        this.enemyZone = enemyZone;
        this.restZone = restZone;
        Block.blocks[index] = this;
    }

    public Block(int index, char symbol, String wall, String top, boolean solid, boolean enemyZone, boolean restZone) {
        this.index = index;
        this.symbol = symbol;
        if (wall != null) {
            this.wall = new Image("tiles/" + wall);
        } else {
            this.wall = null;
        }
        if (top != null) {
            this.top = new Image("tiles/" + top);
        } else {
            this.top = null;
        }
        this.border = null;
        this.borderAngle = null;
        this.borderAngleInside = null;
        this.solid = solid;
        this.enemyZone = enemyZone;
        this.restZone = restZone;
        Block.blocks[index] = this;
    }

    public Block(int index, char symbol, String wall, String top, String border, String borderAngle, String borderAngleInside, boolean solid, boolean enemyZone, boolean restZone) {
        this.index = index;
        this.symbol = symbol;
        if (wall != null) {
            this.wall = new Image("tiles/" + wall);
        } else {
            this.wall = null;
        }
        if (top != null) {
            this.top = new Image("tiles/" + top);
        } else {
            this.top = null;
        }
        if (border != null) {
            this.border = new Image("tiles/" + border);
            this.borderAngle = new Image("tiles/" + borderAngle);
            this.borderAngleInside = new Image("tiles/" + borderAngleInside);
        } else {
            this.border = null;
            this.borderAngle = null;
            this.borderAngleInside = null;
        }
        this.solid = solid;
        this.enemyZone = enemyZone;
        this.restZone = restZone;
        Block.blocks[index] = this;
    }

    public void render(int border, int x, int y) {
        if (wall != null) {
            wall.draw(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT);
        }
        if (this.border != null && top == null) {
            if (r(border, 1)) {
                this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_HEIGHT, 0);
            }
            if (r(border, 2)) {
                this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2);
            }
            if (r(border, 4)) {
                this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI);
            }
            if (r(border, 8)) {
                this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2 * 3);
            }

            if ((r(border, 1) & r(border, 2))) {
                this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_HEIGHT, 0);
            }
            if ((r(border, 2) & r(border, 4))) {
                this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2);
            }
            if ((r(border, 4) & r(border, 8))) {
                this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI);
            }
            if ((r(border, 8) & r(border, 1))) {
                this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2 * 3);
            }

            if (!(r(border, 16))) {
                this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_HEIGHT, 0);
            }
            if (!(r(border, 32))) {
                this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2);
            }
            if (!(r(border, 64))) {
                this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI);
            }
            if (!(r(border, 128))) {
                this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2 * 3);
            }
        }
    }

    public void renderTop(int border, int x, int y) {
        if (top != null) {
            top.draw(0, -BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
            if (this.border != null) {
                if (r(border, 1)) {
                    this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, 0);
                }
                if (r(border, 2)) {
                    this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2);
                }
                if (r(border, 4)) {
                    this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI);
                }
                if (r(border, 8)) {
                    this.border.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2 * 3);
                }

                if ((r(border, 1) & r(border, 2))) {
                    this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, 0);
                }
                if ((r(border, 2) & r(border, 4))) {
                    this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2);
                }
                if ((r(border, 4) & r(border, 8))) {
                    this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI);
                }
                if ((r(border, 8) & r(border, 1))) {
                    this.borderAngle.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2 * 3);
                }

                if (!(r(border, 16))) {
                    this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, 0);
                }
                if (!(r(border, 32))) {
                    this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2);
                }
                if (!(r(border, 64))) {
                    this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI);
                }
                if (!(r(border, 128))) {
                    this.borderAngleInside.draw(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2 - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, PI / 2 * 3);
                }
            }
        }
    }

    public void parserAction(Dungeon dungeon, double x, double y) {

    }
}
