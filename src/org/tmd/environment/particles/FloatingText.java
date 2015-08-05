 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.particles;

import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.render.scenes.Dungeon;

/**
 *
 * @author Whizzpered
 */
public class FloatingText extends Particle {

    public int period;
    public Color color;
    public String message;
    public Dungeon dung = Declaration.dungeon;

    public FloatingText(int x, int y, String s, Color color) {
        super(x, y);
        message = s;
        timer = period = 100;
        this.color = color;
    }

    @Override
    public void tick() {
        super.tick();
        y -= 0.5;
    }

    @Override
    public void renderEntity() {
        Main.defaultFont.drawString(message, (int) x, (int) y, color);
    }
}
