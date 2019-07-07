
package org.yourorghere;

import javax.media.opengl.GL;


public class Dot {
    private double xc, yc, radius;
    Packman pacman;
    boolean eated = false;
    Dot(double x, double y, double r,Packman p) {
        xc = x;
        yc = y;
        radius = r;
        pacman = p;
    }
    void isEated(){
    if((xc-radius)<=(pacman.xc+pacman.radius)
     &&(pacman.xc-pacman.radius)<=(xc+radius)
     &&(pacman.yc-pacman.radius)<=(yc+radius)
     &&(yc-radius)<=(pacman.yc+pacman.radius))    
    eated= true;
    }
    void draw(GL gl) {
      int xc = (int) this.xc;
      int yc = (int) this.yc;
      gl.glColor3f(1, 1, 1);
      gl.glPointSize(4.0f);
      gl.glBegin(GL.GL_POINTS);
        gl.glVertex2i(xc, yc);
        int d = 1 - (int)radius;
        int x = 0;
        int y = (int)radius;
        while (y > x) {
            if (d < 0) {
                d += 2*x + 3;	
            } else {
                d += 2*x - 2*y + 5;
                y--;
            }
            x++;
            gl.glVertex2i(x + xc, y + yc);
            gl.glVertex2i(-x + xc, y + yc);
            gl.glVertex2i(-x + xc, -y + yc);
            gl.glVertex2i(x + xc, -y + yc);
            gl.glVertex2i(y + xc, x + yc);
            gl.glVertex2i(-y + xc, x + yc);
            gl.glVertex2i(-y + xc, -x + yc);
            gl.glVertex2i(y + xc, -x + yc);
        }
      gl.glEnd();
      gl.glFlush();
    }
}
