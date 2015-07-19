/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment;

import java.util.Random;
import static org.tmd.environment.Block.BLOCK_HEIGHT;
import static org.tmd.environment.Block.BLOCK_WIDTH;
import org.tmd.render.Color;
import org.tmd.render.Image;

/**
 *
 * @author Whizzpered
 */
public class AreaBlock extends Block {

    public final Image left, down, right, downLeft, downRight, alone;
    Random r = new Random();

    public AreaBlock(int index, char symbol, Color color, String sprite, boolean enemyZone, boolean restZone) {
        super(index, symbol, color, sprite + "/center.png", false, enemyZone, restZone);
        left = new Image("tiles/" + sprite + "/left.png");
        right = new Image("tiles/" + sprite + "/right.png");
        downLeft = new Image("tiles/" + sprite + "/down-left.png");
        downRight = new Image("tiles/" + sprite + "/down-right.png");
        border = new Image("tiles/" + sprite + "/top.png");
        down = new Image("tiles/" + sprite + "/down.png");
        borderAngle = new Image("tiles/" + sprite + "/top-left.png");
        borderAngleInside = new Image("tiles/" + sprite + "/top-right.png");
        alone = new Image("tiles/" + sprite + "/alone" + /*(r.nextInt(6)) +*/ ".png");
    }

    @Override
    public void render(int border, int x, int y) {
        if ((r(border, 1) & r(border, 2) & (r(border, 4) & r(border, 8)))) {
            this.alone.draw(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT);
            return;
        }

        if ((r(border, 1) & r(border, 2))) {
            this.borderAngle.draw(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT);
            return;
        }
        if ((r(border, 2) & r(border, 4))) {
            this.borderAngleInside.draw(0, 0, BLOCK_WIDTH, BLOCK_WIDTH);
            return;
        }
        if ((r(border, 4) & r(border, 8))) {
            this.downRight.draw(0, 0, BLOCK_WIDTH, BLOCK_WIDTH);
            return;
        }
        if ((r(border, 8) & r(border, 1))) {
            this.downLeft.draw(0, 0, BLOCK_WIDTH, BLOCK_WIDTH);
            return;
        }

        if (r(border, 1)) {
            this.left.draw(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT);
            return;
        }
        if (r(border, 2)) {
            this.border.draw(0, 0, BLOCK_WIDTH, BLOCK_WIDTH);
            return;
        }
        if (r(border, 4)) {
            this.right.draw(0, 0, BLOCK_WIDTH, BLOCK_WIDTH);
            return;
        }
        if (r(border, 8)) {
            this.down.draw(0, 0, BLOCK_WIDTH, BLOCK_WIDTH);
            return;
        }

        if (wall != null) {
            wall.draw(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT);
        }
    }
}
