import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * <h1>Fruit Crusher - Grid Based Game</h1>
 * <p> Fruit Crusher is a matching-tile puzzle game inspired by popular matching game Candy Crush. 
 *     Players swap colored fruits on a aboard to create a row or column of at least three matching fruits.
 *     The aim is to score higher points in a time limit of 60s by matching fruits, each match will help 
 *     clear the board and crush more fruits! 
 * <P> Matching four fruits in a row or column will create a special striped fruit. When matched, it clears
 *     an entire row and column. Matching five fruits will create a spotted bomb fruit. When matched, it 
 *     clears all fruits of that color from the board. 
 * <P> Boosters are activated after player reaches LEVEL 1. The number of boosters player gets for next round 
 *     depends on the points scored in previous round. Boosters that are not used in this round will be saved 
 *     for next round. Watermelon Bomb will clear all the fruits within a 3x3 range around it. Paintbrush will turn 
 *     a selected fruit to a special striped fruit of that color. 
 * 
 * @author Vanessa Huo, Megan Lee, Rick Li, Anya Shah, Gennie Won, Luke Xiao
 * @version June 2024
 */
public class MainScreen extends World
{
    //Current level
    public static int LEVEL = 0;
    
    //Grid 
    private static final int CELL_SIZE = 65;
    private static Fruit[][] board;
    private int rows, cols;
    private int x, y;
    
    //Game state
    private boolean runGame;
    
    //Score
    private int score = 0;

    //Vairables on screen
    Timer timer;
    Label scoreLabel, melonNum, brushNum;
    Watermelon melon;
    Paintbrush brush;

    //Buttons
    private HomeButton home;
    private TutorialButton tut;

    //Animation 
    private GreenfootImage[] explode = new GreenfootImage[9];
    private int animCounter, animDelay, animIndex, maxIndex;
    private enum GameState { CHECK_MATCHES, REMOVE_MATCHES, PLAY_EXPLOSION, FILL_SPACES , GAME_OVER}
    private GameState state;
    
    PrintWriter out;
    public MainScreen()
    {    
        //Create a new world with 1020x720 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 
        
        //The game is still in preparation
        runGame = false;
        
        //Set up background
        setBackground("mainScreen.png");
        
        //Set up and locate buttons
        home = new HomeButton();
        addObject(home, 100, getHeight() - 50);
        tut = new TutorialButton();
        addObject(tut, 250, getHeight() - 50);
        
        //Set up boosters
        melon = new Watermelon(false);
        brush = new Paintbrush(false);

        //Set up the size of the board 
        //according to current game Level
        boardSetUp();
        addObject(new Board(rows,cols,CELL_SIZE), 665,360);
        // Init game board 
        board = new Fruit[rows][cols];
        
        //Init timer and texts
        timer = new Timer();
        scoreLabel = new Label(score, 80);
        scoreLabel.setFillColor(Color.BLACK);
        melonNum = new Label(melon.getNumB(), 30);
        melonNum.setFillColor(Color.BLACK);
        brushNum = new Label(brush.getNumB(), 30);
        brushNum.setFillColor(Color.BLACK);
        
        // Locate timer and texts
        addObject(timer, 175, 175);
        addObject(scoreLabel, 175, 345);
        addObject(melonNum, 150, 555);
        addObject(brushNum, 275, 555);
        
        //Initial set up, draw the board 
        drawBoard(true);

        //Animation varaibles 
        animCounter = 0;
        maxIndex = explode.length;
        setPaintOrder(Label.class, Booster.class);
        state = GameState.CHECK_MATCHES;
        //Greenfoot.setSpeed(70); // Set the speed to 7 0 out of 100
    
        try{
            FileWriter scores = new FileWriter("Scores.txt", true);
            out = new PrintWriter(scores);
        } catch(IOException e){
            System.out.println("IO exception");
        }
    }

    /**
     * Called when world is ran, resets status of Selection
     */
    public void started(){
        Selection.setSelecting(false);
        //activates printwriter for scores
        try{
            FileWriter scores = new FileWriter("Scores.txt", true);
            out = new PrintWriter(scores);
        } catch(IOException e){
            System.out.println("IO exception");
        }
        runGame = true;
    }

    public void act(){
        // Setup
        while(runGame==false && crushThree(true)){
            crushFive(true);
            crushFour(true);
            crushThree(true);
            dropFruits();
            score = 0;
        }
        // Play
        runGame = true; //Start the game
        // Update variables on screen
        scoreLabel.setValue(score);
        melonNum.setValue(melon.getNumB());
        brushNum.setValue(brush.getNumB());
        //updateTimer();
        if (state != GameState.GAME_OVER) {
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
                    triggerExplosionsFour();
                    state = GameState.PLAY_EXPLOSION;
                    break;
                case PLAY_EXPLOSION:
                    if (getObjects(Explosion.class).isEmpty() && getObjects(ExplosionFour.class).isEmpty()) {
                        state = GameState.FILL_SPACES;
                    }
                    break;
                case FILL_SPACES:
                    dropFruits();
                    state = GameState.CHECK_MATCHES;
                    break;
            }
        }
        //Prints score to save file
        if(timer.done){
            out.println(score);
            out.close();
        }
        if(timer.done){
            Greenfoot.setWorld(new EndingScreen(score));
        }
        if (getObjects(Selection.class).size() == 0){
            Selection.setSelecting(false);
        }
        /*
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
                triggerExplosionsFour();
                state = GameState.PLAY_EXPLOSION;
                break;
            case PLAY_EXPLOSION:
                if (getObjects(Explosion.class).isEmpty() && getObjects(ExplosionFour.class).isEmpty()) {
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
    
            
            if(timer.done && once){
                endScreen();
                once = false;
            }
            
            //prints score to save file
            if(!run){
                out.println(score);
                out.close();
            }
        }*/
    }  
    
    /**
     * Determines the size of the board according to current level. 
     * As level increases, the size of the board also increases. 
     */
    private void boardSetUp(){
        if(LEVEL==0){
            rows = 5;
            cols = 6;
        }else if(LEVEL==1){
            rows = 7;
            cols = 6;
        }else if(LEVEL==2){
            rows = 8;
            cols = 7;
        }else{
            rows = 10;
            cols = 10;
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
     * Then, creates a SpecialFruit of the same type at board[i][j].
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
     * Then, creates a BombFruit of the same type at board[i][j].
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

    /**
     * Checks if a watermelonBomb is added to the board. It tirggers a explosion. 
     * The explosion removes all the fruits that are in the 3x3 range around the watermelon. 
     * 
     * @return boolean  Crush was found (true) or not (false)
     */
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
        //Shifts non-empty fruits down to fill empty spaces.
        for (int j = 0; j < cols; j++) {
            for (int i = rows - 1; i >= 0; i--) {
                if (board[i][j] == null) {
                    // Find the first non-empty tile above the current empty space
                    for (int k = i - 1; k >= 0; k--) {
                        // Move the fruit down to the empty space
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
        //Refills the board with new fruits
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

    /**
     * While running the game, delay between acts
     * @param x Number of time steps to delay by
     */
    private void delay(int x){
        if(runGame){
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
     * @return Fruit    A new SpecialFruit
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
     * Given Peach will return a BombPeach
     * 
     * @param fruit     Given fruit
     * @return Fruit    A new BombFruit.
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
     * Removes all the fruits that have the type as the fruit at board[i][j]
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
    }

    /**
     * Clears the specified type of fruit from the board by setting their positions to null.
     * For every fruit that has been removed, increases the score by 1. Bonus score + 5.
     * 
     * @param fruitClass the class type of the fruit to be cleared from the board
     */
    private void clearFruit(Class<?> fruitClass) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (fruitClass.isInstance(board[x][y])) {
                    removeObject(board[x][y]);
                    board[x][y] = null;
                    score++;
                }
            }
        }
        score+=5;
    }

    /**
     * Removes all fruits in the given row i and column j.
     * For every fruit that has been removed, increases the score by 1. Bonus score + 3.
     * 
     * @param i     The row index to remove
     * @param j     The column index to remove
     */
    private void removeRowCol(int i, int j){
        for(int x=0; x<cols;x++){
            if(board[i][x]!=null){
                removeObject(board[i][x]);
                board[i][x] = null;
                score++;
            }
        }
        for(int x=0; x<rows;x++){
            if(board[x][j]!=null){
                removeObject(board[x][j]);
                board[x][j] = null;
                score++;
            }
        }
        score+=3;
    }

    /**
     * Check if two objects are in the same class.
     * If one of the objects is in the subclass, they will still be consiered in the same class. 
     * 
     * @param i     First fruit
     * @param j     Second fruit
     * @return boolean     Return objects are in the same class (true) or not (false)
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
    private void drawBoard(boolean isNew){
        if(!isNew) removeObjects(getObjects(Fruit.class));
        //Locates positions at the center of each grid
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
        //Fills the board with fruits 
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if(isNew || board[i][j]== null) board[i][j] = getRandomFruit();
                addObject(board[i][j],x+j*65,y+i*65);
            }
        }
        //Displays boosters 
        addObject(melon, 110,515);
        addObject(brush, 235,520);
    }

    /**
     * Removes all current selection boxes from world.
     */
    public void resetSelection(){
        //get all selections in the world (should be one max at a time)
        ArrayList<Selection> selections = (ArrayList<Selection>) getObjects(Selection.class);

        //reset each selection's associated fruit's image (not its larger pulse version)
        for(Selection s : selections){
            s.resetFruitImage();
        }

        //reset class variable isSelecting to be false 
        Selection.setSelecting(false);

        //remove all selections
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
        //loop through outer indexes
        for(int i=0; i<rows;i++){
            //loop through inner indexes
            for(int j=0;j<cols;j++){
                //matches given fruit (parameter) to each Fruit on the board by reference 
                if(board[i][j] == fruit){
                    if(outerIndex){ 
                        return i; //returns outer index
                    }
                    else{
                        return j; //returns inner index
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Stores the indexes of the first first within 2D array. 
     * Removes the first fruit and replace it with the second 
     * fruit at the same position within 2D array.
     * 
     * @param oldOne     Fruit that needs to be replaced 
     * @param newOne     New fruit that replaces the old one
     */
    public void replace(Fruit oldOne, Fruit newOne){
        //Get indexes of the fruit within the array
        int one = getIndex(oldOne,true);
        int two = getIndex(oldOne,false);
        removeObject(oldOne);
        board[one][two] = null;
        board[one][two] = newOne;
        addObject(board[one][two],x+two*65,y+one*65);
    }
    
    /**
     * Replaces given fruit with a special(striped) fruit of the same type.  
     * 
     * @param fruit     Fruit that needs to be replaced
     */
    public void paintStripes(Fruit fruit){
        //Get indexes of the fruit within the array
        int one = getIndex(fruit,true);
        int two = getIndex(fruit,false);
        Fruit temp = getSpecialFruit(fruit);
        removeObject(board[one][two]);
        //Replace the fruit with a special fruit
        board[one][two] = temp;
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
        //switches the fruits' indexes
        Fruit temp = board[outerIndex1][innerIndex1]; //temp holds first fruit's reference
        board[outerIndex1][innerIndex1] = board[outerIndex2][innerIndex2]; //first fruit can take second fruit's reference
        board[outerIndex2][innerIndex2] = temp; //second fruit can take first fruit's reference (from temp)

        //checks if switch made a crush possible
        if(crushFour(false) || crushThree(false) || crushFive(false)){ 
            //revert each fruit to its original index so that swap animation can occur (without crush being formed & removed yet)
            board[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1]; //second fruit takes first fruit's reference (its original)        
            board[outerIndex1][innerIndex1] = temp; //first fruit takes temp's reference (its original)

            //removes selection box
            resetSelection();

            //calls swap animation for a successful swap
            swapAnimation(board[outerIndex1][innerIndex1], board[outerIndex2][innerIndex2], direction, 5, true);
        }
        else{ //if switch cannot form a crush
            //revert each fruit to its original index
            board[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1]; //second fruit takes first fruit's reference (its original)        
            board[outerIndex1][innerIndex1] = temp; //first fruit takes temp's reference (its original)[outerIndex2][innerIndex2] = board[outerIndex1][innerIndex1];            

            //calls swap animation for a unsuccessful swap
            swapAnimation(board[outerIndex1][innerIndex1], board[outerIndex2][innerIndex2], direction, 5, false);
        }
    }

    /**
     * A visual animation of two fruit swapping places.
     * If not successful, will call itself twice to return to original positions. 
     * 
     * @param fruit1                First fruit
     * @param frui2                 Second fruit
     * @param direction             A number representing direction of selected tile(0-3)
     * @param displacementFactor    Pixels to move at a time
     * @param isSucessful           Was match valid (True) or not (false)
     */
    public void swapAnimation(Fruit fruit1, Fruit fruit2, int direction, int displacementFactor, boolean isSucessful){
        //loops until displacement equals a cell size (both fruits visually swapped cells)
        for(int displacement = 0; displacement <= getTileSize(); displacement+=displacementFactor){
            //depending on direction, move each fruit accordingly
            switch(direction){
                case 0: //up
                    fruit1.setLocation(fruit1.getX(), fruit1.getY() - displacementFactor);
                    fruit2.setLocation(fruit2.getX(), fruit2.getY() + displacementFactor);
                    break;
                case 1: //right
                    fruit1.setLocation(fruit1.getX() + displacementFactor, fruit1.getY());
                    fruit2.setLocation(fruit2.getX() - displacementFactor, fruit2.getY());
                    break;
                case 2: //down
                    fruit1.setLocation(fruit1.getX(), fruit1.getY() + displacementFactor);
                    fruit2.setLocation(fruit2.getX(), fruit2.getY() - displacementFactor);
                    break;
                case 3: //left
                    fruit1.setLocation(fruit1.getX() - displacementFactor, fruit1.getY());
                    fruit2.setLocation(fruit2.getX() + displacementFactor, fruit2.getY());
                    break;
            }
            delay(1);
        }

        //if not successful
        if(!isSucessful){
            //call again, switch order for fruit parameter so that they reverse back
            swapAnimation(fruit2, fruit1, direction, displacementFactor, true);
        }
        else{
            //indexes of first fruit
            int outerIndex1 = getIndex(fruit1, true);
            int innerIndex1 = getIndex(fruit1, false);

            //indexes of second fruit
            int outerIndex2 = getIndex(fruit2, true);
            int innerIndex2 = getIndex(fruit2, false);

            //"permanantely"
            Fruit temp = board[outerIndex1][innerIndex1]; //temp holds first fruit's reference
            board[outerIndex1][innerIndex1] = board[outerIndex2][innerIndex2]; //first fruit can take second fruit's reference
            board[outerIndex2][innerIndex2] = temp; //second fruit can take first fruit's reference (from temp)
            
            //refreshes board to display changes, crush will then be cleared 
            drawBoard(false);
        }
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
    
    //Method to trigger a popup end screen
    private void endScreen(){
        runGame = false;
        Label endScore = new Label(score, 100);
        EndScreen a = new EndScreen();
        Fadescreen b = new Fadescreen();
        addObject(b, getWidth()/2, getHeight()/2);
        addObject(a, getWidth()/2, getHeight()/2);
        addObject(endScore, getWidth()/2, getHeight()/2 - 50);
        
        HomeButton home = new HomeButton();
        addObject(home, getWidth()/2 - 100, 500);
        AchievementButton ach = new AchievementButton();
        addObject(ach, getWidth()/2 + 100, 500);
    }

    private void triggerExplosionsFour() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                int length = getMatchLength(i, j, 0, 1);
                if (length == 4) {
                    for (int k = 0; k < 4; k++) {
                        if (board[i][j + k] == null) {
                            ExplosionFour explosion = new ExplosionFour();
                            addObject(explosion, x + (j + k) * 65, y + i * 65);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols; j++) {
                int length = getMatchLength(i, j, 1, 0);
                if (length == 4) {
                    for (int k = 0; k < 4; k++) {
                        if (board[i + k][j] == null) {
                            ExplosionFour explosion = new ExplosionFour();
                            addObject(explosion, x + j * 65, y + (i + k) * 65);
                        }
                    }
                }
            }
        }
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
    
    /**
     * Getter for scores.
     * 
     * @return int  Number of scores
     */
    public int getScore() {
        return score;
    }
}
