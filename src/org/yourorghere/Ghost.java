
package org.yourorghere;

import javax.media.opengl.GL;


public class Ghost {
    double x1,y1 , x2 , y2 , range1 , range2 ,speed;
    String movement;
    String currentDirection ="Default";
    Packman pacman;

    Ghost(double a, double b, double r1 , double r2 ,double s ,String move , Packman p){
        x1 = a;
	y1 = b;
	x2 = x1+50;
	y2 = y1+50;
        range1 = r1;
        range2 = r2;
        movement=move;
        speed = s;
        pacman = p;
        if(movement.equals("x"))
           currentDirection = "right";
        else if(movement.equals("y"))
           currentDirection = "bottom";        
    }
    void draw(GL gl){
    gl.glColor3f(1, 0, 0);
    gl.glPointSize(3.0F);
    gl.glBegin(GL.GL_POLYGON);
	gl.glVertex2d(x1, y1);
	gl.glVertex2d(x2, y1);
	gl.glVertex2d(x2, y2);
	gl.glVertex2d(x1, y2);
    gl.glEnd();
    gl.glFlush();
    drawTails(gl);
    }
    void drawTails(GL gl){
    gl.glColor3f(0, 0, 0);
    gl.glPointSize(3.0F);
    gl.glBegin(GL.GL_POLYGON);
	gl.glVertex2d(x1+10, y1);
	gl.glVertex2d(x2-30, y1);
	gl.glVertex2d(x2-30, y1+10);
	gl.glVertex2d(x1+10, y1+10);
    gl.glEnd();
    gl.glFlush();
    gl.glBegin(GL.GL_POLYGON);
	gl.glVertex2d(x1+30, y1);
	gl.glVertex2d(x2-10, y1);
	gl.glVertex2d(x2-10, y1+10);
	gl.glVertex2d(x1+30, y1+10);
    gl.glEnd();
    gl.glFlush();
    }
    void move(){
    if(movement.equals("x")){
        if(x2==range2)
        currentDirection = "left";
        else if(x1==range1)
        currentDirection = "right";         
    }
    if(movement.equals("y")){
        if(y2==range2)
        currentDirection = "bottom";
        else if(y1==range1)
        currentDirection = "up";         
    }
    if(currentDirection.equals("right") &&x2<=range2){
        x1+=speed;
        x2+=speed;
    }
    if(currentDirection.equals("left") && x1>=range1){
        x1-=speed;
        x2-=speed;
    }
    if(currentDirection.equals("up") &&y2<=range2){
        y1+=speed;
        y2+=speed;
    }
    if(currentDirection.equals("bottom") && y1>=range1){
        y1-=speed;
        y2-=speed;
    }
    }
    boolean hitPacman(){
    if((x2-5 <= pacman.xc - pacman.radius - 10 &&x2 >= pacman.xc - pacman.radius - 10 && y2>=pacman.yc+25&&y1<=pacman.yc-25)//right
     ||(x1+5 >= pacman.xc + pacman.radius + 10 &&x1 <= pacman.xc + pacman.radius + 10 && y2>=pacman.yc+25&&y1<=pacman.yc-25)//left
     ||(y2-5 <= pacman.yc - pacman.radius - 10 &&y2 >= pacman.yc - pacman.radius - 10 && x2>=pacman.xc+25&&x1<=pacman.xc-25)//up
     ||(y1+5 >= pacman.yc + pacman.radius + 10 &&y1 <= pacman.yc + pacman.radius + 10 && x2>=pacman.xc+25&&x1<=pacman.xc-25))//bottom
        return true;
    return false;    
    }
}
