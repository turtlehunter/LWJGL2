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
public class LWJGLApplication2 {
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

        boolean temp = false;

        while (!Display.isCloseRequested()) {
            boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP);
            boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);

            if(forward)
                cam.move(0.5f, 1);
            if(backward)
                cam.move(-0.5f, 1);
            if(left)
                cam.move(0.5f, 0);
            if(right)
                cam.move(-0.5f, 0);

            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
                cam.rotateY(-0.5f);
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
                cam.rotateY(0.5f);

            if((forward || backward) && (left || right)) {
                temp = true;
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();

            glPushMatrix();
            {
                glColor3f(1.0f, 0.5f, 0f);
                glTranslatef(0, 0, -10);
                //glRotatef(x, 1, 1, 1);
                
                if(temp) {
                    glRotatef(45, 0, 1, 0);
                }

                glBegin(GL_QUADS);
                {
                    //Front face
                    glColor3f(1f, 0f, 0f);
                    glVertex3f(-1, -1, 1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(-1, 1, 1);

                    //Back face
                    glColor3f(0f, 1f, 0f);
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, -1, -1);

                    //Bottom face
                    glColor3f(0f, 0f, 1f);
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, 1);
                    glVertex3f(-1, 1, -1);

                    //Top face
                    glColor3f(1f, 1f, 0f);
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, 1, -1);

                    //Left face
                    glColor3f(0f, 1f, 1f);
                    glVertex3f(-1, -1, -1);
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(-1, -1, 1);

                    //Right face
                    glColor3f(1f, 0f, 1f);
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
            Logger.getLogger(LWJGLApplication2.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
