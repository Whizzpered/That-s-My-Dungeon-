package org.tmd.environment.entities;

import org.newdawn.slick.Color;
import org.tmd.main.Main;

/**
 *
 * @author yew_mentzaki
 */
public class AreaOfEffect extends Entity {

    public int radius;
    public int timer;
    public int delay;

    public boolean isometric;

    public int currentDelayTick;

    public AreaOfEffect(double x, double y, int radius, int timer, int delay) {
        super(x, y);
        this.radius = radius;
        this.timer = timer;
        this.delay = delay;
    }

    public AreaOfEffect(double x, double y, int radius, int timer) {
        super(x, y);
        this.radius = radius;
        this.timer = timer;
        this.delay = 1;
    }

    @Override
    public void tick() {
        timer--;
        if (timer == 0) {
            dungeon.entities.remove(this);
        }
        if (currentDelayTick > 0) {
            currentDelayTick--;
        } else {
            currentDelayTick = delay;
            for (Entity e : dungeon.getEntities()) {
                if (goodTarget(e)) {
                    if (isometric) {
                        if (Math.sqrt(Math.pow(x - e.x, 2)) + Math.pow(y - e.y, 2) * 2 < distance) {
                            cast(e);
                        }
                    } else {
                        if (Math.sqrt(Math.pow(x - e.x, 2)) + Math.pow(y - e.y, 2) < distance) {
                            cast(e);
                        }
                    }
                }
            }
        }
    }

    public boolean goodTarget(Entity e) {
        return true;
    }

    public void cast(Entity e) {

    }

    @Override
    public void render() {
        Main.g.setColor(Color.yellow);
        if (isometric) {
            Main.g.drawOval((int) (x - radius), (int) (y - radius / 2), radius * 2, radius);
        } else {
            Main.g.drawOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
        }
    }
}
