/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.tmd.render.Textures;
import org.tmd.render.gui.Mouse;
import org.tmd.render.scenes.Scene;
import org.tmd.xfg.*;

/**
 *
 * @author yew_mentzaki
 */
public class Main {

    public static XFG conf;
    private static boolean exit;
    private static Image logo;
    public static Graphics g = new Graphics();
    public static FontRender defaultFont;

    public static void main(String[] args) {
        setUpNatives();
        setUpDisplay();
    }
    
    public static void exit(){
        exit = true;
    }

    public static void setUpDisplay() {
        try {
            try {
                if (new File("cfg/conf.xfg").exists()) {
                    conf = new XFG(new File("cfg/conf.xfg"));
                } else {
                    conf = new XFG();
                    int maxWidth = 0;
                    GraphicsDevice gdd = null;
                    for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
                        if (maxWidth < gd.getDisplayMode().getWidth()) {
                            maxWidth = gd.getDisplayMode().getWidth();
                            gdd = gd;
                        }
                    }
                    if (gdd == null) {
                        System.err.println("No graphic device found!");
                        System.exit(1);
                    } else {
                        conf.set("x", gdd.getDefaultConfiguration().getBounds().x);
                        conf.set("y", gdd.getDefaultConfiguration().getBounds().y);
                        conf.set("width", gdd.getDisplayMode().getWidth());
                        conf.set("height", gdd.getDisplayMode().getHeight());
                        conf.set("locale", "en_US");
                        conf.writeToFile(new File("cfg/conf.xfg"));
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            GameLocale.load(conf.get("locale").getString());

            Display.setDisplayMode(new DisplayMode(conf.get("width").getInteger(), conf.get("height").getInteger()));
            Display.setTitle("That's My Dungeon!");
            Display.setLocation(conf.get("x").getInteger(), conf.get("y").getInteger());
            Display.setResizable(true);
            Display.create();
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            glEnable(GL11.GL_BLEND);
            glEnable(GL11.GL_TEXTURE_2D);
            glEnable(GL_ALPHA_TEST);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
            glClearColor(0, 0, 0, 0);
            glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
            try {
                logo = new Image("res/textures/gui/logo.png");
                logo.draw((Display.getWidth() - logo.getWidth()) / 2, (Display.getHeight() - logo.getHeight()) / 2);

            } catch (SlickException ex) {
                Logger.getLogger(Main.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Display.update();
        } catch (LWJGLException e) {
            Display.destroy();
            JOptionPane.showMessageDialog(null, "Error!\n" + e);
            System.exit(1);
        }
        try {
            Textures.load();
            defaultFont = FontRender.getTextRender("sans cherif", 0, 18);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int displayWidth = Display.getWidth(), displayHeight = Display.getHeight();
        float allTasks = LoadTask.tasks.size();
        try {
            while (LoadTask.tasks.size() > 0) {
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                if (displayWidth != Display.getWidth() || displayHeight != Display.getHeight()) {
                    displayWidth = Display.getWidth();
                    displayHeight = Display.getHeight();
                    glViewport(0, 0, Display.getWidth(), Display.getHeight());
                }
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
                glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                glEnable(GL11.GL_BLEND);
                glEnable(GL11.GL_TEXTURE_2D);
                glEnable(GL_ALPHA_TEST);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
                glClearColor(0, 0, 0, 0);
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
                logo.draw((Display.getWidth() - logo.getWidth()) / 2, (Display.getHeight() - logo.getHeight()) / 2);
                defaultFont.drawString(LoadTask.tasks.get(0).text, 16, displayHeight - 60, Color.white);
                g.setColor(new Color(154, 185, 233));
                g.fillRect(16, displayHeight - 27, (float) (displayWidth - 32), 14);
                g.setColor(new Color(6, 18, 39));
                g.fillRect(20, displayHeight - 23, (float) (displayWidth - 40), 6);
                g.setColor(Color.white);
                g.fillRect(21, displayHeight - 22, (float) (displayWidth - 42) * (1 - (float) LoadTask.tasks.size() / allTasks), 4);
                Display.update();
                LoadTask.tasks.get(0).load();
                LoadTask.tasks.remove(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n" + e);
            System.exit(1);
        }
        Declaration.mainMenu.set();
        try {
            while (!(Display.isCloseRequested() | exit)) {
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                if (displayWidth != Display.getWidth() || displayHeight != Display.getHeight()) {
                    displayWidth = Display.getWidth();
                    displayHeight = Display.getHeight();
                    glViewport(0, 0, Display.getWidth(), Display.getHeight());
                }
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
                glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                glEnable(GL11.GL_BLEND);
                glEnable(GL11.GL_TEXTURE_2D);
                glEnable(GL_ALPHA_TEST);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
                glClearColor(0, 0, 0, 0);
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
                if(Scene.currentScene != null){
                    Mouse.update();
                    Scene.currentScene.handleScene();
                    Scene.currentScene.renderScene();
                }
                Display.update();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error!\n" + e);
        }
        conf.set("x", Display.getX());
        conf.set("y", Display.getY());
        conf.set("width", Display.getWidth());
        conf.set("height", Display.getHeight());
        try {
            conf.writeToFile(new File("cfg/conf.xfg"));
        } catch (FileNotFoundException ex) {
            System.out.println("Configuration doesn't saved!");
        } catch (IOException ex) {
            System.out.println("Configuration doesn't saved!");
        }
        Display.destroy();
        System.exit(1);

    }

    public static void setUpNatives() {
        if (!new File("native").exists()) {

            JOptionPane.showMessageDialog(null, "Error!\nNative libraries not found!");
            System.exit(1);
        }
        try {

            System.setProperty("java.library.path", new File("native").getAbsolutePath());

            Field fieldSysPath = ClassLoader.class
                    .getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(
                    true);

            try {
                fieldSysPath.set(null, null);
            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
                System.exit(1);
            } catch (IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
                System.exit(1);
            }
        } catch (NoSuchFieldException ex) {
            JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
            System.exit(1);
        } catch (SecurityException ex) {
            JOptionPane.showMessageDialog(null, "Error!\n" + ex.toString());
            System.exit(1);
        }
    }

}
