package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) throws Exception {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Eigenvalues");

        GraphicsPanel graphicsPanel = new GraphicsPanel();
        window.add(graphicsPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        graphicsPanel.startGraphicsThread();
    }
}