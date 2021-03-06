/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;
import org.tmd.render.Color;
import org.tmd.environment.Block;
import org.tmd.environment.Point;
import org.tmd.environment.Pointer;
import org.tmd.environment.Terrain;
import org.tmd.environment.entities.Entity;
import org.tmd.environment.entities.Mob;
import org.tmd.environment.entities.Player;
import org.tmd.environment.entities.Raider;
import org.tmd.environment.entities.raiders.*;
import org.tmd.environment.particles.FloatingText;
import org.tmd.environment.particles.Particle;
import org.tmd.main.Declaration;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.main.Nicknames;
import org.tmd.render.Image;
import org.tmd.render.gui.AbilitiesPanel;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Chat;
import org.tmd.render.gui.Frame;
import org.tmd.render.gui.Label;
import org.tmd.render.gui.MiniMap;
import org.tmd.render.gui.Mouse;
import org.tmd.render.gui.Panel;

/**
 *
 * @author yew_mentzaki & Whizzpered
 */
public class Dungeon extends Scene implements Serializable {

    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public Inventory inventory;
    public Training training;
    public Point cam = new Point(), floor = new Point();
    public Pointer pointer = new Pointer();
    public Entity cameraTarget;
    public Chat chat = new Chat(0, -50, 512, 256) {

        @Override
        public void init() {
            horisontalAlign = Align.CENTER;
            verticalAlign = Align.DOWN;
        }

    };
    public Point playerRespawnPoint, raidersRespawnPoint;
    public ArrayList<Point> minionsRespawnPoints = new ArrayList<Point>();
    public Player player;
    public Entity underMouse;
    File maps[] = new File("maps").listFiles();
    public Terrain terrain = new Terrain(this, "maps/" + maps[Main.RANDOM.nextInt(maps.length)].getName());
    public Particle[] particles = new Particle[512];
    public int longtim = 0, wave = 0, target = 0;
    private boolean wavetimer, frezedMouse;
    public int quake = 0;
    private Image shadow = new Image("gui/shadow.png");

    public MiniMap miniMap = new MiniMap(0, 0, 256, 256, this) {

        @Override
        public void init() {
            horisontalAlign = Align.RIGHT;
            verticalAlign = Align.DOWN;
        }

    };

    public Panel statsPanel = new Panel(0, 0, 256, 256) {

        Label name, health, souls, attack, armor;

        @Override
        public void init() {
            horisontalAlign = Align.LEFT;
            verticalAlign = Align.DOWN;
            final Image end = new Image("gui/lifebar.png");
            name = new Label("", 16, 8, Color.white) {

                @Override
                public void render() {
                    string = player.getName();
                    if (player.level > 0) {
                        String of = player.level + " " + GameLocale.get("level");
                        Main.defaultFont.drawStringRight(of, 240, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 240, (int) getY() - 2, color);
                    }

                    super.render();
                }

            };
            health = new Label("gui/icons/hp.png", "Health", 16, 36, Color.white) {

                @Override
                public void render() {
                    super.render();
                    double d = player.hp;
                    double m = player.getMaxHP();
                    String of = (int) d + "/" + (int) m;
                    Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                    Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                    Main.g.setColor(org.newdawn.slick.Color.green);
                    d = d / m * 200;
                    if (d < 0) {
                        d = 0;
                    }
                    if (d > 200) {
                        d = 200;
                    }
                    Main.g.fillRect(28, (int) getY() + 36, (int) d, 24);
                    end.draw((int) d + 18, (int) getY() + 36);
                    Frame.glassFrame.render(16, getY() + 32, 224, 33);
                }

            };
            souls = new Label("gui/icons/souls.png", "Souls", 16, 96, Color.white) {

                @Override
                public void render() {
                    if (player != player) {
                        return;
                    }
                    super.render();
                    double d = player.souls;
                    double m = player.neededSouls;
                    String of = (int) d + "/" + (int) m;
                    Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                    Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                    Main.g.setColor(Color.cyan.slickColor());
                    d = d / m * 200;
                    if (d < 0) {
                        d = 0;
                    }
                    if (d > 200) {
                        d = 200;
                    }
                    Main.g.fillRect(28, (int) getY() + 36, (int) d, 24);
                    end.draw((int) d + 18, (int) getY() + 36);
                    Frame.glassFrame.render(16, getY() + 32, 224, 33);
                }

            };
            attack = new Label("Damage", 25, 180, Color.white) {

                @Override
                public void render() {
                    if (player.getDMG() > 0) {
                        super.render();
                        String of = player.getDMG() + "";
                        Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                        Main.g.setColor(org.newdawn.slick.Color.green);
                    }
                }

            };
            armor = new Label("Armor", 25, 210, Color.white) {

                @Override
                public void render() {
                    if (player.getArmor() > 0) {
                        super.render();
                        String of = player.getArmor() + "";
                        Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                        Main.g.setColor(org.newdawn.slick.Color.green);
                    }
                }

            };
            add(name);
            add(souls);
            add(health);
            add(attack);
            add(armor);
        }

        @Override
        public void render() {

            super.render();
        }

    };

    public Panel underMousePanel = new Panel(0, 0, 256, 256) {

        Label name, health, souls, attack, armor;

        @Override
        public void init() {
            horisontalAlign = Align.LEFT;
            verticalAlign = Align.TOP;
            final Image end = new Image("gui/lifebar.png");
            name = new Label("", 16, 8, Color.white) {

                @Override
                public void render() {
                    if (underMouse == null) {
                        return;
                    }
                    string = underMouse.getName();
                    if (underMouse.level > 0) {
                        String of = underMouse.level + " " + GameLocale.get("level");
                        Main.defaultFont.drawStringRight(of, 240, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 240, (int) getY() - 2, color);
                    }
                    /*
                     if (underMouse.getDMG() > 0) {
                     String of = GameLocale.get("damage") + " " + underMouse.getDMG();
                     Main.defaultFont.drawStringRight(of, 240, (int) getY(), Color.black);
                     Main.defaultFont.drawStringRight(of, 240, (int) getY() - 2, color);
                     }
                     */
                    super.render();
                }

            };
            health = new Label("gui/icons/hp.png", "Health", 16, 36, Color.white) {

                @Override
                public void render() {
                    if (underMouse == null) {
                        return;
                    }
                    if (underMouse.maxhp != Float.MAX_VALUE) {
                        double d = underMouse.hp;
                        double m = underMouse.getMaxHP();
                        String of = (int) d + "/" + (int) m;
                        Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                        Main.g.setColor(org.newdawn.slick.Color.green);
                        d = d / m * 200;
                        if (d < 0) {
                            d = 0;
                        }
                        if (d > 200) {
                            d = 200;
                        }
                        Main.g.fillRect(28, (int) getY() + 36, (int) d, 24);
                        end.draw((int) d + 18, (int) getY() + 36);
                        Frame.glassFrame.render(16, getY() + 32, 224, 33);
                        super.render();
                    } else {
                        String of = GameLocale.get("invulnerable");
                        Main.defaultFont.drawStringAtCenter(of, 128, (int) getY(), Color.black);
                        Main.defaultFont.drawStringAtCenter(of, 128, (int) getY() + 2, color);
                    }
                }

            };
            attack = new Label("Damage", 25, 180, Color.white) {

                @Override
                public void render() {
                    if (underMouse == null) {
                        return;
                    }
                    if (underMouse.getDMG() > 0) {
                        super.render();
                        String of = underMouse.getDMG() + "";
                        Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                        Main.g.setColor(org.newdawn.slick.Color.green);
                    }
                }

            };
            armor = new Label("Armor", 25, 210, Color.white) {

                @Override
                public void render() {
                    if (underMouse == null) {
                        return;
                    }
                    if (underMouse.getArmor() > 0) {
                        super.render();
                        String of = underMouse.getArmor() + "";
                        Main.defaultFont.drawStringRight(of, 225, (int) getY(), Color.black);
                        Main.defaultFont.drawStringRight(of, 225, (int) getY() - 2, color);
                        Main.g.setColor(org.newdawn.slick.Color.green);
                    }
                }

            };
            add(name);
            add(health);
            add(attack);
            add(armor);
            visible = false;
        }

        @Override
        public boolean handle() {
            visible = underMouse != null;
            return super.handle();
        }

        @Override
        public void render() {
            super.render();
        }

    };

    public Entity[] getEntities() {
        ArrayList<Entity> i = new ArrayList<Entity>(entities.size());
        for (int j = 0; j < entities.size(); j++) {
            i.add(entities.get(j));
        }
        Entity[] e = new Entity[i.size()];
        for (int j = 0; j < i.size(); j++) {
            e[j] = i.get(j);
        }
        return e;
    }

    public Raider[] getRaiders() {
        ArrayList<Entity> en = new ArrayList<Entity>();
        for (Entity ent : entities) {
            if (ent instanceof Raider) {
                en.add(ent);
            }
        }
        Raider[] r = new Raider[en.size()];
        r = en.toArray(r);
        return r;
    }

    public Entity[] getEntitiesForRender() {
        Entity[] en = getEntities();
        Arrays.sort(en);
        return en;
    }

    public void addParticle(Particle p) {
        int most_oldest = 0;
        int oldness = 5000;
        for (int i = 0; i < particles.length; i++) {
            if (particles[i] == null) {
                particles[i] = p;
                return;
            } else {
                if (oldness > particles[i].timer) {
                    oldness = particles[i].timer;
                    most_oldest = i;
                }
            }
        }
        particles[most_oldest] = p;
    }

    @Override
    public void tick() {
        chat.tick();
        if (quake > 0) {
            quake--;
        }
        try {
            Declaration.inventory.tick();
            for (Entity e : getEntities()) {
                try {
                    e.tick();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            for (int i = 0; i < particles.length; i++) {
                if (particles[i] != null) {
                    particles[i].tick();
                }
                if (particles[i] != null) {
                    if (particles[i].timer <= 0) {
                        particles[i] = null;
                    }
                }
            }
            for (Entity e : getEntities()) {
                if (e instanceof Raider) {
                    return;
                }
            }
            if (!wavetimer) {
                longtim = 20;
                wavetimer = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttons() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if (pressed) {
                pressed = false;
                Declaration.mainMenu.pressed = false;
                currentScene = Declaration.mainMenu;
            }
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            if (pressed && getRaiders().length > 0) {
                pressed = false;
                if (target == 0) {
                    target = getRaiders().length - 1;
                } else {
                    target--;
                }
                Raider r = getRaiders()[target];
                if (r != null) {
                    cameraTarget = getRaiders()[target];
                }
            }
        } else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (pressed) {
                pressed = false;
                cameraTarget = player;
            }
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            if (pressed && getRaiders().length > 0) {
                pressed = false;
                if (target == getRaiders().length - 1) {
                    target = 0;
                } else {
                    target++;
                }
                Raider r = getRaiders()[target];
                if (r != null) {
                    cameraTarget = getRaiders()[target];
                }
            }
        } else {
            pressed = true;
        }
    }

    public void camUpdate() {
        int mx = (int) Mouse.x, my = (int) Mouse.y;
        if (cameraTarget != null) {
            cam.x = -cameraTarget.x;
            cam.y = -cameraTarget.y;
        } else {
            cam.x = 0;
            cam.y = 0;
        }
        if (quake / 10 > 0) {
            cam.x += Main.RANDOM.nextInt(Math.min(quake / 10, 5));
            cam.y += Main.RANDOM.nextInt(Math.min(quake / 10, 5));
        }
        cam.x += (int) (Block.BLOCK_WIDTH / 2) + (Display.getWidth() / 2) - (mx - (Display.getWidth() / 2));
        cam.y += (int) (Block.BLOCK_HEIGHT / 2) + (Display.getHeight() / 2) - (my - (Display.getHeight() / 2));

        floor.x = (int) cameraTarget.x + mx - Display.getWidth();
        floor.y = (int) cameraTarget.y + my - Display.getHeight();
    }

    @Override
    public void longTick() {
        for (Entity e : getEntities()) {
            try {
                e.longTick();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (wavetimer ) {
            if((wave==3 || wave == 5)&& longtim == 20){
                player.expa++;
            }
            if (longtim > 0) {
                longtim--;
            } else {
                wave++;
                Nicknames.free();
                entities.add(new Warrior(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                entities.add(new Warrior(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                entities.add(new Assasin(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                entities.add(new Archer(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                entities.add(new Archer(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                entities.add(new Priest(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                entities.add(new Priest(raidersRespawnPoint.x, raidersRespawnPoint.y, wave));
                wavetimer = false;
            }
        }
    }

    @Override
    public void init() {
        inventory = Declaration.inventory = new Inventory();
        inventory.init();
        training = Declaration.training = new Training();
        training.init();
        Declaration.shop = new Shop();
        gui.add(miniMap);
        gui.add(chat);
        gui.add(statsPanel);
        gui.add(underMousePanel);
        cameraTarget = underMouse = player = new Player(playerRespawnPoint.x, playerRespawnPoint.y);
        for (Point p : minionsRespawnPoints) {
            entities.add(new Mob(p.x, p.y));
        }
        entities.add(player);
        Declaration.inventory.player = player;
        gui.add(new AbilitiesPanel());

        gui.add(new Label("Gold", -50, 5, Color.white) {
            @Override
            public void init() {
                horisontalAlign = Align.RIGHT;
                verticalAlign = Align.TOP;
            }

            @Override
            public String getText() {
                return super.getText() + ": " + player.money;
            }
        });
        gui.add(new Label("Points", -50, 30, Color.white) {
            @Override
            public void init() {
                horisontalAlign = Align.RIGHT;
                verticalAlign = Align.TOP;
            }
            @Override
            public String getText() {
                return super.getText() + ": " + player.expa;
            }
        });
        Declaration.inventory.gui.add(new Label("Gold", -50, 5, Color.white) {
            @Override
            public void init() {
                horisontalAlign = Align.RIGHT;
                verticalAlign = Align.TOP;
            }

            @Override
            public String getText() {
                return super.getText() + ": " + player.money;
            }
        });
        Declaration.inventory.gui.add(new Label("Points", -50, 30, Color.white) {
            @Override
            public void init() {
                horisontalAlign = Align.RIGHT;
                verticalAlign = Align.TOP;
            }

            @Override
            public String getText() {
                return super.getText() + ": " + player.expa;
            }
        });
        Declaration.shop.gui.add(new Label("Gold", -50, 5, Color.white) {
            @Override
            public void init() {
                horisontalAlign = Align.RIGHT;
                verticalAlign = Align.TOP;
            }

            @Override
            public String getText() {
                return super.getText() + ": " + player.money;
            }
        });
        Declaration.training.gui.add(new Label("Points", -50, 30, Color.white) {
            @Override
            public void init() {
                horisontalAlign = Align.RIGHT;
                verticalAlign = Align.TOP;
            }

            @Override
            public String getText() {
                return super.getText() + ": " + player.expa;
            }
        });
    }

    public void deserialized() {
        Declaration.inventory = inventory;
        Declaration.training = training;
        inventory.init();
        Declaration.shop = new Shop();
    }

    @Override
    public void render() {
        camUpdate();
        staticRender();
    }

    public void staticRender() {
        Graphics g = new Graphics();
        g.setColor(new org.newdawn.slick.Color(0, 0, 250));
        g.fillRect(0, 0, Display.getWidth(), Display.getHeight());

        GL11.glTranslated(cam.x, cam.y, 0);
        {
            terrain.render(floor);
            for (int i = 0; i < particles.length; i++) {
                if (particles[i] != null) {
                    particles[i].renderFloor();
                }
            }
            pointer.render();
            for (Entity e : getEntitiesForRender()) {
                try {
                    e.render();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            terrain.renderTops(floor);
            for (int i = 0; i < particles.length; i++) {
                if (particles[i] != null) {
                    particles[i].renderEntity();
                }
            }

            shadow.draw(cameraTarget.x - Display.getWidth() - 50, cameraTarget.y - Display.getHeight() - 50, Display.getWidth() * 2, Display.getHeight() * 2);
        }
        GL11.glTranslated(-cam.x, -cam.y, 0);
    }

    boolean mpressed;

    @Override
    public void handle() {
        underMouse = null;
        buttons();
        if (player.castAbility != null) {
            if (Mouse.left) {
                player.castAbility.cast(new Point(Mouse.x - cam.x, Mouse.y - cam.y));
                frezedMouse = true;
            }
            if (Mouse.right) {
                player.castAbility = null;
            }
        }
        if (frezedMouse) {
            if (Mouse.left) {
                return;
            } else {
                frezedMouse = false;
            }
        }
        for (Entity e : getEntities()) {
            if (player.agro == null && !frezedMouse) {
                try {
                    e.handle();
                } catch (NullPointerException exeption) {
                    exeption.printStackTrace();
                }
            }
            if (Math.abs(e.x - Mouse.x + cam.x) < e.width / 2 && Math.abs(e.y - Mouse.y + cam.y - e.height) < e.height) {
                underMouse = e;
            }
        }

        if (player.agro == null) {

            if (underMouse != null && underMouse.clickable && Mouse.left) {
                underMouse.click();
            }
        } else {
            if (Mouse.left) {
                if (mpressed) {
                    addParticle(new FloatingText((int) (Mouse.x - cam.x), (int) (Mouse.y - cam.y), "GET ME TO THIS MOTHERFUCKER", Color.red));
                    mpressed = false;
                }
            } else {
                mpressed = true;
            }
        }
    }

}
