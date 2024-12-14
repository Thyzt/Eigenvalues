package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageCo {

    static BufferedImage image = null;

    public static BufferedImage readImage() {
        File input_file = new File("eigenvalues/src/main/fort_sumter2.png");/*
        if (input_file.exists()) {
            System.out.println("File found: " + input_file.getAbsolutePath());
        } else {
            System.out.println("File not found.");
        }*/
        image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        
        try {
            image = ImageIO.read(input_file);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return image;
    }

    public static int[][] pngToMatrix(BufferedImage bi){
        
        int[][] C = new int[bi.getHeight()][bi.getWidth()];
        
        for(int i=0;i<bi.getHeight();i++) {
            for(int j=0;j<bi.getWidth();j++){
                C[i][j] = bi.getRGB(i,j);
            }
        }
        return C;
    }
}