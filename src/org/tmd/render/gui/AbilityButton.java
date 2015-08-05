/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.tmd.environment.Point;
import org.tmd.environment.abilities.*;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class AbilityButton extends Button {

    public Image abilityIcon;
    public boolean activated = false, pressed;
    public int cooldown;
    public int level;
    public Ability abil;
    public String key;
    public double angle;

    public AbilityButton(String abilityName) {
        super("", 0, 0, 64, 64);
        text = abilityName;
        this.level = 1;
        this.abilityIcon = new Image("abilities/" + abilityName.toLowerCase() + "-" + level + ".png");
        try {
            abil = (Ability) Class.forName("org.tmd.environment.abilities." + text).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbilityButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AbilityButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbilityButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (abil != null && !(abil instanceof Passive)) {
            key = ((Declaration.dungeon.player.abilities.size() + 1) + "") + "";
            enabled = true;
        } else {
            enabled = false;
        }
    }

    @Override
    public void click() {
        System.out.println(abil.getClass().getName());
        if (enabled) {
            if (abil instanceof Active) {
                ((Active) abil).cast(level, Declaration.dungeon.player);
                enabled = false;
                cooldown = abil.cooldown;
            } else if (abil instanceof Target) {
                Declaration.dungeon.player.castAbility = this;
            } else if (abil instanceof Passive) {
                enabled = false;
            }
        }
    }

    public void cast(Point target) {
        if (cooldown == 0 && abil instanceof Target) {
            Declaration.dungeon.player.castAbility = null;
            ((Target) abil).cast(level, target);
            enabled = false;
            cooldown = abil.cooldown;
        }
    }

    public void tick() {
        abil.cd = cooldown;
        if (cooldown > 0) {
            cooldown--;
        } else {
            enabled = true;
        }
        abil.tick();
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

    }

    @Override
    public void render() {
        if (enabled) {
            Frame.glassFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
            if (Declaration.dungeon.player.castAbility == this) {
                AbilityTrain.levelUp.a = 1;
                AbilityTrain.levelUp.draw(getX() + width / 2, getY() + height / 2, angle += 0.1);
            }
            abilityIcon.draw(getX(), getY());
        } else {
            Frame.grayFrame.render(getX(), getY() + (hover ? 1 : -1), width, height);
            if (abil.cooldown > 0) {
                double d = 1 - (double) cooldown / (double) abil.cooldown;
                Main.g.setColor(Color.green);
                Main.g.fillRect((int) getX(), (int) (getY() + height), (int) (width * d), 4);
            }
            abilityIcon.draw(getX(), getY());
        }
    }
}
