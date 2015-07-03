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
import org.tmd.environment.WaterBlock;
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
    
    /*
    *   Blocks:
    */
    
    public static Block air = new Block(0, ' ', null, true, false, false);
    public static Block floor = new Block(1, '_', "floor.png", false, false, false);
    public static Block wall = new Block(2, '*', "wall.png", "top.png", "border.png", "border_angle.png", "border_angle_inside.png", true, false, false);
    public static Block water = new WaterBlock(3, '~', "water.png", "border.png", "border_angle.png", "border_angle_inside.png", true, false, false);
    
}
