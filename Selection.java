import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Selection is a white frame that indicates an adjacent tile 
 * an Actor on a grid can interact with, as selected by the user.
 * 
 * It reads mouse input for dragging, and will update its position accordingly.
 * Possible directions include: one tile up, right, down, or left of its current tile.
 * 
 * Interaction of Actor and next tile is customizable via changing the checkKey() method.
 * 
 * @author  Megan Lee
 * @version June 2024
 */
public class Selection extends Actor
{
    public static final Color TRANSPARENT_WHITE = new Color (255, 255, 255, 80);
    
    private static boolean isSelecting; //if user is currently dragging box to choose location
    
    private GreenfootImage image;//selection box picture
    private Actor actor; //target actor
    private MouseInfo mouse; //mouse of user
    private int width, height; //dimensions of selection box 
    private int initialPosX, initialPosY; //initial position of selection box
    private int pos; //current location of selection box; -1 = default, 0 = above, 1 = right, 2 = down, 3 = left
    private boolean topEdge, rightEdge, bottomEdge, leftEdge;

    /**
     * A constructor for Selection - specify its associated Actor as well as the width
     * and height of the selection box (most likely the size of the tile)
     * 
     * @param actor     Target Actor
     * @param width     Width of selection box
     * @param height    Height of selection box
     */
    public Selection(Actor actor, int width, int height){
        this.actor = actor;
        this.width = width;
        this.height = height;

        //uncomment below for built-in image
        //image = drawBox ();

        image = new GreenfootImage("selectBorder.png");
        image.setTransparency(0);
        setImage(image);

        pos = -1;
    }

    /**
     * A method called by the Greenfoot system when an Actor is added to a world.
     * Determines whether selected fruit is along any outer edges of the board.
     */
    public void addedToWorld(World world){
        MainScreen mainScreen = (MainScreen) world;
        int rowNumber = mainScreen.getIndex((Fruit) actor, true) + 1; //row number of fruit
        int columnNumber = mainScreen.getIndex((Fruit) actor, false) + 1; //column number of fruit

        topEdge = rowNumber == 1; //on first row
        rightEdge = columnNumber == mainScreen.getRows(); //on last column
        bottomEdge = rowNumber  == mainScreen.getColumns(); //on bottom row
        leftEdge = columnNumber  == 1; //on first column

        //set initial position of selection box at start
        initialPosX = actor.getX();
        initialPosY = actor.getY(); 
    }

    public void act(){
        if(actor.getWorld() == null){
            //if actor removed, remove its selection box too
            getWorld().removeObject(this);
        }
        else{
            checkKey("Enter"); 

            ((Fruit)actor).pulseImage();

            followMouse();
        }
    }

    /**
     * Checks for mouse input for dragging.
     * Will update position of selection box accordingly in the direction of most signficance.
     */
    private void followMouse () {
        mouse = Greenfoot.getMouseInfo(); //user's mouse
        int xCoord, yCoord; //mouse coords

        //cell dimensions to translate to
        int xMovementFactor = width; 
        int yMovementFactor = height;
        
        //when mouse has been dragged by user, display selection
        if(image.getTransparency() == 0 && pos != -1){
            image.setTransparency(255);
        }
        
        if (mouse != null){
            // check for dragging of selection box
            if(Greenfoot.mouseDragged(this)){
                isSelecting = true;
                //get coords of mouse
                xCoord = mouse.getX();
                yCoord = mouse.getY();

                //determine position of selection box
                pos = directionToSwitch(xCoord, yCoord);
                switch(pos){
                    case 0: //move up
                        if(!topEdge) setLocation(initialPosX, initialPosY - yMovementFactor);
                        break;
                    case 1: //move right
                        if(!rightEdge)setLocation(initialPosX + xMovementFactor, initialPosY);
                        break;
                    case 2: //move down
                        if(!bottomEdge)setLocation(initialPosX, initialPosY + yMovementFactor);
                        break;
                    case 3://move left
                        if(!leftEdge)setLocation(initialPosX - xMovementFactor, initialPosY);
                        break;
                }
            }
            
            //after dragging, no longer selecting
            if(Greenfoot.mouseDragEnded(this)){
                isSelecting = false;
            }
        }   
    }

    /**
     * Determines direction of selection box: the direction (+/- x or +/- y) with the greatest 
     * magnitude of difference between mouse and target actor's location.
     * 
     * Returns a integer value representative of direction of selected tile relative to original:
     * 0: switch up
     * 1: switch right
     * 2: switch down
     * 3: switch left
     * 
     * @param xCoord    Mouse's X-coordinate
     * @param yCoord    Mouse's Y-coordinate
     * @return int      A number representing direction of selected tile(0-3)
     */
    private int directionToSwitch(int xCoord, int yCoord){
        int xDisplacement = xCoord - initialPosX;
        int yDisplacement = yCoord - initialPosY;

        //horizontal displacement more significant
        if (Math.abs(xDisplacement) > Math.abs(yDisplacement)){ //
            //mouse moved leftwards --> left
            if(xDisplacement < 0){
                return 3;
            }
            //mouse moved rightwards --> right
            else{
                return 1;
            }
        }
        //vertical displacement more significant
        else{
            //mouse moved higher --> up
            if(yDisplacement < 0){
                return 0;
            }
            //mouse moved lower --> down
            else{
                return 2;
            }
        }
    }

    /**
     * If a tile is selected and confirm key pressed, initializes the fruit swap.
     * 
     * @param key   Key to confirm selection
     */
    private void checkKey(String key){
        if(pos != -1){ //has been moved (up, down, right, or left)
            MainScreen world = (MainScreen) getWorld();
            if(Greenfoot.isKeyDown(key)){
                int newOuterIndex = ((Fruit)actor).getIndexOfSwap(pos,true);
                int newInnerIndex = ((Fruit)actor).getIndexOfSwap(pos,false);
                world.swapFruits(world.getIndex((Fruit)actor, true), world.getIndex((Fruit)actor, false), newOuterIndex, newInnerIndex, pos);

                isSelecting = false;

                world.removeObject(this);
            }
        }
    }

    /**
     * Selected Actor will change to its original image. 
     */
    public void resetFruitImage(){
        ((Fruit) actor).resetImage();
    }

    /**
     * Draws a translucent white box with a border at its corners.
     * 
     * @return GreenfootImage   A translucent white box
     */
    private GreenfootImage drawBox(){
        GreenfootImage image = new GreenfootImage (width, height);
        //draws selection 
        image.setColor(TRANSPARENT_WHITE);
        image.fillRect(0, 0, width-1, height - 1);

        int cornerLength = width/6;
        image.setColor(Color.WHITE);

        //top left corner
        image.drawLine(0, 0, cornerLength, 0);
        image.drawLine(0,0,0,cornerLength);

        //top right corner
        image.drawLine(width-cornerLength, 0, width, 0);
        image.drawLine(width-1, 0, width-1, cornerLength);

        //bottom left corner
        image.drawLine(0, height, 0, height-cornerLength);
        image.drawLine(0, height-1, cornerLength, height-1);

        //bottom right corner
        image.drawLine(width-cornerLength, height-1, width, height-1);
        image.drawLine(width-1, height-1, width-1, height-cornerLength-1);

        return image;
    }

    /**
     * Returns whether user is currently dragging mouse.
     * Checked by Actor to ensure end of a drag won't trigger anything.
     * 
     * @return boolean  User choosing a tile (true) or not (false)
     */
    public static boolean isSelecting(){
        return isSelecting;
    }

    /**
     * Sets isSelecting value
     * 
     * @param isSelectingNow is selection box in use (true) or not (false)
     */
    public static void setSelecting(boolean isSelectingNow){
        isSelecting = isSelectingNow;
    }
}
