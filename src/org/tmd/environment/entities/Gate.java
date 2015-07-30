/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class Gate extends Entity{
    
    private Image spriteOpened = new Image("creatures/door/opened.png");
    private Image spriteClosed = new Image("creatures/door/closed.png");
    
    public boolean opened = true;

    public Gate(double x, double y) {
        super(x, y);
        phantom = true;
        width = 40;
        height = 300;
        minimapIcon = null;
        maxhp = Double.MAX_VALUE;
        name = "gate";
        opened = true;
    }

    @Override
    public void tick() {
        if(opened){
            for(Entity e : dungeon.getEntities()){
                if(e instanceof Raider){
                    opened = false;
                    if(dungeon.terrain.get(dungeon.player.x, dungeon.player.y).restZone){
                        dungeon.player.x = dungeon.playerRespawnPoint.x;
                        dungeon.player.y = dungeon.playerRespawnPoint.y;
                    }
                    break;
                }
            }
        }else{
            boolean open = true;
            for(Entity e : dungeon.getEntities()){
                if(Math.abs(y - e.y) < height / 2 && Math.abs(x - e.x) < (e.width + width) / 2){
                    if(e.x > x)e.x = x + (e.width + width) / 2;
                    if(e.x < x)e.x = x - (e.width + width) / 2;
                }
                if(e instanceof Raider){
                    open = false;
                }
            }
            opened = open;
        }
    }

    @Override
    public int getRenderQueuePriority() {
        return 0;
    }

    @Override
    public void render() {
        if(opened){
            spriteOpened.draw(x - spriteClosed.width, y - 150, spriteOpened.width * 2, spriteOpened.height * 2);
        }else{
            spriteClosed.draw(x - spriteClosed.width, y - 150, spriteClosed.width * 2, spriteClosed.height * 2);
        }
    }
    
}
