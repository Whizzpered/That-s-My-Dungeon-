/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import static java.lang.Math.*;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.gui.Mouse;
import org.tmd.render.scenes.Dungeon;

/**
 *
 * @author yew_mentzaki
 */
public class Entity {
    Dungeon dungeon = Declaration.dungeon;
    double x, y, size = 10, hp;
    int faction;
    boolean phantom = false;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void tick() {
        move(Main.RANDOM.nextInt(11) - 5, Main.RANDOM.nextInt(11) - 5);
        move(cos(atan2(Mouse.y - y, Mouse.x - x)) * 2, sin(atan2(Mouse.y - y, Mouse.x - x)) * 2);
    }
    
    public void move(double x, double y) {
        if(!dungeon.terrain.get(this.x + x, this.y + y).solid){
            this.x += x;
            this.y += y;
        }else if(!dungeon.terrain.get(this.x + x, this.y).solid){
            this.x += x;
        }else if(!dungeon.terrain.get(this.x, this.y + y).solid){
            this.y += y;
        }
    }
    
    public void longTick() {
        
    }
    
    public void render() {
        Main.g.setColor(Color.black);
        Main.g.fillOval((float)x - 50, (float)y - 100, 100, 100);
        Main.g.setColor(Color.pink);
        Main.g.fillOval((float)x - 40, (float)y - 90, 80, 80);
        
    }
    
    public int getRenderQueuePriority() {
        return (int) y;
    }
    
    public void hit(double damage, Entity from){
        
    }
}
