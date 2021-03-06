/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.tmd.environment.entities.Player;
import org.tmd.main.Declaration;
import org.tmd.main.GameLocale;
import org.tmd.render.Image;
import org.tmd.render.scenes.Scene;

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
    public String description, mod;

    public AbilityTrain(String abilityName, boolean enabled, int x, int y) {
        super("", x, y, 64, 64);
        String d[] = abilityName.split("-");
        this.enabled = enabled;
        this.text = d[0];
        if (text.equals("Regen")) {
            mod = "hp";
        } else {
            mod = "damage";
        }
        this.level = Integer.valueOf(d[1]);
        description = text.toLowerCase() + "description";
        this.ability = new Image("abilities/" + abilityName.toLowerCase() + ".png");
    }

    @Override
    public void click() {
        descrip = false;
        if (!activated) {
            if (Declaration.dungeon.player.expa >= 1) {
                Declaration.dungeon.player.expa -= 1;
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
                        if (p.abilities.get(i).level < level) {
                            p.abilities.get(i).level = level;
                            p.abilities.get(i).abilityIcon = ability;
                        }
                        return;
                    }
                }
                p.abilities.add(new AbilityButton(text));
            }
        }
    }

    public void rclick() {
        descrip = false;
    }

    @Override
    public void render() {
        if (activated) {
            ability.a = 1f;
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
            ability.a = 0.5f;
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
        if (enabled && Declaration.dungeon.player.expa >= level) {
            Frame.glassFrame.render(getX(), getY(), width, height);
        } else {
            Frame.grayFrame.render(getX(), getY(), width, height);
        }
        ability.draw(getX(), getY() - 1);
        if (levelUpTimer >= 0) {
            levelUp.a = ((float) levelUpTimer) / 255f;
            int size = (255 - levelUpTimer) / 2 + 20;
            levelUp.draw(getX() + width / 2, getY() + height / 2, size, size, (((float) levelUpTimer) / 25f));
            levelUpTimer -= 2;
        }

        if (descrip) {
            Scene.currentScene.currentTip = new ToolTip(GameLocale.get(text) + '\n' + '\n' + GameLocale.get(description) + '\n' + GameLocale.get(mod) + level * 12);
        }
    }

}
