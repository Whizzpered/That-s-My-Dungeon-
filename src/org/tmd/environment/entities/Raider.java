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
import org.tmd.main.Main;
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
        distance = 400;
        for (int i = 0; i < 4; i++) {
            if (Main.RANDOM.nextBoolean()) {
                int type = Main.RANDOM.nextInt(4);
                if (type == 0) {
                    weared[i] = new Item("hat", 1) {

                        @Override
                        public void modificate(Entity cr) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                } else if (type == 1) {
                    weared[i] = new Item("arms", 1) {

                        @Override
                        public void modificate(Entity cr) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                } else if (type == 2) {
                    weared[i] = new Item("braces", 1) {

                        @Override
                        public void modificate(Entity cr) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                } else if (type == 3) {
                    weared[i] = new Item("pants", 1) {

                        @Override
                        public void modificate(Entity cr) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                }
            }
        }
    }

    @Override
    public void alive() {
        ai();
    }

    public void ai() {
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
                condition = GOING;
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
        if (focus != null && focus.dead) {
            goTo(dungeon.playerRespawnPoint.x, dungeon.playerRespawnPoint.y);
            focus = null;
        }

        if (focus != null) {
            attack(focus);
        }

    }

    public void battle() {

    }

    public void noTanks() {

    }

    public void won() {

    }

    public void died() {

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
