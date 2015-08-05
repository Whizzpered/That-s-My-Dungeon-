/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import static java.lang.Math.*;
import org.tmd.render.Animation;
import org.tmd.render.scenes.Dialog;

/**
 *
 * @author yew_mentzaki
 */
public class NPC extends Entity {

    Animation sprite;
    String dialog;

    public NPC(double x, double y, String sprite, String name, String dialog) {
        super(x, y);
        this.sprite = new Animation(sprite);
        this.name = name;
        this.sprite.delay = 250;
        this.dialog = dialog;
        maxhp = (float)Double.MAX_VALUE;
        phantom = true;
        level = 9001;
        clickable = true;
    }

    @Override
    public void tick() {
        if (dungeon.player != null && dungeon.player.focus == this) {
            dungeon.player.attackReload = 10;
            if (sqrt(pow(dungeon.player.x - x, 2) + pow(dungeon.player.y - y, 2)) < 150) {
                dungeon.player.focus = null;
                Dialog.set(dialog);
            }
        }

    }

    @Override
    public void render() {
        sprite.get().draw(x - sprite.get().width, y - sprite.get().height * 2, sprite.get().width * 2, sprite.get().height * 2);
    }

    @Override
    public void click() {
        dungeon.player.focus = this;
    }

}
