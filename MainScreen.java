import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * game desc...
 * 
 * @author Vanessa Huo, Megan Lee
 * @version June 2024
 */
public class MainScreen extends World
{
    private static Fruit[][] board;
    private int rows, cols;
    private static final int CELL_SIZE = 65;
    private int x, y;
    private boolean run;

    Timer timer;
    Label scoreLabel;

    private HomeButton home;
    private int score; 

    public MainScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 

        setBackground("plainBG.png");
        home = new HomeButton();
        addObject(home, 100, getHeight() - 50);

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

        drawBoard(true);
        text();
        //addObject(scoreBar, 100,330);
    }

    public void act(){
        dropFruits();

        scoreLabel.setValue(score);

        if(Greenfoot.mouseClicked(home)) {
            TitleScreen title = new TitleScreen();
            Greenfoot.setWorld(title);
        }
        crushFive(true);
        crushFour(true);
        crushThree(true);
        dropFruits();
    }

    public void text(){
        addObject(new Label("Time",50),100,80);
        addObject(new Label("Score",50),100,280);
        addObject(new Label("Booster",50),100,480);
    }

    /**
     * Checks for horizontal and vertical matches of three Fruits and removes them.
     * 
     * @param removeCrushes   Remove found crushes (true) or not (false)
     * @return boolean  crush was found (true) or not (false)
     */
    private boolean crushThree(boolean removeCrushes) {
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-2;j++){
                int length = getMatchLength(i, j, 0, 1);
                if (length == 3) {
                    if(removeCrushes){
                        removeCrush(i, j, 0, 1, length);
                        j += length - 1; // Skip the already checked candies
                    }
                    crushFound = true;
                }
            }
        }
        for(int i=0; i<rows-2;i++){
            for(int j=0;j<cols;j++){
                int length = getMatchLength(i, j, 1, 0);
                if (length == 3) {
                    if(removeCrushes){
                        removeCrush(i, j, 1, 0, length);
                        i += length - 1; // Skip the already checked candies
                    }
                    crushFound = true;
                }
            }
        }
        return crushFound;
    }

    /**
     * Checks for horizontal and vertical matches of four Fruits and removes them.
     * 
     * @param removeCrushes   Remove found crushes (true) or not (false)
     * @return boolean  crush was found (true) or not (false)
     */
    private boolean crushFour(boolean removeCrushes){
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-3;j++){
                int length = getMatchLength(i, j, 0, 1);
                if (length == 4) {
                    if(removeCrushes){
                        removeCrush(i, j, 0, 1, length);
                        j += length - 1; // Skip the already checked candies
                    }
                    crushFound = true;
                }
            }
        }
        for(int i=0; i<rows-3;i++){
            for(int j=0;j<cols;j++){
                int length = getMatchLength(i, j, 1, 0);
                if (length == 4) {
                    if(removeCrushes){
                        removeCrush(i, j, 1, 0, length);
                        i += length - 1; // Skip the already checked candies
                    }
                    crushFound = true;
                }
            }
        }
        return crushFound;
    }

    /**
     * Checks for matches of five or more Fruits and removes them.
     * 
     * @param removeCrushes   Remove found crushes (true) or not (false)
     * @return boolean  crush was found (true) or not (false)
     */
    private boolean crushFive(boolean removeCrushes){
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-4;j++){
                int length = getMatchLength(i, j, 0, 1);
                if (length >= 5) {
                    if(removeCrushes){
                        removeCrush(i, j, 0, 1, length);
                        j += length - 1; // Skip the already checked candies
                    }
                    crushFound = true;
                }
            }
        }
        for(int i=0; i<rows-4;i++){
            for(int j=0;j<cols;j++){
                int length = getMatchLength(i, j, 1, 0);
                if (length >= 5) {
                    if(removeCrushes){
                        removeCrush(i, j, 1, 0, length);
                        i += length - 1; // Skip the already checked candies
                    }
                    crushFound = true;
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
     * A method that gives the length of a match starting from position (i, j) in the given direction (di, dj).
     * 
     * @param i     The row index to start the match check
     * @param j     The column index to start the match check
     * @param di    The row direction. 0 for horizontal check and 1 for vertical check
     * @param dj    The col direction. 1 for horizontal check and 0 for vertical check
     * @return      The length of the match (three, four, five or more)
     */
    private int getMatchLength(int i, int j, int di, int dj) {
        int length = 1;
        while (i + length * di < rows && j + length * dj < cols && board[i][j] != null && board[i + length * di][j + length * dj]!= null 
        && board[i][j].getClass().equals(board[i + length * di][j + length * dj].getClass())){
            length++;
        }
        return length;
    }

    /**
     * Removes the match starting from the specified position (i, j) in the given direction (di, dj).
     * 
     * @param i     The row index to start removing
     * @param j     The column index to start removing
     * @param di    The row direction. 0 for horizontal check and 1 for vertical check
     * @param dj    The col direction. 1 for horizontal check and 0 for vertical check
     * @param length    The number of fruits to remove (length of the match)
     */
    private void removeCrush(int i, int j, int di, int dj, int length) {
        for (int k = 0; k < length; k++) {
            removeObject(board[i + k * di][j + k * dj]);
            board[i + k * di][j + k * dj] = null;
        }
        score += length;
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
        if(crushFour(false) || crushThree(false) || crushFive(false)){
            resetSelection();
            drawBoard(false);
        }
        else{ //if switch cannot form a crush
            board[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1];            
            board[outerIndex1][innerIndex1] = temp;
        }
    }
}
