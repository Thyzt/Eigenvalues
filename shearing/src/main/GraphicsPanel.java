package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements Runnable {
    
    // settubgs
    final int SCREENWIDTH = 1000;
    final int SCREENHEIGHT = 1000;

    int FPS = 10;

    static int horizTrans = 0;
    static int vertTrans = 0;

    static BufferedImage transImage;
    
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
                    //System.out.println("FPS:" + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }
        
        public void update() {
    
            if(keyH.rightPressed == true){
                horizTrans++;
            }
            else if(keyH.leftPressed == true){
                horizTrans--;
            }

            if(keyH.upPressed == true){
                vertTrans--;
            }
            else if(keyH.downPressed == true){
                vertTrans++;
            }
        }
    
        public static void drawImage(Graphics2D window) throws IOException{
            transImage = imageCo.readImage();
            int[][] newImage = imageCo.pngToMatrix(transImage);
            for (int i=0;i<newImage.length;i++){
                for (int j=0;j<newImage[0].length;j++){
                    //System.out.println(newImage[i][j]);
                    window.setColor(new Color (newImage[i][j]));
                    window.drawRect
                    (i + (j-transImage.getWidth()/2)*horizTrans+400,
                    j + (i-transImage.getHeight()/2)*vertTrans+400,
                    1, 1);
                }
            }
        }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        

        try {
            drawImage(g2);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        g2.setColor(Color.BLACK);
        
        g2.drawString("horizTrans " + horizTrans, 50, 50);
        g2.drawString("vertTrans " + -1 * vertTrans, 50, 70);

        //g2.fillRect(100,100,400,400);

        g2.dispose();
    }

}
