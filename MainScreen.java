import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * Images for crushing effect: https://www.freepik.com/premium-vector/animated-smoke-explosion-cloud-game-sprite_30653607.htm
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainScreen extends World
{
    private Fruit[][] board;
    private int rows, cols;
    private static final int CELL_SIZE = 65;
    private int x, y;
    private boolean run;
    Timer timer;
    Label scoreLabel;
    int score = 0;
    public MainScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 
        rows = 10;
        cols = 10;
        
        addObject(new Board(rows,cols,CELL_SIZE), 665,360);
        
        board = new Fruit[rows][cols];
        
        run = false;
        
        timer = new Timer();
        scoreLabel = new Label(score, 80);
        scoreLabel.setFillColor(Color.BLACK);
        addObject(timer, 170, 150);
        addObject(scoreLabel, 170, 350);
        setUp();
    }
    
    public void act(){
        horizontalCrush();
        verticalCrush();
        dropFruits();
        scoreLabel.setValue(score);
    }
    
    /**
     * Checks for horizontal matches of three Fruits and removes them.
     */
    private void horizontalCrush(){
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-2;j++){
                if(board[i][j] != null && board[i][j + 1] != null && board[i][j + 2] != null){
                    if(board[i][j].getClass() == board[i][j + 1].getClass() && board[i][j].getClass() == board[i][j + 2].getClass()){
                        removeObject(board[i][j]);
                        removeObject(board[i][j+1]);
                        removeObject(board[i][j+2]);
                        board[i][j] =  board[i][j+1] =  board[i][j+2] = null;
                        score += 3;
                    }
                }
            }
        }
    }
    
    /**
     * Checks for vertical matches of three Fruits and removes them.
     */
    private void verticalCrush(){
        for(int i=0; i<rows-2;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j] != null && board[i + 1][j] != null && board[i + 2][j] != null){
                    if(board[i][j].getClass().equals(board[i + 1][j].getClass()) && board[i][j].getClass().equals(board[i + 2][j].getClass())){
                        removeObject(board[i][j]);
                        removeObject(board[i+1][j]);
                        removeObject(board[i+2][j]);
                        board[i][j] = board[i+1][j] = board[i+2][j] = null;
                        score += 3;
                    }
                }
            }
        }
    }
    
    /**
     * Drops the Fruits to fill empty spaces below them and refills the board with new Fruits at the top.
     */
    private void dropFruits() {
        for (int j = 0; j < cols; j++) {
            // Start from the bottom of the column and look for empty spaces
            for (int i = rows - 1; i >= 0; i--) {
                if (board[i][j] == null) {
                    // Find the first non-empty tile above the current empty space
                    for (int k = i - 1; k >= 0; k--) {
                        if (board[k][j] != null) {
                            //Move the tile down to the empty space
                            board[i][j] = board[k][j];
                            board[k][j] = null;
                            Greenfoot.delay(1);
                            board[i][j].setLocation(x+j*65, y+i*65);
                            break;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < cols; j++) {
            for (int i = rows - 1; i >= 0; i--) {
                if (board[i][j] == null) {
                    board[i][j] = getRandomFruit();
                    Greenfoot.delay(1);
                    addObject(board[i][j], x+j*65, y+i*65);
                }
            }
        }
    }

    /**
     * Creates a random Fruit.
     * @return Fruit A new Fruit of random type.
     */
    private Fruit getRandomFruit(){
        int chance = Greenfoot.getRandomNumber(5);
        switch(chance){
            case 0: return new Strawberry();
            case 1: return new Blueberry();
            case 2: return new Pear();
            case 3: return new Peach();
            case 4: return new Pineapple();
        }
        return new Strawberry();
    }
    
    /**
     * Initializes the board with Fruits.
     */
    private void setUp(){
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
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                board[i][j]=getRandomFruit();
                addObject(board[i][j],x+j*65,y+i*65);
            }
        }
    }
}
