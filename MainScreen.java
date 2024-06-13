import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * game desc...
 * 
 * @author Vanessa Huo, Megan Lee, Luke Xiao
 * @version June 2024
 */
public class MainScreen extends World
{
    private static Fruit[][] board;
    private int rows, cols;
    private static final int CELL_SIZE = 65;
    private int booster1, booster2;
    private int x, y;
    
    private boolean run;
    private int score;

    Timer timer;
    Label scoreLabel;
    Watermelon melon;
    Paintbrush brush;

    //int score = 0;

    private HomeButton home;
    private TutorialButton tut;
    private GreenfootImage[] explode = new GreenfootImage[3];
    
    private int animCounter, animDelay, animIndex, maxIndex;
    private enum GameState { CHECK_MATCHES, REMOVE_MATCHES, PLAY_EXPLOSION, FILL_SPACES }
    private GameState state;
    
    public MainScreen()
    {    
        super(1024, 720, 1); 

        setBackground("mainScreen.png");
        home = new HomeButton();
        addObject(home, 100, getHeight() - 50);
        tut = new TutorialButton();
        addObject(tut, 250, getHeight() - 50);
        melon = new Watermelon(true,false);
        brush = new Paintbrush(false);

        rows = 10;
        cols = 10;

        addObject(new Board(rows,cols,CELL_SIZE), 665,360);

        board = new Fruit[rows][cols];

        run = false;

        timer = new Timer();
        scoreLabel = new Label(score, 80);
        scoreLabel.setFillColor(Color.BLACK);
        addObject(timer, 175, 175);
        addObject(scoreLabel, 175, 345);
       
        drawBoard(true);
        
        animCounter = 0;
        maxIndex = explode.length;
        //Greenfoot.setSpeed(70); // Set the speed to 70 out of 100

        state = GameState.CHECK_MATCHES;
    }
    
    /**
     * Called when world is ran, resets status of Selection
     */
    public void started(){
        Selection.setSelecting(false);
    }

    public void act(){
        //Setup
        while(run==false && crushThree(true)){
            crushFive(true);
            crushFour(true);
            crushThree(true);
            dropFruits();
            score = 0;
        }
        //Play
        run = true;
        scoreLabel.setValue(score);
        switch (state) {
            case CHECK_MATCHES:
                if (crushFive(true) || crushFour(true) || crushThree(true) || watermelonBomb()) {
                    state = GameState.REMOVE_MATCHES;
                } else {
                    dropFruits();
                }
                break;
            case REMOVE_MATCHES:
                triggerExplosions();
                state = GameState.PLAY_EXPLOSION;
                break;
            case PLAY_EXPLOSION:
                if (getObjects(Explosion.class).isEmpty()) {
                    state = GameState.FILL_SPACES;
                }
                break;
            case FILL_SPACES:
                dropFruits();
                state = GameState.CHECK_MATCHES;
                break;
        }
        if(getObjects(Selection.class).size() == 0){
            //System.out.println("none detected");
            Selection.setSelecting(false);
        }
    }

    /**
     * Checks for horizontal and vertical matches of three Fruits and removes them.
     * 
     * @param removeCrushes   Remove found crushes (true) or not (false)
     * @return boolean  Crush was found (true) or not (false)
     */
    private boolean crushThree(boolean removeCrushes) {
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-2;j++){
                if (getMatchLength(i, j, 0, 1) == 3) {
                    if(removeCrushes){
                        removeCrush(i, j, 0, 1, 3);
                        //i += length - 1;
                    }
                    crushFound = true;
                }
            }
        }
        for(int i=0; i<rows-2;i++){
            for(int j=0;j<cols;j++){
                if (getMatchLength(i, j, 1, 0) == 3) {
                    if(removeCrushes){
                        removeCrush(i, j, 1, 0, 3);
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
     * @return boolean  Crush was found (true) or not (false)
     */
    private boolean crushFour(boolean removeCrushes){
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-3;j++){
                if (getMatchLength(i, j, 0, 1) == 4) {
                    if(removeCrushes){
                        Fruit test = getSpecialFruit(board[i][j]);
                        removeCrush(i, j, 0, 1, 4);
                        board[i][j]=test;
                        addObject(board[i][j], x+j*65, y+i*65);
                    }
                    crushFound = true;
                }
            }
        }
        for(int i=0; i<rows-3;i++){
            for(int j=0;j<cols;j++){
                if (getMatchLength(i, j, 1, 0) == 4) {
                    if(removeCrushes){
                        Fruit test = getSpecialFruit(board[i][j]);
                        removeCrush(i, j, 1, 0, 4);
                        board[i][j]=test;
                        addObject(board[i][j], x+j*65, y+i*65);
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
     * @return boolean  Crush was found (true) or not (false)
     */
    private boolean crushFive(boolean removeCrushes){
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols-4;j++){
                int length = getMatchLength(i, j, 0, 1);
                if (length >= 5) {
                    if(removeCrushes){
                        Fruit temp = getBombFruit(board[i][j]);
                        removeCrush(i, j, 0, 1, length);
                        board[i][j]=temp;
                        addObject(board[i][j], x+j*65, y+i*65);
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
                        Fruit temp = getBombFruit(board[i][j]);
                        removeCrush(i, j, 1, 0, length);
                        board[i][j]=temp;
                        addObject(board[i][j], x+j*65, y+i*65);
                    }
                    crushFound = true;
                }
            }
        }
        return crushFound;
    }
    
    private boolean watermelonBomb(){
        boolean crushFound = false;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if(board[i][j] instanceof Watermelon){
                    for(int x = i-1;x<=i+1;x++){
                        for(int y = j-1;y<=j+1;y++){
                            if(x>=0 && x<rows && y>=0 && y<cols && board[x][y]!=null){
                                removeObject(board[x][y]);
                                board[x][y]=null;
                                score++;
                            }
                        }
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
            for (int i = rows - 1; i >= 0; i--) {
                if (board[i][j] == null) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (board[k][j] != null) {
                            board[i][j] = board[k][j];
                            board[k][j] = null;
                            delay(1);
                            board[i][j].setLocation(x+j*65, y+i*65);
                            delay(1);
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
                    delay(1);
                    addObject(board[i][j], x+j*65, y+i*65);
                }
            }
        }
    }
    
    private void delay(int x){
        if(run){
            Greenfoot.delay(x);
        }
    }

    /**
     * Creates a random Fruit.
     * @return Fruit A new Fruit of random type.
     */
    private Fruit getRandomFruit(){
        switch(Greenfoot.getRandomNumber(5)){
            case 0: return new Blueberry();
            case 1: return new Peach();
            case 2: return new Pear();
            case 3: return new Pineapple();
            case 4: return new Strawberry();
            default: return new Blueberry();
        }
    }

    /**
     * Creates a special fruit that has the same type as given fruit.
     * Given Peach will return a SpecialPeach
     * 
     * @param fruit     Given fruit
     * @return Fruit A new SpecialFruit.
     */
    private Fruit getSpecialFruit(Fruit x){
        if(x instanceof Blueberry){
            return new SBlueberry();
        }else if(x instanceof Peach){
            return new SPeach();
        }else if(x instanceof Pear){
            return new SPear();
        }else if(x instanceof Pineapple){
            return new SPineapple();
        }else if(x instanceof Strawberry){
            return new SStrawberry();
        }
        return new SBlueberry();
    }
    
    /**
     * Creates a bomb fruit that has the same type as given fruit.
     * 
     * @param fruit     Given fruit
     * @return Fruit A new BombFruit.
     */
    private Fruit getBombFruit(Fruit y){
        if(y instanceof Blueberry){
            return new BBlueberry();
        }else if(y instanceof Peach){
            return new BPeach();
        }else if(y instanceof Pear){
            return new BPear();
        }else if(y instanceof Pineapple){
            return new BPineapple();
        }else if(y instanceof Strawberry){
            return new BStrawberry();
        }
        return new BBlueberry();
    }

    /**
     * A method that gives the length of a match starting from position (i, j) in the given direction (di, dj).
     * 
     * @param i     The row index to start the match check
     * @param j     The column index to start the match check
     * @param di    The row direction. 0 for horizontal check and 1 for vertical check
     * @param dj    The col direction. 1 for horizontal check and 0 for vertical check
     * @return      The length of the match (three, four, five)
     */
    private int getMatchLength(int i, int j, int di, int dj) {
        int length = 1;
        while (i + length * di < rows && j + length * dj < cols && board[i][j] != null && board[i + length * di][j + length * dj]!= null 
        && isSameClass(board[i][j], board[i + length * di][j + length * dj])){
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
            if(board[i + k * di][j + k * dj]!=null && board[i + k * di][j + k * dj].getFruitNum()==2){
                //If this is a bomb fruit, remove all fruits in same type
                removeSameColor(i + k * di,j + k * dj);
            }else if(board[i + k * di][j + k * dj]!=null && board[i + k * di][j + k * dj].getFruitNum()==1){
                //If this is a special fruit, remove all fruits that are in the same row and col
                removeRowCol(i + k * di,j + k * dj);
            }else{
                removeObject(board[i + k * di][j + k * dj]);
                board[i + k * di][j + k * dj] = null;
            }
        }
        score += length;
    }
    
    /**
     * Removes all the fruits that have the type as fruit at board[i][j]
     * 
     * @param i     The row index
     * @param j     The column index
     */
    private void removeSameColor(int i, int j){         
        if(board[i][j] instanceof Blueberry){
            clearFruit(Blueberry.class);
        }else if(board[i][j] instanceof Peach){
            clearFruit(Peach.class);
        }else if(board[i][j] instanceof Pear){
            clearFruit(Pear.class);
        }else if(board[i][j] instanceof Pineapple){
            clearFruit(Pineapple.class);
        }else if(board[i][j] instanceof Strawberry){
            clearFruit(Strawberry.class);
        }
        score+=5;
    }
    
    /**
     * Clears the specified type of fruit from the board by setting their positions to null.
     * 
     * @param fruitClass the class type of the fruit to be cleared from the board
     */
    private void clearFruit(Class<?> fruitClass) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (fruitClass.isInstance(board[x][y])) {
                    removeObject(board[x][y]);
                    board[x][y] = null;
                }
            }
        }
    }
    
    /**
     * Removes all fruits in the given row i and column j.
     * 
     * @param i     The row index to remove
     * @param j     The column index to remove
     */
    private void removeRowCol(int i, int j){
        for(int x=0; x<cols;x++){
            if(board[i][x]!=null){
                removeObject(board[i][x]);
                board[i][x] = null;
            }
        }
        for(int x=0; x<rows;x++){
            if(board[x][j]!=null){
                removeObject(board[x][j]);
                board[x][j] = null;
            }
        }
        score+=3;
    }
    
    /**
     * Check if two objects are in the same class (include subclass).
     * 
     * @param i     First fruit
     * @param j     Second fruit
     * @return boolean     Return "true" if they are in the same class
     */
    private boolean isSameClass(Fruit one, Fruit two){
        boolean result = false;
        if(one.getClass().equals(two.getClass()))result=true;
        else if(one instanceof Blueberry && two instanceof Blueberry)result=true;
        else if(one instanceof Peach && two instanceof Peach)result=true;
        else if(one instanceof Pear && two instanceof Pear)result=true;
        else if(one instanceof Pineapple && two instanceof Pineapple)result=true;
        else if(one instanceof Strawberry && two instanceof Strawberry)result = true;
        return result;
    }

    /**
     * Adds fruits onto board.
     * If hasn't been set up already, initialize fruits. 
     * 
     * @param isNew   Initial set up or not
     */
    public void drawBoard(boolean isNew){
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
                if(isNew || board[i][j] == null) board[i][j] = getRandomFruit();
                try{
                    addObject(board[i][j],x+j*65,y+i*65);
                }
                catch (NullPointerException e){
                    System.out.println(i + ", " + j + " is null");
                }
            }
        }
        addObject(melon, 110,515);
        addObject(brush, 235,520);
    }

    /**
     * Returns width/height of tile
     * 
     * @return  size of tile
     */
    public int getTileSize(){
        return CELL_SIZE;
    }

    /**
     * Removes all current selection boxes from world.
     */
    public void resetSelection(){
        ArrayList<Selection> selections = (ArrayList<Selection>) getObjects(Selection.class);
        
        for(Selection s : selections){
            s.resetFruitImage();
        }
        
        Selection.setSelecting(false);
        removeObjects(selections);
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
     * Stores the indexes of the first first within 2D array. 
     * Remove the first fruit and replace it with the second fruit at the same position within 2D array.
     * 
     * @param oldOne     Fruit that needs to be replaced 
     * @param newOne     New fruit that replaces the old one
     */
    public void replace(Fruit oldOne, Fruit newOne){
        int one = getIndex(oldOne,true);
        int two = getIndex(oldOne,false);
        removeObject(oldOne);
        board[one][two] = null;
        board[one][two] = newOne;
        addObject(board[one][two],x+two*65,y+one*65);
    }
    
    /**
     * Swaps positions of two fruits within the 2D array,
     * determines whether swap is valid or not, and handles each appropriately.
     * 
     * @param outerIndex1   Outer index of first fruit
     * @param innerIndex1   Inner index of first fruit
     * @param outerIndex2   Outer index of second fruit
     * @param outerIndex2   Inner index of second fruit
     * @param direction A number representing direction of selected tile(0-3)
     */
    public void swapFruits(int outerIndex1, int innerIndex1, int outerIndex2, int innerIndex2, int direction){
        Fruit temp = board[outerIndex1][innerIndex1];
        board[outerIndex1][innerIndex1] = board[outerIndex2][innerIndex2];
        board[outerIndex2][innerIndex2] = temp;
        
        //if switch made a crush possible
        if(crushFour(false) || crushThree(false) || crushFive(false)){
            board[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1];            
            board[outerIndex1][innerIndex1] = temp;
            //if fruits are currently being cleared
            if(getObjects(Fruit.class).size() >= cols*rows){
                //removes selection box
                resetSelection();
                Swap swap = new Swap(board[outerIndex1][innerIndex1], board[outerIndex2][innerIndex2],direction, true);
                addObject(swap, board[outerIndex1][innerIndex1].getX(), board[outerIndex1][innerIndex1].getY());
            }
            else{ //if fruits are currently being cleared
                dropFruits();
                //call method again
                swapFruits(outerIndex1, innerIndex1, outerIndex2, innerIndex2,direction);
            }
        }
        else{ //if switch cannot form a crush
            board[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1];            
            board[outerIndex1][innerIndex1] = temp;
            Swap swap = new Swap(board[outerIndex1][innerIndex1], board[outerIndex2][innerIndex2],direction, false);
            addObject(swap, board[outerIndex1][innerIndex1].getX(), board[outerIndex1][innerIndex1].getY());
        }
    }
    
    
    /**
     * Swaps positions of two fruits within the 2D array.
     * 
     * @param outerIndex1   Outer index of first fruit
     * @param innerIndex1   Inner index of first fruit
     * @param outerIndex2   Outer index of second fruit
     * @param outerIndex2   Inner index of second fruit
     * @param direction A number representing direction of selected tile(0-3)
     */
    public void swapIndexes(int outerIndex1, int innerIndex1, int outerIndex2, int innerIndex2){
        Fruit temp = board[outerIndex1][innerIndex1];
        board[outerIndex1][innerIndex1] = board[outerIndex2][innerIndex2];
        board[outerIndex2][innerIndex2] = temp;
        System.out.println(outerIndex1 + "," + innerIndex1 + " switches with " + outerIndex2 + "," + innerIndex2);
    }
    
    /**
     * Creates explosion effect for cleared fruits.
     */
    private void triggerExplosions() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == null) {
                    Explosion explosion = new Explosion();
                    addObject(explosion, x + j * 65, y + i * 65);
                }
            }
        }
    }
    
    /**
     * Getter for number of rows on board.
     * 
     * @return int  Number of rows
     */
    public int getRows(){
        return rows;
    }
    
     /**
     * Getter for number of columns on board.
     * 
     * @return int  Number of columns
     */
    public int getColumns(){
        return cols;
    }
}
