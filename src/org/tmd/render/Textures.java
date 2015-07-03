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
            super(GameLocale.get("load") + GameLocale.get("texture") + name);
            this.name = name;
            this.file = file;
        }

        @Override
        public void load() {      
            try {
                this.texture = TextureLoader.getTexture(name.split("\\.")[0].toUpperCase(), new FileInputStream(file));
                this.image = new Image(this.texture);
                System.out.println("Texture \"" + name + "\" is loaded!");
            } catch (IOException ex) {
                Logger.getLogger(Textures.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static Tex[] textures;

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
        return textures;
    }
}
