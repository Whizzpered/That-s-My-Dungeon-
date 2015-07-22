/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.environment.entities.items.Item;
import static java.lang.Math.*;
import org.tmd.environment.Condition;
import static org.tmd.environment.Condition.*;
import org.tmd.environment.entities.raiders.Warrior;
import org.tmd.main.Main;
import org.tmd.main.Nicknames;
import org.tmd.render.Animation;
import org.tmd.render.Image;
import org.tmd.render.Sprite;
import org.tmd.render.gui.Chat;

/**
 *
 * @author Whizzpered
 */
public class Raider extends Entity {

    Animation ghost = new Animation("creatures/ghost");
    int deathtimer = 800;
    boolean hasMoney = true;
    public Condition condition = JOINED;
    public Item[] weared = new Item[4];

    public Raider(double x, double y, int level) {
        super(x, y);
        this.level = level;
        spriteStanding = new Sprite("creatures/pries");
        minimapIcon = new Image("minimap/warrior.png");
        name = "raider";
        width = 96;
        maxhp = 20;
        deltahp = 15;
        attackDamage = 2;
        attackDeltaDamage = 2;
        faction = 2;
        headType = 0;
        clickable = true;
        counter.period = 400;
        distance = 400;
        nickmame = Nicknames.get();
        for (int i = 0; i < 4; i++) {
            if (Main.RANDOM.nextBoolean()) {
                int type = Main.RANDOM.nextInt(4);
                if (type == 0) {
                    weared[i] = new Item("hat", 1);
                } else if (type == 1) {
                    weared[i] = new Item("arms", 1);
                } else if (type == 2) {
                    weared[i] = new Item("braces", 1);
                } else if (type == 3) {
                    weared[i] = new Item("pants", 1);
                }
            }
        }
    }

    @Override
    public void alive() {
        ai();
    }

    public void ai() {
        if (dead) {
            condition = DEAD;
        }
        if (dungeon.player.dead) {
            condition = WON;
        }
        if (condition == JOINED) {
            join();
        } else if (condition == GOING) {
            going();
        } else if (condition == BATTLE) {
            battle();
        } else if (condition == NOTANKS) {
            noTanks();
        } else if (condition == WON) {
            won();
        } else if (condition == DEAD) {
            died();
        }
    }

    public void join() {
        if (entried) {
            entried = false;
            counter.start();
        } else {
            if (counter.is()) {
                goTo(dungeon.playerRespawnPoint.x, dungeon.playerRespawnPoint.y);
            }
            counter.tick();
            dungeon.chat.addMessage(this, Chat.messageType.TYPE_JOINED);
        }
    }

    public void going() {
        double dist = detectDistance;
        if (focus == null) {
            for (Entity e : dungeon.getEntities()) {
                if (!e.dead && e.faction == 1) {
                    double d = sqrt(pow(e.x - x, 2) + pow(e.y - y, 2));
                    if (d < dist) {
                        dist = d;
                        focus = e;
                    }
                }
            }
        }
        if (focus != null) {
            attack(focus);
            condition = BATTLE;
            dungeon.chat.addMessage(this, Chat.messageType.TYPE_BATTLE);
        }

    }

    public void battle() {
        if (focus != null && focus.dead) {
            condition = GOING;
            goTo(dungeon.playerRespawnPoint.x, dungeon.playerRespawnPoint.y);
            focus = null;
        }
        attack(focus);
        /*
         Specific battle actions
         */
        for (Raider r : dungeon.getRaiders()) {
            if (r instanceof Warrior) {
                if (!r.dead) {
                    return;
                }
            }
        }
        dungeon.chat.addMessage(this, Chat.messageType.TYPE_NOTANKS);
        if (attackDistance > width) {
            attackDistance = (int) width;
        }
        condition = NOTANKS;
    }

    public void noTanks() {
        attack(dungeon.player);
    }

    public void won() {
        dungeon.chat.addMessage(this, Chat.messageType.TYPE_HEAL);
        goTo(dungeon.raidersRespawnPoint.x, dungeon.raidersRespawnPoint.y);
    }

    public void died() {
        if (counter.is()) {
            dungeon.chat.addMessage(this, Chat.messageType.TYPE_RISE);
            counter.start();
        }
    }

    @Override
    public void goTo(double x, double y) {
        super.goTo(x, y);
        condition = GOING;
    }

    @Override
    public void dead() {
        condition = DEAD;
        phantom = true;
        if (hasMoney) {
            hasMoney = false;
            for (int i = 0; i < 4; i++) {
                dungeon.entities.add(new Coin(x, y));
                if (weared[i] != null) {
                    weared[i].drop(this);
                    weared[i] = null;
                }
            }
        }
        deathtimer--;
        if (deathtimer == 0) {
            dungeon.entities.remove(this);
            for (int i = 0; i < 4; i++) {
                dungeon.entities.add(new Expa(x, y));
            }
        }
    }

    @Override
    public void render() {
        if (!dead) {
            super.render();
        } else {
            int i = 0;
            if (deathtimer < 100) {
                i = 6 - deathtimer / 14;
                if (i > 6) {
                    i = 6;
                }
                if (i < 0) {
                    i = 0;
                }
            }
            Image g = ghost.images[i];
            g.draw(x - g.width, y - g.height * 2, g.width * 2, g.height * 2);
        }
    }

    @Override
    public void click() {
        dungeon.player.focus = this;
        dungeon.player.standing = true;
    }
}
