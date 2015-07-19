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
    public XFG replicList;
    public String locale;

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
        for (int i = 8; i >= 0; i--) {
            nicknames[i + 1] = nicknames[i];
            replics[i + 1] = replics[i];
        }
        String l = GameLocale.get("locale_name");
        System.out.println(l);
        System.out.println(this.locale);
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
        nicknames[0] = ent.nickmame;
        if (type == messageType.TYPE_JOINED) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_joined").size());
            replics[0] = replicList.get("raider_joined").get(i).getString();
        } else if (type == messageType.TYPE_GOING) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_going").size());
            replics[0] = replicList.get("raider_going").get(i).getString();
        } else if (type == messageType.TYPE_BATTLE) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_battle").size());
            replics[0] = replicList.get("raider_battle").get(i).getString();
        } else if (type == messageType.TYPE_HEAL) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_heal").size());
            replics[0] = replicList.get("raider_heal").get(i).getString();
        } else if (type == messageType.TYPE_RISE) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_heal").size());
            replics[0] = replicList.get("raider_heal").get(i).getString();
        } else if (type == messageType.TYPE_NOTANKS) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_notanks").size());
            replics[0] = replicList.get("raider_notanks").get(i).getString();
        } else if (type == messageType.TYPE_DEAD) {
            int i = Main.RANDOM.nextInt(replicList.get("raider_dead").size());
            replics[0] = replicList.get("raider_dead").get(i).getString();
        }
    }

    @Override
    public void render() {
        for (int i = 0; i < 10; i++) {
            if (nicknames[i] == null) {
                break;
            }
            Main.defaultFont.drawString(nicknames[i] + ":", (int) getX(), (int) (getY() + height - i * 25), Color.black);
            Main.defaultFont.drawString(replics[i], (int) getX() + Main.defaultFont.getWidth(nicknames[i] + ": "), (int) (getY() + height - i * 25), Color.black);
            Main.defaultFont.drawString(nicknames[i] + ":", (int) getX(), (int) (getY() + height - i * 25) - 2, Color.yellow);
            Main.defaultFont.drawString(replics[i], (int) getX() + Main.defaultFont.getWidth(nicknames[i] + ": "), (int) (getY() + height - i * 25) - 2, Color.white);
        }
    }

    @Override
    public boolean handle() {
        return false;
    }
}
