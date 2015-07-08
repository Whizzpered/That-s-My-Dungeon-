/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import static java.lang.Math.*;
import org.tmd.environment.particles.BloodParticle;
import org.tmd.environment.particles.Hit;
import org.tmd.main.Main;
import org.tmd.render.Image;
import org.tmd.render.gui.Mouse;

/**
 *
 * @author yew_mentzaki
 */
public class Player extends Entity {

    public Player(double x, double y) {
        super(x, y);
        minimapIcon = new Image("minimap/player.png");
        name = "Player";
    }

    @Override
    public void tick() {
        super.tick();
        if(Main.RANDOM.nextInt(100) == 0){
            dungeon.addParticle(new Hit("hit_sword", x, y - 35));
            for (int i = 0; i < Main.RANDOM.nextInt(3); i++) {
                dungeon.addParticle(new BloodParticle(x, y - 35));
            }
        }
    }

    @Override
    public void handle() {
        if (Mouse.left) {
            goTo(Mouse.x - dungeon.cam.x, Mouse.y - dungeon.cam.y);
        }
    }

}
