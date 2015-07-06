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

    private static Tex[] textures;
    private static ArrayList<Animation> animations = new ArrayList<Animation>();

    public static void load() {
        ArrayList<Tex> textureList = new ArrayList<Tex>();
        for (File f : new File("res/textures").listFiles()) {
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
    
    
    public static Animation animation(String name){
        for (Animation a : animations) {
            if (a.name.equals(name)){
                return a;
            }
        }
        try {
            throw new FileNotFoundException("Image \"" + name + "\" requested but not loaded!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Textures.class.getName()).log(Level.SEVERE, null, ex);
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
        for (File f : folder.listFiles()) {
            if (f.isDirectory()) {
                textures.addAll(load(names + "/" + f.getName(), f));
            } else {
                textures.add(new Tex(names + "/" + f.getName(), f));
            }
        }
        animations.add(new Animation(names, textures));
        return textures;
    }
}
