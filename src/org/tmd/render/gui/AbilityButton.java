/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class AbilityButton extends Button{
    public Image ability;
    public boolean activated = false;
    public int level;

    public AbilityButton(String abilityName, boolean enabled, int x, int y) {
        super("", x, y, 64, 64);
        this.text = abilityName;
        this.level = 1;
        this.ability = new Image("abilities/" + abilityName.toLowerCase() + "-" + level + ".png");
    }

    @Override
    public void click() {
        if(!activated){
            activated = true;
        }
    }

    public void rclick() {
    }

    @Override
    public void render() {
        if (enabled) {
            Frame.glassFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        } else {
            Frame.glassFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
        }
    }
}
