/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import static org.tmd.environment.Block.BLOCK_WIDTH;
import static org.tmd.environment.Block.BLOCK_HEIGHT;
import org.tmd.render.scenes.Dungeon;

/**
 *
 * @author yew_mentzaki
 */
public class Terrain {

    public int width, height;
    byte[][] blocks;
    byte[][] borders;

    public Terrain(Dungeon dungeon, String file) {
        try {
            File f = new File(file);
            int w = 0, h = 0, b[][] = new int[256][256];
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                h++;
                char[] n = s.nextLine().toCharArray();
                if (n.length > w) {
                    w = n.length;
                }
                for (int i = 0; i < n.length; i++) {
                    int index = 0;
                    for (int j = 0; j < Block.blocks.length; j++) {
                        if (Block.blocks[j] != null && Block.blocks[j].symbol == n[i]) {
                            b[i][h - 1] = Block.blocks[j].index;
                            Block.blocks[j].parserAction(dungeon, (double) (i * BLOCK_WIDTH) + BLOCK_WIDTH / 2, (double) ((h - 1) * BLOCK_HEIGHT) + BLOCK_HEIGHT / 2);
                        }
                    }
                }
            }
            blocks = new byte[w][h]; borders = new byte[w][h];
            width = w;
            height = h;
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    blocks[x][y] = (byte) b[x][y];
                }
            }
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    if (get(x, y).border != null) {
                        int i = getIndex(x, y);
                        int v = 0;
                        if (getIndex(x - 1, y) == i) {
                            v += 1;
                        }
                        if (getIndex(x, y - 1) == i) {
                            v += 2;
                        }
                        if (getIndex(x + 1, y) == i) {
                            v += 4;
                        }
                        if (getIndex(x, y + 1) == i) {
                            v += 8;
                        }
                        if (getIndex(x - 1, y) == i & getIndex(x, y - 1) == i & getIndex(x - 1, y - 1) != i) {
                            v += 16;
                        }
                        if (getIndex(x + 1, y) == i & getIndex(x, y - 1) == i & getIndex(x + 1, y - 1) != i) {
                            v += 32;
                        }
                        if (getIndex(x + 1, y) == i & getIndex(x, y + 1) == i & getIndex(x + 1, y + 1) != i) {
                            v += 64;
                        }
                        if (getIndex(x - 1, y) == i & getIndex(x, y + 1) == i & getIndex(x - 1, y + 1) != i) {
                            v += 128;
                        }
                        borders[x][y] = (byte) (v - 128);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Terrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void render(Point camera) {
        for (int x = (int)((camera.x) / BLOCK_WIDTH - 1); x < (camera.x + Display.getWidth()) / BLOCK_WIDTH + 1; x++) {
            for (int y = (int)((camera.y) / BLOCK_HEIGHT - 1); y < (camera.y + Display.getHeight()) / BLOCK_HEIGHT + 1; y++) {
                GL11.glTranslated(x * BLOCK_WIDTH, y * BLOCK_HEIGHT, 0);
                get(x, y).render(getBorder(x, y));
                GL11.glTranslated(-x * BLOCK_WIDTH, -y * BLOCK_HEIGHT, 0);
            }
        }
    }

    public void renderTops(Point camera) {
        for (int x = (int)((camera.x) / BLOCK_WIDTH - 1); x < (camera.x + Display.getWidth()) / BLOCK_WIDTH + 1; x++) {
            for (int y = (int)((camera.y) / BLOCK_HEIGHT - 1); y < (camera.y + Display.getHeight()) / BLOCK_HEIGHT + 1; y++) {
                GL11.glTranslated(x * BLOCK_WIDTH, y * BLOCK_HEIGHT, 0);
                get(x, y).renderTop(getBorder(x, y));
                GL11.glTranslated(-x * BLOCK_WIDTH, -y * BLOCK_HEIGHT, 0);
            }
        }
    }

    public Block get(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return Block.blocks[blocks[x][y]];
        } else {
            return Block.blocks[0];
        }
    }

    public int getIndex(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return blocks[x][y];
        } else {
            return 0;
        }
    }

    public int getBorder(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return borders[x][y] + 128;
        } else {
            return 0;
        }
    }

    public void set(int x, int y, Block block) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            blocks[x][y] = (byte) block.index;
        }
    }

    public void setIndex(int x, int y, int index) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            blocks[x][y] = (byte) index;
        }
    }

    public void setBorder(int x, int y, int index) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            borders[x][y] = (byte) (index - 128);
        }
    }

    public Block get(double x, double y) {
        return get((int) (x / BLOCK_WIDTH), (int) (y / BLOCK_HEIGHT));
    }

    public int getIndex(double x, double y) {
        return getIndex((int) (x / BLOCK_WIDTH), (int) (y / BLOCK_HEIGHT));
    }

    public int getBorder(double x, double y) {
        return getBorder((int) (x / BLOCK_WIDTH), (int) (y / BLOCK_HEIGHT));
    }

    public void set(double x, double y, Block block) {
        set((int) (x / BLOCK_WIDTH), (int) (y / BLOCK_HEIGHT), block);
    }

    public void setIndex(double x, double y, int index) {
        setIndex((int) (x / BLOCK_WIDTH), (int) (y / BLOCK_HEIGHT), index);
    }
    
    public void setBorder(double x, double y, int index) {
        setBorder((int) (x / BLOCK_WIDTH), (int) (y / BLOCK_HEIGHT), index);
    }
}
