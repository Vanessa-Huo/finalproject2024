import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
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
    
    public Board(int rows, int cols, int cellSize) {
        width = cols * cellSize; //Total width of the board
        height = rows * cellSize; //Total height of the board
        
        GreenfootImage gridImage = new GreenfootImage(width+1, height+1);
        
        //Set color and fill the board with color
        gridImage.setColor(OFF_WHITE);
        gridImage.fillRect(0,0,width,height);
        
        //Set color and draw the vertical lines
        gridImage.setColor(Color.LIGHT_GRAY);
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