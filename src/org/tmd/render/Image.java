 /*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author yew_mentzaki
 */
public class Image {

    public final String path;
    private org.newdawn.slick.Image subimage;
    public final int width, height;

    public Image(String path) {
        this.path = path;
        this.subimage = Textures.image(path);
        this.width = subimage.getWidth();
        this.height = subimage.getHeight();
    }

    public void draw() {
        subimage.draw(0, 0);
    }

    public void draw(double x, double y) {
        subimage.draw((float) x, (float) y);
    }

    public void draw(double x, double y, double width, double height) {
        subimage.draw((float) x, (float) y, (float) width, (float) height);
    }

    public void draw(double angle) {
        draw(0, 0, angle);
    }

    public void draw(double x, double y, double angle) {
        draw(x, y, width, height, angle);
    }

    public void draw(double x, double y, double width, double height, double angle) {
        GL11.glTranslated(x, y, 0);
        GL11.glRotated(angle / Math.PI * 180.0, 0, 0, 1);
        subimage.draw((float) width / 2f, (float) height / 2f, (float) width, (float) height);
        GL11.glRotated(angle / Math.PI * 180.0, 0, 0, 1);
        GL11.glTranslated(x, y, 0);
    }
    
    public void bind(){
        subimage.bind();
    }
    
    public static void unbind(){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

}
