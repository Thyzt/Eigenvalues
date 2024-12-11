package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements Runnable{
    
    // settubgs
    final int SCREENWIDTH = 1000;
    final int SCREENHEIGHT = 1000;

    int FPS = 60;

    Thread graphicsThread;
    KeyHandler keyH = new KeyHandler();

    public GraphicsPanel() {

        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGraphicsThread()  {
        
        graphicsThread = new Thread(this);
        graphicsThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(graphicsThread != null){
            
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1){
                //1 updates information
                update();

                //2 draws this updated information
                repaint();
                
                delta--;

                drawCount++;
            }
            
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {

        if(keyH.rightPressed == true){
            
        }
        else if(keyH.leftPressed == true){
            
        }
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);

        g2.fillRect(100,100,400,400);

        g2.dispose();
    }

}
