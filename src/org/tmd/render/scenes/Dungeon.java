/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import org.tmd.environment.Terrain;
import org.tmd.main.Declaration;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;

/**
 *
 * @author yew_mentzaki
 */
public class Dungeon extends Scene{

    Terrain terrain = new Terrain(this, "maps/dungeon1.map");
    
    Button menuButton = new Button("menu", 0, 20, 140, 50){

        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
        }

        @Override
        public void click() {
            currentScene = Declaration.mainMenu;
        }
        
    };
    
    public Dungeon() {
        
    }

    @Override
    public void init() {
        gui.add(menuButton);
    }
    
    @Override
    public void render() {
        terrain.render(null);
        terrain.renderTops(null);
    }
    
}
