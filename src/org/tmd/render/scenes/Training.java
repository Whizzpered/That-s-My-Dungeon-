package org.tmd.render.scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Color;
import org.tmd.render.gui.AbilityTrain;
import org.tmd.render.gui.Align;
import org.tmd.render.gui.Button;
import org.tmd.render.gui.Label;
import static org.tmd.render.scenes.Scene.currentScene;

/**
 *
 * @author Yew_Mentzaki
 */
public class Training extends Scene {

    @Override
    public void init() {
        String string = new String();
        try {
            Scanner scanner = new Scanner(new File("res/text/abilities.csv"));
            while (scanner.hasNextLine()) {
                string += scanner.nextLine() + '\n';
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Training.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] lines = string.split("\n");
        int width = lines[0].split(",").length;
        AbilityTrain[][] at = new AbilityTrain[width + 1][(lines.length + 1) / 2];
        for (int i = 0; i < lines.length; i += 2) {
            String[] ab = lines[i].split(",");
            for (int j = 0; j < ab.length; j++) {
                if (ab[j].length() > 0) {
                    at[j][i / 2] = new AbilityTrain(ab[j], i == 0, -(width * 35) + 70 * j, 30 + i * 55);
                    at[j][i / 2].horisontalAlign = Align.CENTER;
                    gui.add(at[j][i / 2]);
                }
            }
        }
        for (int i = 1; i < lines.length; i += 2) {
            String[] ab = lines[i].split(",");
            for (int j = 0; j < ab.length; j++) {
                if (ab[j].length() > 0) {
                    if (ab[j].contains("/")) {
                        at[j][(i - 1) / 2].left = at[j - 1][(i - 1) / 2 + 1];
                    }
                    if (ab[j].contains("|")) {
                        at[j][(i - 1) / 2].center = at[j][(i - 1) / 2 + 1];
                    }
                    if (ab[j].contains("\\")) {
                        at[j][(i - 1) / 2].right = at[j + 1][(i - 1) / 2 + 1];
                    }
                }
            }
        }
        gui.add(new Button("exit", 25, -25, 150, 50){

            @Override
            public void init() {
                verticalAlign = Align.DOWN;
            }
            
            @Override
            public void click() {
                currentScene = Declaration.dungeon;
            }
            
        });
    }

    @Override
    public boolean handleGUI() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Declaration.dungeon.pressed = false;
            currentScene = Declaration.dungeon;
        }
        return super.handleGUI();
    }

    @Override
    public void render() {
        if (Declaration.dungeon != null) {
            Declaration.dungeon.render();
            Main.g.setColor(new Color(0, 0, 0, 150).slickColor());
            Main.g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
        }

    }

    @Override
    public void renderGUI() {
        super.renderGUI();
        if (currentTip != null) {
            currentTip.render();
        }
    }
}
