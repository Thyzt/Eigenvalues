package main;

public class Point2D 
{

    private static final int WINDOWWIDTH = GraphicsPanel.SCREENWIDTH;
    private static final int WINDOWHEIGHT = GraphicsPanel.SCREENHEIGHT;
    private static final double WORLD_MIN_X = -100.0;
    private static final double WORLD_MAX_X = 100.0;
    private static final double WORLD_MIN_Y = -100.0;
    private static final double WORLD_MAX_Y = 100.0;

    /*public static void main(String[] args) {
        System.out.println(screenToWorldX(600) + ", " + screenToWorldY(800));
        System.out.println(worldToScreenX(5.0) + ", " + worldToScreenY(5.0));
    }*/


    // convert x values: apply scaling (optional zoom) and translations
    public static int worldToScreenX(double worldX) {
        return (int) ((worldX - WORLD_MIN_X) / (WORLD_MAX_X - WORLD_MIN_X) * WINDOWWIDTH);
    }

    // convert in y direction: apply scaling (optional zoom), translation, and inversion
    public static int worldToScreenY(double worldY) {
        return (int) (WINDOWHEIGHT - ((worldY - WORLD_MIN_Y) / (WORLD_MAX_Y - WORLD_MIN_Y) * WINDOWHEIGHT));
    }
    
    public static double screenToWorldX(int screenX) {
        return WORLD_MIN_X + (screenX / (double) WINDOWWIDTH) * (WORLD_MAX_X - WORLD_MIN_X);
    }

    public static double screenToWorldY(int screenY) {
        return WORLD_MIN_Y + ((WINDOWHEIGHT - screenY) / (double) WINDOWHEIGHT) * (WORLD_MAX_Y - WORLD_MIN_Y);
    }
    
}
