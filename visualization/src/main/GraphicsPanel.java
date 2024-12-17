package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements Runnable {
    
    // settubgs
    public static final int SCREENWIDTH = 800;
    public static final int SCREENHEIGHT = 800;

    int FPS = 60;

    private static int horizTrans = 10;
    private static int vertTrans = 0;
    private static int horiz2Trans = 0;
    private static int vert2Trans = 10;

    public static double[] linearCombo = new double[2];

    
        Thread graphicsThread;
        KeyHandler keyH = new KeyHandler();
    
        public GraphicsPanel() {

            linearCombo[0] = 2.0;
            linearCombo[1] = 1.0;
    
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
                }
                
                if (timer >= 1000000000) {
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
                vertTrans++;
            }
            else if(keyH.downPressed == true){
                vertTrans--;
            }

            if(keyH.dPressed == true){
                horiz2Trans--;
            }
            else if(keyH.aPressed == true){
                horiz2Trans++;
            }

            if(keyH.wPressed == true){
                vert2Trans++;
            }
            else if(keyH.sPressed == true){
                vert2Trans--;
            }

            if(keyH.zPressed == true){
                linearCombo[0]-=10.0;
            }
            else if(keyH.xPressed == true){
                linearCombo[0]+=10.0;
            }

            if(keyH.cPressed == true){
                linearCombo[1]-=10.0;
            }
            else if(keyH.vPressed == true){
                linearCombo[1]+=10.0;
            }
        }
    
        public static void drawLines(Graphics2D window){
            window.setColor(Color.BLACK);
            window.drawLine(Point2D.worldToScreenX(0.0), Point2D.worldToScreenY(0.0), Point2D.worldToScreenX(horizTrans), Point2D.worldToScreenY(vertTrans));
            window.drawLine(Point2D.worldToScreenX(0.0), Point2D.worldToScreenY(0.0), Point2D.worldToScreenX(horiz2Trans), Point2D.worldToScreenY(vert2Trans));
        }

        public static double[][] matrixMaker(){
            double[][] matrix = new double[2][2];
            matrix[0][0] = horizTrans;
            matrix[1][0] = vertTrans;
            matrix[0][1] = horiz2Trans;
            matrix[1][1] = vert2Trans;
            return matrix;
        }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        drawLines(g2);

        g2.setColor(Color.BLACK);

        double[] newThingy = findEigen.findLambda(matrixMaker());
        
        g2.drawString("horizTrans " + ((double)horizTrans)/10.0, 50, 50);
        g2.drawString("vertTrans " + ((double)vertTrans)/10.0, 50, 70);
        g2.drawString("horiz2Trans " + ((double)horiz2Trans)/10.0, 50, 90);
        g2.drawString("vert2Trans " + ((double)vert2Trans)/10.0, 50, 110);
        g2.drawString("j",Point2D.worldToScreenX(horiz2Trans)+10, Point2D.worldToScreenY(vert2Trans)-10);
        g2.drawString("i",Point2D.worldToScreenX(horizTrans)+10, Point2D.worldToScreenY(vertTrans)-10);
        g2.drawString("Eigenvalues " + newThingy[0] + " " + newThingy[1],50,130);
        g2.drawString("Current transformation " + linearCombo[0] + " " + linearCombo[1],50,150);

        g2.setColor(Color.RED);
        g2.drawLine(Point2D.worldToScreenX(0),Point2D.worldToScreenY(0), Point2D.worldToScreenX(linearCombo[0]*((double) horizTrans) + linearCombo[1]*((double) horiz2Trans)), Point2D.worldToScreenY(linearCombo[0]*((double) vertTrans) + linearCombo[1]*((double) vert2Trans)));


        //g2.fillRect(100,100,400,400);

        g2.dispose();
    }

}
