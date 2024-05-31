import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainScreen extends World
{
    private int[][] board;
    private int rows, cols;
    private static final int CELL_SIZE = 65;
    private int x, y;
    public MainScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 
        rows = 10;
        cols = 10;
        
        addObject(new Board(rows,cols,CELL_SIZE), 665,360);
        
        board = new int[rows][cols];
        
        setUp();
    }
    
    public void act(){
        
    }
    
    public void setUp(){
        for(int i=0; i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=0;
            }
        }
        if(cols%2==0){
            x = 665-(cols/2*CELL_SIZE)+(CELL_SIZE/2);
        }else{
            x = 665-(cols/2*CELL_SIZE);
        }
        if(rows%2==0){
            y = 360-(rows/2*CELL_SIZE)+(CELL_SIZE/2);
        }else{
            y = 360-(rows/2*CELL_SIZE);
        }
        for(int i=0; i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==0){
                    int chance = Greenfoot.getRandomNumber(5);
                    switch(chance){
                        case 0:
                            addObject(new RedTile(),x+j*65,y+i*65);
                            break;
                        case 1:
                            addObject(new BlueTile(),x+j*65,y+i*65);
                            break;
                        case 2:
                            addObject(new GreenTile(),x+j*65,y+i*65);
                            break;
                        case 3:
                            addObject(new PinkTile(),x+j*65,y+i*65);
                            break;
                        case 4:
                            addObject(new YellowTile(),x+j*65,y+i*65);
                            break;
                    }
                }
            }
        }
    }
}
