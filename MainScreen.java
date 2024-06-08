import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * game desc...
 * 
 * @author Vanessa Huo, Megan Lee
 * @version June 2024
 */
public class MainScreen extends World
{
    private Fruit[][] board;
    private int rows, cols;
    private static final int CELL_SIZE = 65;
    private int x, y;
    private boolean run;
    private HomeButton home;

    public MainScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 

        setBackground("mainScreen.png");
        home = new HomeButton();
        addObject(home, 100, getHeight() - 50);

        rows = 10;
        cols = 10;

        addObject(new Board(rows,cols,CELL_SIZE), 665,360);

        board = new Fruit[rows][cols];

        run = false;

        drawBoard(true);
    }

    public void act(){
        horizontalCrush(true);
        verticalCrush(true);
        dropFruits();
        if(Greenfoot.mouseClicked(home)) {
            TitleScreen title = new TitleScreen();
            Greenfoot.setWorld(title);
        }
    }

    /**
     * Checks for horizontal matches of three Fruits and removes them.
     * 
     * @param removeCrushes   Remove found crushes (true) or not (false)
     * @return boolean  Horizontal crush was found (true) or not (false)
     */
    private boolean horizontalCrush(boolean removeCrushes){
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-2;j++){
                if(board[i][j] != null && board[i][j + 1] != null && board[i][j + 2] != null){
                    if(board[i][j].getClass() == board[i][j + 1].getClass() && board[i][j].getClass() == board[i][j + 2].getClass()){
                        if(removeCrushes){
                            removeObject(board[i][j]);
                            removeObject(board[i][j+1]);
                            removeObject(board[i][j+2]);
                            board[i][j] =  board[i][j+1] =  board[i][j+2] = null;
                        }
                        crushFound = true;
                    }
                }
            }
        }
        return crushFound;
    }

    /**
     * Checks for vertical matches of three Fruits and can remove them.
     * 
     * @param removeCrushes   Remove found crushes (true) or not (false)
     * @return boolean        Vertical crush was found (true) or not (false)
     */
    private boolean verticalCrush(boolean removeCrushes){
        boolean crushFound = false;
        for(int i=0; i<rows-2;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j] != null && board[i + 1][j] != null && board[i + 2][j] != null){
                    if(board[i][j].getClass().equals(board[i + 1][j].getClass()) && board[i][j].getClass().equals(board[i + 2][j].getClass())){
                        if(removeCrushes){
                            removeObject(board[i][j]);
                            removeObject(board[i+1][j]);
                            removeObject(board[i+2][j]);
                            board[i][j] = board[i+1][j] = board[i+2][j] = null;
                        }
                        crushFound = true;
                    }
                }
            }
        }
        return crushFound;
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
     * If hasn't been set up already, initialize fruits. 
     * 
     * @param isNew   Initial set up or not
     */
    private void drawBoard(boolean isNew){
        if(!isNew) removeObjects(getObjects(Fruit.class));
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
                if(isNew) board[i][j]=getRandomFruit();
                addObject(board[i][j],x+j*65,y+i*65);
            }
        }
    }

    /**
     * Returns width of tile
     * 
     * @return  Width of tile
     */
    public int getTileWidth(){
        return CELL_SIZE;
    }

    /**
     * Returns height of tile
     * 
     * @return  Height of tile
     */
    public int getTileHeight(){
        return CELL_SIZE;
    }

    /**
     * Removes all current selection boxes from world.
     *@author Megan
     */
    public void resetSelection(){
        removeObjects(getObjects(Selection.class));
    }

    /**
     * Locates and returns outer or inner index of a fruit within 2D array.
     * 
     * @param fruit         Fruit to determine index of 
     * @param outerIndex    To return the outer index (true) or not (false)
     * @return int          Outer/Inner index of fruit
     */
    public int getIndex(Fruit fruit, boolean outerIndex){
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j] == fruit){
                    if(outerIndex){
                        return i;
                    }
                    else{
                        return j;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Swaps positions of two fruits within the 2D array.
     * Refreshes board to display changes.
     * 
     * @param outerIndex1   Outer index of first fruit
     * @param innerIndex1   Inner index of first fruit
     * @param outerIndex2   Outer index of second fruit
     * @param outerIndex2   Inner index of second fruit
     * 
     */
    public void swapFruits(int outerIndex1, int innerIndex1, int outerIndex2, int innerIndex2){
        Fruit temp = board[outerIndex1][innerIndex1];
        board[outerIndex1][innerIndex1] = board[outerIndex2][innerIndex2];
        board[outerIndex2][innerIndex2] = temp;

        //if switch made a crush possible
        if(horizontalCrush(false) || verticalCrush(false)){
            resetSelection();
            drawBoard(false);
        }
        else{ //if switch cannot form a crush
            board[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1];            
            board[outerIndex1][innerIndex1] = temp;
        }
    }
}
