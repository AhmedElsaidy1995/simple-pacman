package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * SimplePackman.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class SimplePackman implements GLEventListener , KeyListener {
    private Packman packman;
    private Dot dots[];
    private int numberOfDots ;
    static Wall walls[];
    static int numberOfWalls = 18 ;
    static Ghost ghosts[];
    static int numberOfGhosts = 3 ;
    static int screenHeight = 900;
    static int screenWidth = 705;
    static String status = "Active";

    public static void main(String[] args) {
        Frame frame = new Frame("Simple Pacman");
        final GLCanvas canvas = new GLCanvas();
        final SimplePackman handler = new SimplePackman();
        canvas.addGLEventListener(handler);
        canvas.addKeyListener(handler);
        frame.add(canvas);
        frame.setSize(screenHeight, screenWidth);
        frame.setResizable(false);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
         animator.start();
        canvas.requestFocus();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
            handler.timerFunction();
          }
        }, 0, 1000 / 30);
        timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
          handler.mouthChanger();
          }
        }, 0, 1000 / 5);
    }

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluOrtho2D(0.0,screenHeight,0.0,screenWidth);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void display(GLAutoDrawable drawable) {
        int i;
        for(i=0 ; i< numberOfDots &&dots[i].eated  ; i++);
        if(i==numberOfDots)
        status = "Won";
        for(i=0 ; i< numberOfGhosts &&!ghosts[i].hitPacman() ; i++);
        if(i!=numberOfGhosts)
        status = "Lost";
        if(status.equals("Active")){
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        for(i=0 ; i< numberOfDots ; i++){
        dots[i].isEated();
        if(!dots[i].eated)
        dots[i].draw(gl);
        }
        for(i=0 ; i< numberOfWalls ; i++){
        walls[i].draw(gl);
        }
        for(i=0 ; i< numberOfGhosts ; i++){
        ghosts[i].draw(gl);
        }
        packman.draw(gl);
        packman.drawMouth(gl);
        }
        if(status.equals("Won")){
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        packman.draw(gl);
        packman.drawMouth(gl);
        }
        if(status.equals("Lost")){
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        }
        
        /*gl.glEnd();
        gl.glFlush();*/
    }
     public void keyTyped(KeyEvent e) {
        if(status.equals("Active")){
        char key = e.getKeyChar();
        int i;
        if ( key == 'd'){ 
            for(i = 0 ; i<numberOfWalls &&!walls[i].hitLeft() ;i++);
            if(i==numberOfWalls)
            packman.currentDirection = "right";
            else
            packman.storedDirection = "right";
        }
        else if (key == 'a'){
            for(i = 0 ; i<numberOfWalls &&!walls[i].hitRight() ;i++);
            if(i==numberOfWalls)
            packman.currentDirection = "left";
            else
            packman.storedDirection = "left";
        }
         else if (key == 'w'){
            for(i = 0 ; i<numberOfWalls &&!walls[i].hitDown() ;i++);
            if(i==numberOfWalls)
            packman.currentDirection = "up";
            else
            packman.storedDirection = "up";
            System.out.println(i);
         } 
         else if (key == 's'){
            for(i = 0 ; i<numberOfWalls &&!walls[i].hitUp() ;i++);
            if(i==numberOfWalls)
            packman.currentDirection = "down";
            else
            packman.storedDirection = "down";
         }
    }
    }
    void timerFunction(){
        packman.move();
        for(int i=0 ; i< numberOfGhosts ; i++){
        ghosts[i].move();
        }
    }
    void mouthChanger(){
        packman.changeMouth();
    }
    SimplePackman(){
    packman = new Packman(70,55,15,5);
    numberOfDots=0;
    dots = new Dot[200];
    int i =0 ;
    
    for(int y=140;y<700;y+=85){
        for(int j=60;j<870;j+=30 , i++){
        dots[i] = new Dot(j,y,3,packman);
        numberOfDots++;
        }
    }
    
    walls = new Wall[numberOfWalls];
    walls[0] = new Wall(40,860,5,20,packman);
    walls[1] = new Wall(20,35,5,700,packman);
    walls[2] = new Wall(865,880,5,700,packman);
    walls[3] = new Wall(40,600,90,105,packman);
    walls[4] = new Wall(670,860,90,105,packman);
    walls[5] = new Wall(40,600,175,190,packman);
    walls[6] = new Wall(670,860,175,190,packman);
    walls[7] = new Wall(40,500,260,275,packman);
    walls[8] = new Wall(570,860,260,275,packman);
    walls[9] = new Wall(40,250,345,360,packman);
    walls[10] = new Wall(320,795,345,360,packman);
    walls[11] = new Wall(40,250,430,445,packman);
    walls[12] = new Wall(320,795,430,445,packman);
    walls[13] = new Wall(105,420,515,530,packman);
    walls[14] = new Wall(490,795,515,530,packman);
    walls[15] = new Wall(105,420,600,615,packman);
    walls[16] = new Wall(490,860,600,615,packman);
    walls[17] = new Wall(40,860,685,700,packman);
    
    ghosts = new Ghost[numberOfGhosts];
    ghosts[0] = new Ghost(50,455,50,700,5,"x",packman);
    ghosts[1] = new Ghost(50,625,50,700,5,"x",packman);
    ghosts[2] = new Ghost(805,540,285,590,5,"y",packman);

    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

