/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.render.gui;

import static org.lwjgl.opengl.GL11.*;
import org.tmd.render.Color;
import org.tmd.main.Main;
import org.tmd.render.Image;

/**
 *
 * @author yew_mentzaki
 */
public class Frame {

    public static Frame defaultFrame = new Frame("frame");
    public static Frame glassFrame = new Frame("glass_frame");
    public static Frame grayFrame = new Frame("gray_frame");

    Image center;
    Image angle;
    Image line;

    public Frame(String frame) {
        center = new Image("gui/" + frame + "/center.png");
        angle = new Image("gui/" + frame + "/angle.png");
        line = new Image("gui/" + frame + "/line.png");
    }

    
    public void render(double x, double y, double width, double height) {
        glColor3f(1, 1, 1);
        angle.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(x, y);
            glTexCoord2d(1, 0);
            glVertex2d(x + 16, y);
            glTexCoord2d(1, 1);
            glVertex2d(x + 16, y + 16);
            glTexCoord2d(0, 1);
            glVertex2d(x, y + 16);
        }
        {
            glTexCoord2d(0, 1);
            glVertex2d(x + width - 16, y);
            glTexCoord2d(0, 0);
            glVertex2d(x + width, y);
            glTexCoord2d(1, 0);
            glVertex2d(x + width, y + 16);
            glTexCoord2d(1, 1);
            glVertex2d(x + width - 16, y + 16);
        }
        {
            glTexCoord2d(1, 1);
            glVertex2d(x + width - 16, y + height - 16);
            glTexCoord2d(0, 1);
            glVertex2d(x + width, y + height - 16);
            glTexCoord2d(0, 0);
            glVertex2d(x + width, y + height);
            glTexCoord2d(1, 0);
            glVertex2d(x + width - 16, y + height);
        }
        {
            glTexCoord2d(1, 0);
            glVertex2d(x, y + height - 16);
            glTexCoord2d(1, 1);
            glVertex2d(x + 16, y + height - 16);
            glTexCoord2d(0, 1);
            glVertex2d(x + 16, y + height);
            glTexCoord2d(0, 0);
            glVertex2d(x, y + height);
        }
        glEnd();
        line.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(x + 16, y);
            glTexCoord2d((width - 32) / 16, 0);
            glVertex2d(x + width - 16, y);
            glTexCoord2d((width - 32) / 16, 1);
            glVertex2d(x + width - 16, y + 16);
            glTexCoord2d(0, 1);
            glVertex2d(x + 16, y + 16);
        }
        {
            glTexCoord2d(0, 0);
            glVertex2d(x, y + 16);
            glTexCoord2d((height - 32) / 16, 0);
            glVertex2d(x, y + height - 16);
            glTexCoord2d((height - 32) / 16, 1);
            glVertex2d(x + 16, y + height - 16);
            glTexCoord2d(0, 1);
            glVertex2d(x + 16, y + 16);
        }
        {
            glTexCoord2d(0, 0);
            glVertex2d(x + 16, y + height);
            glTexCoord2d((width - 32) / 16, 0);
            glVertex2d(x + width - 16, y + height);
            glTexCoord2d((width - 32) / 16, 1);
            glVertex2d(x + width - 16, y + height - 16);
            glTexCoord2d(0, 1);
            glVertex2d(x + 16, y + height - 16);
        }
        {
            glTexCoord2d(0, 0);
            glVertex2d(x + width, y + 16);
            glTexCoord2d((height - 32) / 16, 0);
            glVertex2d(x + width, y + height - 16);
            glTexCoord2d((height - 32) / 16, 1);
            glVertex2d(x + width - 16, y + height - 16);
            glTexCoord2d(0, 1);
            glVertex2d(x + width - 16, y + 16);
        }
        glEnd();
        center.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(x + 16, y + 16);
            glTexCoord2d((width - 32) / 16, 0);
            glVertex2d(x + width - 16, y + 16);
            glTexCoord2d((width - 32) / 16, (height - 32) / 16);
            glVertex2d(x + width - 16, y + height - 16);
            glTexCoord2d(0, (height - 32) / 16);
            glVertex2d(x + 16, y + height - 16);
        }
        glEnd();
    }

}
