package me.urielsalis.lwjglapp2;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author urielsalis
 */
public class Main {
    public static void main(String[] args) {
        initDisplay();

        gameLoop();
        cleanUp();
    }

    private static void cleanUp() {
        Display.destroy();
        Keyboard.destroy();
    }

    private static void gameLoop() {
        Camera cam = new Camera(70, (float)Display.getWidth()/(float)Display.getHeight(), 0.3f, 1000);
        float x = 0;
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();

            glPushMatrix();
            {
                glColor3f(1.0f, 0.5f, 0f);
                glTranslatef(0, 0, -10);
                glRotatef(x, 1, 1, 1);
                glBegin(GL_QUADS);
                {
                    //Front face
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, -1, 1);

                    //Back face
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, -1, -1);

                    //Bottom face
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, 1);
                    glVertex3f(-1, 1, -1);

                    //Top face
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, 1, -1);

                    //Left face
                    glVertex3f(-1, -1, -1);
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(-1, -1, 1);

                    //Right face
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(-1, 1, 1);

                }
                glEnd();
            }
            glPopMatrix();
            x += 1;
            Display.update();
            Display.sync(60);
        }
    }

    private static void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
            Keyboard.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
