package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, wPressed, aPressed, sPressed, dPressed, zPressed,xPressed,cPressed,vPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }

        if (code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }

        if (code == KeyEvent.VK_UP){
            upPressed = true;
        }

        if (code == KeyEvent.VK_DOWN){
            downPressed = true;
        }

        if (code == KeyEvent.VK_A){
            dPressed = true;
        }

        if (code == KeyEvent.VK_D){
            aPressed = true;
        }

        if (code == KeyEvent.VK_W){
            wPressed = true;
        }

        if (code == KeyEvent.VK_S){
            sPressed = true;
        }

        
    }
    @Override
    public void keyTyped(KeyEvent e){
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Z){
            GraphicsPanel.linearCombo[0]-=10.0;
        }

        if (code == KeyEvent.VK_X){
            GraphicsPanel.linearCombo[0]+=10.0;
        }

        if (code == KeyEvent.VK_C){
            GraphicsPanel.linearCombo[1]-=10.0;
        }

        if (code == KeyEvent.VK_V){
            GraphicsPanel.linearCombo[1]+=10.0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }

        if (code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }

        if (code == KeyEvent.VK_UP){
            upPressed = false;
        }

        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
        }

        if (code == KeyEvent.VK_A){
            dPressed = false;
        }

        if (code == KeyEvent.VK_D){
            aPressed = false;
        }

        if (code == KeyEvent.VK_W){
            wPressed = false;
        }

        if (code == KeyEvent.VK_S){
            sPressed = false;
        }/*

        if (code == KeyEvent.VK_Z){
            zPressed = false;
        }

        if (code == KeyEvent.VK_X){
            xPressed = false;
        }

        if (code == KeyEvent.VK_C){
            cPressed = false;
        }

        if (code == KeyEvent.VK_V){
            vPressed = false;
        }*/
    }
}
