
package org.yourorghere;

import javax.media.opengl.GL;


public class Packman {
     double xc, yc, radius, speed;
    String currentDirection = "Default" ;
    String storedDirection = "Default" ;
    private boolean mouth = true;

    Packman(double x, double y, double r, double s) {
        xc = x;
        yc = y;
        radius = r;
        speed = s;
    }
    void draw(GL gl) {
      int xc = (int) this.xc;
      int yc = (int) this.yc;
      gl.glColor3f(1, 1, 0);
      gl.glPointSize(15.0f);
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
    void drawMouth(GL gl){
        if(mouth){
        if(currentDirection.equals("right")){
        int xc = (int) this.xc;
        int yc = (int) this.yc;
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(0, 0, 0);
        gl.glVertex2i(xc+25, yc-15);
        gl.glVertex2i(xc+25, yc+15);
        gl.glVertex2i(xc, yc);
        gl.glEnd();
        gl.glFlush();}
        else if(currentDirection.equals("left")){
        int xc = (int) this.xc;
        int yc = (int) this.yc;
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(0, 0, 0);
        gl.glVertex2i(xc-29, yc-15);
        gl.glVertex2i(xc-29, yc+15);
        gl.glVertex2i(xc, yc);
        gl.glEnd();
        gl.glFlush();}
        else if(currentDirection.equals("up")){
        int xc = (int) this.xc;
        int yc = (int) this.yc;
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(0, 0, 0);
        gl.glVertex2i(xc+15, yc+29);
        gl.glVertex2i(xc-15, yc+29);
        gl.glVertex2i(xc, yc);
        gl.glEnd();
        gl.glFlush();}
        else if(currentDirection.equals("down")){
        int xc = (int) this.xc;
        int yc = (int) this.yc;
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(0, 0, 0);
        gl.glVertex2i(xc+15, yc-29);
        gl.glVertex2i(xc-15, yc-29);
        gl.glVertex2i(xc, yc);
        gl.glEnd();
        gl.glFlush();}
    }
    }
    void move(){
    int i = 0;
    if(storedDirection.equals("up")){
    for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitDown() ;i++);
    if(i==SimplePackman.numberOfWalls){
    currentDirection = "up";
    storedDirection = "default";}
    }
    if(storedDirection.equals("down")){
    for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitUp() ;i++);
    if(i==SimplePackman.numberOfWalls){
    currentDirection = "down";
    storedDirection = "default";}
    }
    if(storedDirection.equals("right")){
    for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitLeft() ;i++);
    if(i==SimplePackman.numberOfWalls){
    currentDirection = "right";
    storedDirection = "default";}
    }
    if(storedDirection.equals("left")){
    for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitRight() ;i++);
    if(i==SimplePackman.numberOfWalls){
    currentDirection = "left";
    storedDirection = "default";}
    }
    
    if(currentDirection.equals("right")){
        for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitLeft() ;i++);
        if(i==SimplePackman.numberOfWalls)
        xc+=speed;
        else
        storedDirection = "default";
    }
    else if(currentDirection.equals("left")){
        for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitRight() ;i++);
        if(i==SimplePackman.numberOfWalls)
        xc-=speed;
        else
        storedDirection = "default";
    }   
    else if(currentDirection.equals("up")){
        for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitDown() ;i++);
        if(i==SimplePackman.numberOfWalls)
        yc+=speed;
        else
        storedDirection = "default";
    }
    else if(currentDirection.equals("down")){
        for(i = 0 ; i<SimplePackman.numberOfWalls &&!SimplePackman.walls[i].hitUp() ;i++);
        if(i==SimplePackman.numberOfWalls)
        yc-=speed;
        else
        storedDirection = "default";
    }
    }
    void changeMouth(){
    if(mouth)
        mouth = false;
    else
        mouth = true;
    }
}
