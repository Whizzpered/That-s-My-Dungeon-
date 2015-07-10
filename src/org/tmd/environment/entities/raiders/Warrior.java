/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities.raiders;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.tmd.environment.Condition.GOING;
import static org.tmd.environment.Condition.JOINED;
import org.tmd.environment.entities.*;
import org.tmd.main.Counter;

/**
 *
 * @author Whizzpered
 */
public class Warrior extends Raider {

    public Warrior(double x, double y, int lvl) {
        super(x, y, lvl);
        detectDistance = 200;
        attackDistance = 96;
        counter = new Counter(300);
    }

    @Override
    public void ai() {
        if (condition == JOINED) {
            if (entried) {
                entried = false;
                counter.start();
            } else {
                if (counter.is()) {
                    goTo(dungeon.playerRespawnPoint.x, dungeon.playerRespawnPoint.y);
                    condition = GOING;
                }
                counter.tick();
            }
        } else if (condition == GOING) {
            double dist = detectDistance;
            for (Entity e : dungeon.getEntities()) {
                if (!e.dead && e.faction == 1) {
                    double d = sqrt(pow(e.x - x, 2) + pow(e.y - y, 2));
                    if (d < dist) {
                        dist = d;
                        focus = e;
                    }
                }
            }

            if (focus != null) {
                if (focus.dead) {
                    focus = null;
                } else {
                    goTo(focus.x, focus.y);
                    attack(focus);
                }
            }

        }
    }

}
