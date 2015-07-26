/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.main;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Random;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.tmd.render.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.tmd.render.Textures;
import org.tmd.render.gui.Mouse;
import org.tmd.render.scenes.Dungeon;
import org.tmd.render.scenes.Scene;
import org.tmd.xfg.*;
/**
 *
 * @author yew_mentzaki
 */
public class Main {
    public static final Random RANDOM = new Random();
    public static XFG conf;
    private static boolean exit;
    private static Image logo;
    public static Graphics g = new Graphics();
    public static FontRender defaultFont;
    public static String version = "pre-alpha 2.2";
    public static ActionListener longTimerListener = new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            try {
                if (Scene.currentScene != null) {
                    Scene.currentScene.longTick();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }, tickTimerListener = new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            try {
                if (Scene.currentScene != null) {
                    Scene.currentScene.tick();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public static Timer longTimer = new Timer(1000, Main.longTimerListener),
            tickTimer1 = new Timer(10, Main.tickTimerListener),
            tickTimer2 = new Timer(10, Main.tickTimerListener);
    private static Object Serialize;
    public static void main(String[] args) {
        setUpNatives();
        setUpDisplay();
    }
    public static void exit() {
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
            Sounds.startBackgroundLoad();
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
            Font f = null;
            InputStream in = new FileInputStream(new File("res/fonts/dejavusans.ttf"));
            f = Font.createFont(Font.TRUETYPE_FONT, in);
            f = f.deriveFont(18f);
            defaultFont = FontRender.getTextRender(f);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        int displayWidth = Display.getWidth(), displayHeight = Display.getHeight();
        float allTasks = LoadTask.tasks.size();
        try {
            while (LoadTask.tasks.size() > 0) {
                if (Display.isCloseRequested()) {
                    Display.destroy();
                    System.exit(0);
                }
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
                g.setColor(new Color(154, 185, 233).slickColor());
                g.fillRect(16, displayHeight - 27, (float) (displayWidth - 32), 14);
                g.setColor(new Color(6, 18, 39).slickColor());
                g.fillRect(20, displayHeight - 23, (float) (displayWidth - 40), 6);
                g.setColor(Color.white.slickColor());
                g.fillRect(21, displayHeight - 22, (float) (displayWidth - 42) * (1 - (float) LoadTask.tasks.size() / allTasks), 4);
                Display.update();
                LoadTask.tasks.get(0).load();
                LoadTask.tasks.remove(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n" + e);
            System.exit(1);
        }
        logo.getTexture().release();
        Nicknames.init();
        File dung = new File("save.data");
        if (dung.exists()) {
            try {
                byte[] b = Serialization.readFromFile(dung);
                Declaration.dungeon = (Dungeon) Serialization.deserialize(b);
                Declaration.dungeon.deserialized();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Nicknames.init();
        Declaration.mainMenu.set();
        longTimer.start();
        tickTimer1.start();
        tickTimer2.start();
        Sounds.musicAudio.playAsSoundEffect(Sounds.music, Sounds.music, true);
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
                if (Scene.currentScene != null) {
                    Mouse.update();
                    Scene.currentScene.handleScene();
                    Scene.currentScene.renderScene();
                }
                Display.update();
            }
            try {
                byte[] b = Serialization.serialize(Declaration.dungeon);
                Serialization.writeToFile(b, new File("save.data"));
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
        System.exit(0);
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