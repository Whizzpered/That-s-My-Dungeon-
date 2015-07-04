/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.main;

import java.util.ArrayList;

/**
 *
 * @author yew_mentzaki
 */
public abstract class LoadTask {
    public static ArrayList<LoadTask> tasks = new ArrayList<LoadTask>();
    public final String text;
    public LoadTask(String text) {
        this.text = text;
        tasks.add(this);
    }
    public abstract void load();
}
