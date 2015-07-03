/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import org.tmd.environment.Terrain;

/**
 *
 * @author yew_mentzaki
 */
public class Dungeon extends Scene{

    Terrain terrain = new Terrain(this, "maps/dungeon1.map");
    
    public Dungeon() {
        
    }

    @Override
    public void render() {
        terrain.render(null);
        terrain.renderTops(null);
    }
    
}
