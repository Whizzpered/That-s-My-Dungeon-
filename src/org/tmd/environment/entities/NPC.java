/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class NPC extends Entity{

    Image sprite;
    String dialog;
    
    public NPC(double x, double y, String sprite, String dialog) {
        super(x, y);
        this.sprite = new Image(sprite + "1.png");
        this.dialog = dialog;
        phantom = true;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render() {
        sprite.draw(x - sprite.width, y - sprite.height * 2, sprite.width * 2, sprite.height * 2);
    }
    
}
