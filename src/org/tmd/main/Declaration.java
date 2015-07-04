/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.main;

import static org.lwjgl.opengl.GL11.*;
import org.tmd.environment.Block;
import static org.tmd.environment.Block.BLOCK_HEIGHT;
import static org.tmd.environment.Block.BLOCK_WIDTH;
import org.tmd.environment.Point;
import org.tmd.environment.WaterBlock;
import org.tmd.environment.entities.Gate;
import static org.tmd.main.Main.RANDOM;
import org.tmd.render.Image;
import org.tmd.render.scenes.*;

/**
 *
 * @author yew_mentzaki
 */
public class Declaration {
    /*
     *   Scenes:
     */

    public static MainMenu mainMenu = new MainMenu();
    public static Dungeon dungeon = null;
    
    /*
    *   Blocks:
    */
    
    public static Block blockAir = new Block(0, ' ', null, true, false, false);
    public static Block blockFloor = new Block(1, '_', "floor.png", false, false, false);
    public static Block blockWall = new Block(2, '*', "wall.png", "top.png", "border.png", "border_angle.png", "border_angle_inside.png", true, false, false);
    public static Block blockWater = new WaterBlock(3, '~', "water.png", "border.png", "border_angle.png", "border_angle_inside.png", true, false, false);
    public static Block blockPlayer = new Block(4, 'P', "floor.png", false, false, false){
        @Override
        public void parserAction(Dungeon dungeon, double x, double y) {
            dungeon.playerRespawnPoint = new Point(x, y);
        }
    };
    public static Block blockEnemy = new Block(5, '|', "floor.png", false, false, false){
        @Override
        public void parserAction(Dungeon dungeon, double x, double y) {
            dungeon.raidersRespawnPoint = new Point(x, y);
        }
    };
    public static Block blockMinion = new Block(6, '@', "floor.png", false, false, false){
        @Override
        public void parserAction(Dungeon dungeon, double x, double y) {
            dungeon.minionsRespawnPoints.add(new Point(x, y));
        }
    };
    
    public static Block blockGate = new Block(7, '!', "floor.png", false, false, false){
        @Override
        public void parserAction(Dungeon dungeon, double x, double y) {
            Gate gate = new Gate(x, y);
            gate.dungeon = dungeon;
            dungeon.entities.add(gate);
            
        }
    };
}
