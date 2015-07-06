/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import static org.tmd.environment.Block.BLOCK_HEIGHT;
import static org.tmd.environment.Block.BLOCK_WIDTH;
import static org.tmd.main.Main.RANDOM;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class WaterBlock extends Block {

    final Image water;

    public WaterBlock(int index, char symbol, String water, String border, String borderAngle, String borderAngleInside, boolean solid, boolean enemyZone, boolean restZone) {
        super(index, symbol, null, null, border, borderAngle, borderAngleInside, solid, enemyZone, restZone);
        this.water = new Image("tiles/" + water);
    }

    @Override
    public void render(int border) {
        water.draw(RANDOM.nextInt(3), 0, BLOCK_WIDTH, BLOCK_HEIGHT);
        super.render(border);
    }

    
}
