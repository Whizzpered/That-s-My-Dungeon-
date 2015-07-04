/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.util.ArrayList;
import org.tmd.environment.Terrain;
import org.tmd.environment.entities.Entity;
import org.tmd.main.Declaration;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;

/**
 *
 * @author yew_mentzaki
 */
public class Dungeon extends Scene {

    public Terrain terrain = new Terrain(this, "maps/dungeon1.map");
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    Entity[] getEntities() {
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

    Entity[] getEntitiesForRender() {
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
        for(Entity e : getEntities()){
            e.tick();
        }
    }

    @Override
    public void longTick() {
        for(Entity e : getEntities()){
            e.longTick();
        }
    }
    
    @Override
    public void init() {
        gui.add(menuButton);
        for (int i = 0; i < 35; i++) {
            entities.add(new Entity(450, 300));
        }
    }

    @Override
    public void render() {
        terrain.render(null);
        for (Entity e : getEntitiesForRender()) {
            e.render();
        }
        terrain.renderTops(null);
    }

}
