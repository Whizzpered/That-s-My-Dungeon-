/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.lwjgl.opengl.Display;
import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;

/**
 *
 * @author Whizzpered
 */
public class AblisPanel extends Element {

    public Player player;

    public AblisPanel() {
        super(Display.getWidth() / 2, Display.getHeight(), 200, 100);
        player = Declaration.dungeon.player;
        
        //this.horisontalAlign = Align.CENTER;
        //this.verticalAlign = Align.DOWN;
    }

    @Override
    public boolean handle() {
        width = player.abilities.size() * 64;
        height = 70;
        return super.handle();
    }

    @Override
    public void render() {
        x = Display.getWidth() / 2 - width / 2;
        y = Display.getHeight() - height;
        //Frame.glassFrame.render(getX() - 4, getY(), width + 8, height);
        for (int i = 0; i < player.abilities.size(); i++) {
            player.abilities.get(i).x = x + i * 64;
            player.abilities.get(i).y = y + 4;
            player.abilities.get(i).render();
        }
    }
}
