package org.tmd.environment.entities;

import org.newdawn.slick.Color;
import org.tmd.main.Main;

/**
 *
 * @author yew_mentzaki
 */
public class AreaOfEffect extends Entity {

    public int timer;
    public int delay;

    public boolean isometric;

    public int currentDelayTick;

    public AreaOfEffect(double x, double y, int radius, int timer, int delay) {
        super(x, y);
        this.distance = radius;
        this.timer = timer;
        this.delay = delay;
        phantom = true;
        this.maxhp = (float)Double.MAX_VALUE;
        this.size = 0;
    }

    public AreaOfEffect(double x, double y, int radius, int timer) {
        super(x, y);
        this.distance = radius;
        this.timer = timer;
        this.delay = 1;
        phantom = true;
        this.maxhp = (float)Double.MAX_VALUE;
        this.size = 0;
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
                if (e != this) {
                    if (goodTarget(e)) {
                        if (isometric) {
                            if (Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2)) * 2 <= distance) {
                                cast(e);
                            }
                        } else {
                            if (Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2)) <= distance) {
                                cast(e);
                            }
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
            Main.g.drawOval((int) (x - distance), (int) (y - distance / 2), (int)distance * 2, (int)distance);
        } else {
            Main.g.drawOval((int) (x - distance), (int) (y - distance), (int)distance * 2, (int)distance * 2);
        }
    }
}
