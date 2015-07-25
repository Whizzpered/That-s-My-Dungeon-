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
public class AbilitiesPanel extends Element {

    public Player player;

    public AbilitiesPanel() {
        super(0, 0, 200, 100);
        player = Declaration.dungeon.player;
        
        this.horisontalAlign = Align.CENTER;
        this.verticalAlign = Align.DOWN;
    }

    @Override
    public boolean handle() {
        width = player.abilities.size() * 64;
        height = 70;
        for (int i = 0; i < player.abilities.size(); i++) {
            AbilityButton ab = player.abilities.get(i);
            ab.x = i * 64;
            ab.y = 4;
            ab.parent = this;
            ab.handle();
        }
        return super.handle();
    }

    @Override
    public void render() {
        for (int i = 0; i < player.abilities.size(); i++) {
            AbilityButton ab = player.abilities.get(i);
            ab.x = i * 64;
            ab.y = 4;
            ab.parent = this;
            ab.render();
        }
    }
}
