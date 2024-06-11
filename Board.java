import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
/**
 * Grid to visually organize the fruits into a game board. 
 * 
 * @author Vanessa Huo
 * @version June 2024
 */
public class Board extends Actor
{
    public static final Color OFF_WHITE = new Color (242, 241, 233, 255);
    private GreenfootImage image;
    private int width, height;
    
    private int cellSize = 50;
    
    
    public Board(int rows, int cols, int cellSize) {
        width = cols * cellSize;
        height = rows * cellSize;
        
        
        GreenfootImage gridImage = new GreenfootImage(width+1, height+1);
        
        gridImage.setColor(OFF_WHITE);
        gridImage.fillRect(0,0,width,height);
        
        gridImage.setColor(Color.LIGHT_GRAY);
        // Draw the vertical lines
        for (int i = 0; i <= cols; i++) {
            int x = i * cellSize;
            gridImage.drawLine(x, 0, x, height);
        }

        // Draw the horizontal lines
        for (int i = 0; i <= rows; i++) {
            int y = i * cellSize;
            gridImage.drawLine(0, y, width, y);
        }
        setImage(gridImage);
    }
}