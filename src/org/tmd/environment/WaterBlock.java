/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import static org.tmd.environment.Block.BLOCK_HEIGHT;
import static org.tmd.environment.Block.BLOCK_WIDTH;
import static org.tmd.main.Main.RANDOM;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class WaterBlock extends Block {

    private static float f;
    final Image water;

    public WaterBlock(int index, char symbol, String water, String border, String borderAngle, String borderAngleInside, boolean solid, boolean enemyZone, boolean restZone) {
        super(index, symbol, new Color(0, 0, 255, 100), null, null, border, borderAngle, borderAngleInside, solid, enemyZone, restZone);
        this.water = new Image("tiles/" + water);
    }

    private static float sign(int x, int y) {
        return ((x % 2 == 0 ? 1 : -1) * (y % 2 == 0 ? 1 : -1)) * 0.1f;
    }

    public void render(int border, int x, int y) {
        f += 0.001f;
        water.bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glBegin(GL_QUADS);
        {
            glTexCoord2d(sin(f) * sign(x, y), cos(f) * sign(x, y));
            glVertex2d(0, 0);
            glTexCoord2d(1 + sin(f) * sign(x+1, y), cos(f) * sign(x+1, y));
            glVertex2d(BLOCK_WIDTH, 0);
            glTexCoord2d(1 + sin(f) * sign(x+1, y+1), 1 + cos(f) * sign(x+1, y+1));
            glVertex2d(BLOCK_WIDTH, BLOCK_HEIGHT);
            glTexCoord2d(sin(f) * sign(x, y+1), 1 + cos(f) * sign(x, y+1));
            glVertex2d(0, BLOCK_HEIGHT);
        }
        glEnd();
        
        super.render(border, x, y);
    }

}
