/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class AbilityTrain extends Button {

    public Image ability;
    public static Image arrow = new Image("gui/arrow.png");
    public static Image arrowRight = new Image("gui/arrow_right.png");
    public static Image grayArrow = new Image("gui/gray_arrow.png");
    public static Image grayArrowRight = new Image("gui/gray_arrow_right.png");
    public static Image levelUp = new Image("gui/levelup.png");
    public boolean activated = false;
    public int level, levelUpTimer;
    public AbilityTrain left, center, right;

    public AbilityTrain(String abilityName, boolean enabled, int x, int y) {
        super("", x, y, 64, 64);
        String d[] = abilityName.split("-");
        this.enabled = enabled;
        this.text = d[0];
        this.level = Integer.valueOf(d[1]);
        this.ability = new Image("abilities/" + abilityName.toLowerCase() + ".png");
    }

    @Override
    public void click() {
        if (!activated) {
            if (left != null) {
                left.enabled = true;
            }
            if (center != null) {
                center.enabled = true;
            }
            if (right != null) {
                right.enabled = true;
            }
            levelUpTimer = 255;
            activated = true;
            Player p = Declaration.dungeon.player;
            for (int i = 0; i < p.abilities.size(); i++) {
                if (p.abilities.get(i).text.equals(text)) {
                    p.abilities.get(i).level = level;
                    return;
                }
            }
            p.abilities.add(new AbilityButton(text, enabled, level, level));
        }
    }

    public void rclick() {
    }

    @Override
    public void render() {
        
        if (activated) {
            ability.a = 1f;
        } else {
            ability.a = 0.5f;
        }
        
        if (enabled) {
            Frame.glassFrame.render(getX(), getY(), width, height);
            if (left != null) {
                arrowRight.draw(getX() + 16, getY() + height, -arrowRight.width, arrowRight.height);
            }
            if (center != null) {
                arrow.draw(getX() + (width - arrow.width) / 2, getY() + height);
            }
            if (right != null) {
                arrowRight.draw(getX() + width - 16, getY() + height);
            }
        } else {
            //Frame.grayFrame.render(getX(), getY(), width, height);
            if (left != null) {
                grayArrowRight.draw(getX() + 16, getY() + height, -arrowRight.width, arrowRight.height);
            }
            if (center != null) {
                grayArrow.draw(getX() + (width - arrow.width) / 2, getY() + height);
            }
            if (right != null) {
                grayArrowRight.draw(getX() + width - 16, getY() + height);
            }
        }
        ability.draw(getX(), getY() - 1);
        if (levelUpTimer >= 0) {
            levelUp.a = ((float) levelUpTimer) / 255f;
            int size = (255 - levelUpTimer) / 2 + 20;
            levelUp.draw(getX() + width / 2, getY() + height / 2, size, size, (((float) levelUpTimer) / 25f));
            levelUpTimer -= 2;
        }
    }
}
