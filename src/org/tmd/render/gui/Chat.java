/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmd.environment.entities.Entity;
import org.tmd.main.GameLocale;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.xfg.XFG;

/**
 *
 * @author Whizzpered
 */
public class Chat extends Element {

    public String[] nicknames = new String[10];
    public String[] replics = new String[10];
    public int[] color = new int[10];
    public XFG replicList;
    public String locale;

    public String[] addNicknames = new String[25];
    public String[] addReplics = new String[25];
    public int[] addString = new int[25];

    public Chat(double x, double y, double width, double height) {
        super(x, y, width, height);
        locale = GameLocale.get("locale_name");
        try {
            this.replicList = new XFG(new File("locale/" + locale + "/replics.xfg"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            try {
                this.replicList = new XFG(new File("locale/" + "en_US" + "/replics.xfg"));
            } catch (FileNotFoundException ex2) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static enum messageType {

        TYPE_JOINED,
        TYPE_GOING,
        TYPE_BATTLE,
        TYPE_HEAL,
        TYPE_RISE,
        TYPE_NOTANKS,
        TYPE_DEAD
    }

    public void addMessage(Entity ent, messageType type) {
        if (ent == null || ent.nickmame == null) {
            return;
        }
        String l = GameLocale.get("locale_name");
        if (!l.equals(this.locale)) {
            this.locale = l;
            try {
                this.replicList = new XFG(new File("locale/" + l + "/replics.xfg"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                try {
                    this.replicList = new XFG(new File("locale/" + "en_US" + "/replics.xfg"));
                } catch (FileNotFoundException ex2) {
                    Logger.getLogger(Chat.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int id = 0;
        for (id = 0; id < 25; id++) {
            if (addString[id] == 0) {
                break;
            }
        }
        addNicknames[id] = ent.nickmame;
        if (type == messageType.TYPE_JOINED) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_joined").size());
            addReplics[id] = replicList.get("raider_joined").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        } else if (type == messageType.TYPE_GOING) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_going").size());
            addReplics[id] = replicList.get("raider_going").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        } else if (type == messageType.TYPE_BATTLE) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_battle").size());
            addReplics[id] = replicList.get("raider_battle").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        } else if (type == messageType.TYPE_HEAL) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_heal").size());
            addReplics[id] = replicList.get("raider_heal").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        } else if (type == messageType.TYPE_RISE) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_heal").size());
            addReplics[id] = replicList.get("raider_heal").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        } else if (type == messageType.TYPE_NOTANKS) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_notanks").size());
            addReplics[id] = replicList.get("raider_notanks").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        } else if (type == messageType.TYPE_DEAD) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_dead").size());
            addReplics[id] = replicList.get("raider_dead").get(i).getString();
            addString[id] = addReplics[id].length() * 25 + Main.RANDOM.nextInt(200);
        }
    }

    public void tick() {
        for (int i = 0; i < 10; i++) {
            if (color[i] > 0) {
                color[i]--;
            }
        }
        for (int i = 0; i < 25; i++) {
            if (addString[i] > 0) {
                addString[i]--;
                if (addString[i] == 0) {
                    for (int j = 8; j >= 0; j--) {
                        nicknames[j + 1] = nicknames[j];
                        replics[j + 1] = replics[j];
                        color[j + 1] = color[j];
                    }
                    nicknames[0] = addNicknames[i];
                    replics[0] = addReplics[i];
                    color[0] = 500;
                }
            }
        }
    }

    @Override
    public void render() {
        for (int i = 0; i < 10; i++) {
            if (nicknames[i] == null) {
                break;
            }
            int color = this.color[i];
            if (color > 255) {
                color = 255;
            }
            Main.defaultFont.drawString(nicknames[i] + ":", (int) getX(), (int) (getY() + height - i * 25), new Color(0, 0, 0, color));
            Main.defaultFont.drawString(replics[i], (int) getX() + Main.defaultFont.getWidth(nicknames[i] + ": "), (int) (getY() + height - i * 25), new Color(0, 0, 0, color));
            Main.defaultFont.drawString(nicknames[i] + ":", (int) getX(), (int) (getY() + height - i * 25) - 2, new Color(255, 255, 0, color));
            Main.defaultFont.drawString(replics[i], (int) getX() + Main.defaultFont.getWidth(nicknames[i] + ": "), (int) (getY() + height - i * 25) - 2, new Color(255, 255, 255, color));
        }
    }

    @Override
    public boolean handle() {
        return false;
    }
}
