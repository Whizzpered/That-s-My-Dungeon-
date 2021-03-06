/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

import java.util.ArrayList;
import org.tmd.environment.entities.items.Item;
import org.tmd.environment.particles.LevelUp;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.main.Sounds;
import org.tmd.render.Image;
import org.tmd.render.gui.AbilityButton;
import org.tmd.render.gui.Mouse;
import org.tmd.render.scenes.Inventory;

/**
 *
 * @author yew_mentzaki
 */
public class Player extends Entity {

    public Image deadSprite = new Image("creatures/player/dead.png");

    public int souls, neededSouls = 12, money, expa = 3;
    
    public Inventory inventory = Declaration.inventory;
    public AbilityButton castAbility;
    public Entity agro;
    public ArrayList<AbilityButton> abilities = new ArrayList<AbilityButton>();

    public Player(double x, double y) {
        super(x, y);
        minimapIcon = new Image("minimap/player.png");
        name = "Player";
        attackType = "hit_big_clutches";
        headType = 1;
        faction = 1;
        level = 1;
        attackDistance = 128;
    }

    @Override
    public void tick() {
        super.tick();
        for (AbilityButton abilitie : abilities) {
            abilitie.tick();
        }
        if (agro != null) {
            focus = agro;
        }
    }

    @Override
    public void longTick() {
        super.longTick();
        for (AbilityButton abilitie : abilities) {
            if (abilitie.abil.cd > 0) {
                abilitie.abil.cd--;
            }
            if (abilitie.abil.conting > 0) {
                abilitie.abil.conting--;
            }
            if (abilitie.cooldown > 0) {
                abilitie.cooldown--;
            }
        }
    }

    @Override
    public void alive() {
        if (focus != null) {
            if (focus.dead) {
                focus = null;
            } else {
                goTo(focus.x, focus.y);
                attack(focus);
            }
        }
        while (souls >= neededSouls) {
            souls -= neededSouls;
            level++;
            dungeon.addParticle(new LevelUp(this));
            for (Entity e : dungeon.entities) {
                if (e instanceof Mob) {
                    e.level++;
                    dungeon.addParticle(new LevelUp(e));
                }
            }
        }
    }

    @Override
    public void render() {
        if (dead) {
            deadSprite.draw(x, y - deadSprite.height, deadSprite.width * 2, deadSprite.height * 2, 0);
        } else {
            super.render();
            for (Item item : weared) {
                if (item != null) {
                    item.render(this);
                }
            }
        }
    }

    @Override
    public void handle() {
        if (Mouse.left) {
            goTo(Mouse.x - dungeon.cam.x, Mouse.y - dungeon.cam.y);
            focus = null;
            dungeon.pointer.set(Mouse.x - dungeon.cam.x, Mouse.y - dungeon.cam.y);
        }
    }

    @Override
    public void attackMethod(Entity e) {
        Sounds.play("player");
        super.attackMethod(e);
    }

    @Override
    public void dead() {
        focus = null;
        agro = null;
    }
    
    @Override
    public boolean hit(double damage, Entity from) {
        if (Main.RANDOM.nextInt(10) == 0) {
            Sounds.play("player_hit");
        }
        return super.hit(damage, from);
    }
}
