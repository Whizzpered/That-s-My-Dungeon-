/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.scenes;

import java.io.Serializable;
import java.util.ArrayList;
import org.tmd.render.gui.Element;
import org.tmd.render.gui.ToolTip;

/**
 *
 * @author yew_mentzaki
 */
public class Scene implements Serializable {

    public static Scene currentScene;
    public ToolTip currentTip;
    public ArrayList<Element> gui = new ArrayList<Element>();
    protected boolean loaded;
    public boolean pressed;

    public void init() {

    }

    public void tick() {

    }

    public void longTick() {

    }

    public void render() {

    }

    public void renderGUI() {
        for (int i = 0; i < gui.size(); i++) {
            gui.get(i).render();
        }
    }

    public void handle() {
    }

    public boolean handleGUI() {
        for (int i = gui.size() - 1; i >= 0; i--) {
            if (gui.get(i).handle()) {
                return true;
            }
        }
        return false;
    }

    public final void handleScene() {
        if (!handleGUI()) {
            handle();
        }
    }

    public final void renderScene() {
        if (!loaded) {
            init();
            loaded = true;
        }
        render();
        renderGUI();
    }

    public void set() {
        currentScene = this;
    }
}
