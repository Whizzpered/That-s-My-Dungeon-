 /*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render;

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
    
    public void draw(){
        subimage.draw(0, 0);
    }
    
    public void draw(double x, double y){
        subimage.draw((float)x, (float)y);
    }
    
    public void draw(double x, double y, double width, double height){
        subimage.draw((float)x, (float)y, (float)width, (float)height);
    }
    
}
