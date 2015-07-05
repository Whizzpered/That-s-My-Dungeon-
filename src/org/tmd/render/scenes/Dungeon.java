/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.tmd.environment.Block;
import org.tmd.environment.Point;
import org.tmd.environment.Terrain;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Mouse;

/**
 *
 * @author yew_mentzaki & Whizzpered
 */
public class Dungeon extends Scene {

    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public Point cam = new Point();
    public Entity cameraTarget;
    public Point playerRespawnPoint, raidersRespawnPoint;
    public ArrayList<Point> minionsRespawnPoints = new ArrayList<Point>();
    public Terrain terrain = new Terrain(this, "maps/dungeon1.map");

    public Entity[] getEntities() {
        ArrayList<Entity> i = new ArrayList<Entity>(entities.size());
        for (int j = 0; j < entities.size(); j++) {
            i.add(entities.get(j));
        }
        Entity[] e = new Entity[i.size()];
        for (int j = 0; j < i.size(); j++) {
            e[j] = i.get(j);
        }
        return e;
    }

    public Entity[] getEntitiesForRender() {
        Entity[] en = getEntities();
        int[] in = new int[en.length];
        for (int i = 0; i < en.length; i++) {
            in[i] = en[i].getRenderQueuePriority();
        }
        for (int i = 0; i < in.length - 1; i++) {
            for (int j = 0; j < in.length - i - 1; j++) {
                if (in[j] > in[j + 1]) {
                    int a = in[j];
                    in[j] = in[j + 1];
                    in[j + 1] = a;
                    Entity b = en[j];
                    en[j] = en[j + 1];
                    en[j + 1] = b;
                }
            }
        }
        return en;
    }

    Button menuButton = new Button("menu", 0, 20, 140, 50) {

        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
        }

        @Override
        public void click() {
            currentScene = Declaration.mainMenu;
        }

    };

    @Override
    public void tick() {
        for (Entity e : getEntities()) {
            try {
                e.tick();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void camUpdate() {
        int mx = (int) Mouse.x, my = (int) Mouse.y;
        if (cameraTarget != null) {
            cam.x = -cameraTarget.x;
            cam.y = -cameraTarget.y;
        } else {
            cam.x = 0;
            cam.y = 0;
        }
        cam.x += (int) (Block.BLOCK_WIDTH / 2) + (Display.getWidth() / 2) - (mx - (Display.getWidth() / 2));
        cam.y += (int) (Block.BLOCK_HEIGHT / 2) + (Display.getHeight() / 2) - (my - (Display.getHeight() / 2));
    }

    @Override
    public void longTick() {
        for (Entity e : getEntities()) {
            try {
                e.longTick();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void init() {
        gui.add(menuButton);
        cameraTarget = new Player(playerRespawnPoint.x, playerRespawnPoint.y);
        entities.add(cameraTarget);
    }

    @Override
    public void render() {
        camUpdate();
        GL11.glTranslated(cam.x, cam.y, 0);
        {
            terrain.render(null);
            for (Entity e : getEntitiesForRender()) {
                try {
                    e.render();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        terrain.renderTops(null);
        GL11.glTranslated(-cam.x, -cam.y, 0);
    }

    @Override
    public void handle() {
        for (Entity e : getEntities()) {
            try {
                e.handle();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
