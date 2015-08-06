package org.tmd.environment.entities.items;

import org.tmd.environment.entities.Entity;

/**
 *
 * @author Yew_Mentzaki
 */
public abstract class Effect {

    public int timer;
    public float coefficient;
    public Entity owner;
    public Entity target;

    public Effect(Entity owner, int timer, float coefficient, Entity enemy) {
        this.timer = timer;
        this.coefficient = coefficient;
        this.owner = owner;
        this.target = enemy;
        apply();
        enemy.effects.add(this);
    }

    public Effect(int timer, float coefficient, Entity enemy) {
        this.timer = timer;
        this.coefficient = coefficient;
        this.target = enemy;
        apply();
        enemy.effects.add(this);
    }

    public Effect(Entity owner, float coefficient, Entity enemy) {
        this.timer = -1;
        this.coefficient = coefficient;
        this.owner = owner;
        this.target = enemy;
        apply();
        enemy.effects.add(this);
    }

    public void tick() {
        if (timer > 0) {
            timer--;
            if (timer == 0) {
                unapply();
                target.effects.remove(this);
            }
        }
        if (owner != null && (owner.dead || owner.hp <= 0)) {
            unapply();
            target.effects.remove(this);
        }
    }

    public abstract void apply();

    public abstract void unapply();

}
