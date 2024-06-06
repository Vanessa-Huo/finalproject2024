import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends Actor
{
    /**
     * Act - do whatever the Grid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage image;
    //private int boardColumns, boardRows;
    private int width;
    private int height;
    private int cellSize = 50;
    
    public void act()
    {
        // Add your action code here.
        
    }
    
    public Board(int rows, int cols, int cellSize) {
        width = cols * cellSize;
        height = rows * cellSize;
        
        GreenfootImage gridImage = new GreenfootImage(width+1, height+1);
        
        gridImage.setColor(Color.LIGHT_GRAY);
        gridImage.fillRect(0,0,width,height);
        
        gridImage.setColor(Color.GRAY);
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
