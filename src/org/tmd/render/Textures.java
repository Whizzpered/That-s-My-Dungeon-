 /*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.tmd.main.GameLocale;
import org.tmd.main.LoadTask;

/**
 *
 * @author yew_mentzaki
 */
public class Textures {

    static class Tex extends LoadTask {

        String name;
        File file;
        Texture texture;
        Image image;

        public Tex(String name, File file) {
            super(GameLocale.get("loading") + " " + GameLocale.get("texture") + " \"" + name + "\"...");
            this.name = name;
            this.file = file;
        }

        @Override
        public void load() {
            try {
                this.texture = TextureLoader.getTexture(name.split("\\.")[0].toUpperCase(), new FileInputStream(file));
                this.image = new Image(this.texture);
                this.image.setFilter(GL11.GL_NEAREST);
                this.image.bind();
                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
                glBindTexture(GL_TEXTURE_2D, 0);
                System.out.println("Texture \"" + name + "\" is loaded!");
            } catch (IOException ex) {
                Logger.getLogger(Textures.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static ArrayList<Animation> tempAnimationList = new ArrayList<Animation>();

    private static Textures.Tex[] textures;
    private static Animation[] animations;

    public static void load() {
        ArrayList<Tex> textureList = new ArrayList<Tex>();
        List<File> ff = Arrays.asList(new File("res/textures").listFiles());
        Collections.sort(ff);
        for (File f : ff) {
            if (f.isDirectory()) {
                textureList.addAll(load(f.getName(), f));
            } else {
                textureList.add(new Tex(f.getName(), f));
            }
        }
        textures = new Tex[textureList.size()];
        for (int i = 0; i < textureList.size(); i++) {
            textures[i] = textureList.get(i);
        }
        animations = new Animation[tempAnimationList.size()];
        for (int i = 0; i < tempAnimationList.size(); i++) {
            animations[i] = tempAnimationList.get(i);
        }
        tempAnimationList.clear();
    }

    public static Image image(String name) {
        for (Tex t : textures) {
            if (t.name.equals(name)) {
                return t.image;
            }
        }
        try {
            throw new FileNotFoundException("Image \"" + name + "\" requested but not loaded!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Textures.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Animation animation(String name) {
        for (Animation a : animations) {
            if (a.name.equals(name)) {
                return a;
            }
        }
        return null;
    }

    public static Texture texture(String name) {
        for (Tex t : textures) {
            if (t.name.equals(name)) {
                return t.texture;
            }
        }
        return null;
    }

    private static ArrayList<Tex> load(String names, File folder) {
        ArrayList<Tex> textures = new ArrayList<Tex>();
        List<File> ff = Arrays.asList(folder.listFiles());
        Collections.sort(ff);
        for (File f : ff) {
            if (f.isDirectory()) {
                textures.addAll(load(names + "/" + f.getName(), f));
            } else {
                textures.add(new Tex(names + "/" + f.getName(), f));
            }
        }
        tempAnimationList.add(new Animation(names, textures));
        return textures;
    }
}
