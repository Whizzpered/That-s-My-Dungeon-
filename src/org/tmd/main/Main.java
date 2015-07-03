/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import org.tmd.xfg.*;

/**
 *
 * @author yew_mentzaki
 */
public class Main {

    private static XFG windowXFG;

    public static void main(String[] args) {
        setUpNatives();
        setUpDisplay();
    }

    public static void setUpDisplay() {
        try {
            try {
                if (new File("cfg/conf.xfg").exists()) {
                    windowXFG = new XFG(new File("cfg/conf.xfg"));
                } else {
                    windowXFG = new XFG();
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
                        windowXFG.set("x", gdd.getDefaultConfiguration().getBounds().x);
                        windowXFG.set("y", gdd.getDefaultConfiguration().getBounds().y);
                        windowXFG.set("width", gdd.getDisplayMode().getWidth());
                        windowXFG.set("height", gdd.getDisplayMode().getHeight());
                        windowXFG.writeToFile(new File("cfg/conf.xfg"));
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            Display.setDisplayMode(new DisplayMode(windowXFG.get("width").getInteger(), windowXFG.get("height").getInteger()));
            Display.setTitle("That's My Dungeon!");
            Display.setLocation(windowXFG.get("x").getInteger(), windowXFG.get("y").getInteger());
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
                Image i = new Image("res/textures/gui/logo.png");
                i.draw((Display.getWidth() - i.getWidth()) / 2, (Display.getHeight() - i.getHeight()) / 2);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Graphics g = new Graphics();
        int displayWidth = Display.getWidth(), displayHeight = Display.getHeight();
        try {
            while (!Display.isCloseRequested()) {
                long beforeRender = System.currentTimeMillis();
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                if (displayWidth != Display.getWidth() || displayHeight != Display.getHeight()) {
                    displayWidth = Display.getWidth();
                    displayHeight = Display.getHeight();
                    glViewport(0, 0, Display.getWidth(), Display.getHeight());
                }
                gluPerspective((float) 90, displayWidth / displayHeight, 0.001f, 1000);
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                glEnable(GL11.GL_BLEND);
                glEnable(GL11.GL_TEXTURE_2D);
                glEnable(GL_ALPHA_TEST);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
                glClearColor(0, 0, 0, 0);
                Display.update();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n" + e);
        }
        windowXFG.set("x", Display.getX());
        windowXFG.set("y", Display.getY());
        windowXFG.set("width", Display.getWidth());
        windowXFG.set("height", Display.getHeight());
        try {
            windowXFG.writeToFile(new File("cfg/conf.xfg"));
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
