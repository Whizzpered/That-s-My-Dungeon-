/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.tmd.render.Color;
import org.tmd.environment.Block;
import org.tmd.environment.entities.Bullet;
import org.tmd.environment.entities.Entity;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.scenes.Dungeon;

/**
 *
 * @author yew_mentzaki
 */
public class MiniMap extends Element {

    Dungeon dungeon;
    Image image = new Image("minimap/block.png");

    public MiniMap(double x, double y, double width, double height, Dungeon dung) {
        super(x, y, width, height);
        dungeon = dung;
    }

    @Override
    public void render() {
        Frame.glassFrame.render(getX(), getY(), width, height);
        double dx = 16 + getX(), dy = 16 + getY();
        double dw = (width - 32) / ((double) dungeon.terrain.width * Block.BLOCK_WIDTH);
        double dh = (height - 32) / ((double) dungeon.terrain.height * Block.BLOCK_HEIGHT);
        image.bind();
        for (int x = 0; x < dungeon.terrain.width; x++) {
            for (int y = 0; y < dungeon.terrain.height; y++) {
                /*
                 Main.g.setColor(dungeon.terrain.get(x, y).color.slickColor());
                 Main.g.fillRect((float)(dx + x * dw * Block.BLOCK_WIDTH), (float)(dy + y * dh * Block.BLOCK_HEIGHT), (float)(dw * Block.BLOCK_WIDTH), (float)(dh * Block.BLOCK_HEIGHT));
                 */
                dungeon.terrain.get(x, y).color.bind();
                glBegin(GL_QUADS);
                {
                    glTexCoord2d(0, 0);
                    glVertex2d((float)(dx + x * dw * Block.BLOCK_WIDTH), (float)(dy + y * dh * Block.BLOCK_HEIGHT));
                    glTexCoord2d(1, 0);
                    glVertex2d((float)(dx + x * dw * Block.BLOCK_WIDTH) + dw * Block.BLOCK_WIDTH, (float)(dy + y * dh * Block.BLOCK_HEIGHT));
                    glTexCoord2d(1, 1);
                    glVertex2d((float)(dx + x * dw * Block.BLOCK_WIDTH) + dw * Block.BLOCK_WIDTH, (float)(dy + y * dh * Block.BLOCK_HEIGHT)+dh * Block.BLOCK_HEIGHT);
                    glTexCoord2d(0, 1);
                    glVertex2d((float)(dx + x * dw * Block.BLOCK_WIDTH), (float)(dy + y * dh * Block.BLOCK_HEIGHT)+dh * Block.BLOCK_HEIGHT);
                }
                glEnd();
            }
        }
        for (Entity e : dungeon.getEntities()) {
            if (e.minimapIcon != null && !(e instanceof Bullet)) {
                e.minimapIcon.draw(dx + (e.x * dw) - e.minimapIcon.width / 2, dy + (e.y * dh) - e.minimapIcon.height / 2);
            }
        }
    }
}
