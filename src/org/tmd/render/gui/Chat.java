/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.render.gui;

import org.tmd.environment.entities.Entity;

/**
 *
 * @author Whizzpered
 */
public class Chat {

    public String[] nicknames = new String[10], replicks = new String[10];
    public String[] dialog, rage;

    public void addMessage(Entity ent) {
        if (ent == null || ent.name == null) {
            return;
        }
        for (int i = 8; i >= 0; i--) {
            nicknames[i + 1] = nicknames[i];
            replicks[i + 1] = replicks[i];
        }
        nicknames[0] = ent.name;
        replicks[0] = "FUCK YOUR MOM";
    }

    public void render() {
        
    }

}
