/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.tmd.environment.abilities.Ability;
import org.tmd.environment.abilities.Target;
import org.tmd.main.Declaration;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class AbilityButton extends Button {

    public Image ability;
    public boolean activated = false, pressed;
    public int level;
    public Ability abil;
    public String key;

    public AbilityButton(String abilityName, boolean active, int x, int y) {
        super("", x, y, 64, 64);
        text = abilityName;
        this.level = 1;
        this.ability = new Image("abilities/" + abilityName.toLowerCase() + "-" + level + ".png");
        try {
            abil = (Ability) Class.forName("org.tmd.environment.ability." + text).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbilityButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AbilityButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbilityButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (active) {
            key = ((Declaration.dungeon.player.abilities.size() + 1) + "") + "";
            enabled = true;
        } else {
            enabled = false;
        }
    }

    @Override
    public void click() {
        if (enabled) {
            abil.cast(level, Declaration.dungeon.player);
        }
    }

    @Override
    public boolean handle() {
        if (Keyboard.isKeyDown(Keyboard.getKeyIndex(key))) {
            if (pressed) {
                click();
                pressed = false;
            }
        } else {
            pressed = true;
        }
        return super.handle();
    }

    public void rclick() {
        if(abil instanceof Target){
            if(((Target)abil).aiming){
                ((Target)abil).aiming = false;
            }
        }
    }

    @Override
    public void render() {
        if (enabled) {
            Frame.glassFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
            ability.draw(getX(), getY());
        } else {
            ability.draw(getX(), getY());
        }
    }
}
