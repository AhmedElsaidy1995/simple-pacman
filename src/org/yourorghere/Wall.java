
package org.yourorghere;

import javax.media.opengl.GL;

public class Wall {
    double x1, y1, x2, y2;
    Packman pacman;
    Wall(double a, double b, double c, double d , Packman p) {
	x1 = a;
	x2 = b;
	y1 = c;
	y2 = d;
        pacman = p;
    }
    void draw(GL gl) {
	gl.glColor3f(0, 0, 1);
	gl.glPointSize(3.0F);
	gl.glBegin(GL.GL_POLYGON);
            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y1);
            gl.glVertex2d(x2, y2);
            gl.glVertex2d(x1, y2);
	gl.glEnd();
	gl.glFlush();
    }
    boolean hitUp(){
    if((pacman.yc - pacman.radius - 20)==y2&& 
       (pacman.xc-pacman.radius<x2+20 && pacman.xc>x1
      ||pacman.xc+pacman.radius>x1-20 && pacman.xc<x2))  
        return true;
    return false;
    }
    boolean hitDown(){
    if((pacman.yc + pacman.radius + 20)==y1 && 
       (pacman.xc-pacman.radius<x2+20 && pacman.xc>x1
      ||pacman.xc+pacman.radius>x1-20 && pacman.xc<x2)) 
        return true;
    return false;
    }
    boolean hitLeft(){
    if((pacman.xc + pacman.radius + 20)==x1 &&
       (pacman.yc-pacman.radius<y2+20 && pacman.yc>y1
      ||pacman.yc+pacman.radius>y1-20 && pacman.yc<y2))
        return true;
    return false;
    }
    boolean hitRight(){
    if((pacman.xc - pacman.radius - 20)==x2 &&
       (pacman.yc-pacman.radius<y2+20 && pacman.yc>y1
      ||pacman.yc+pacman.radius>y1-20 && pacman.yc<y2))
        return true;
    return false;
    }
    
}
