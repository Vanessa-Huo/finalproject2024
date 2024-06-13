import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fruit Superclass.
 * 
 * @author Megan Lee
 */
public abstract class Fruit extends Actor
{
    private MouseInfo mouse;
    protected GreenfootImage image, pulseImage;
    protected Selection selectBox;
    protected int pulseCount;
    protected int fruitNum = 0;

    private static GreenfootSound[] fruitSound;
    private static int fruitSoundIndex;
    
    public static void init()
    {
        fruitSoundIndex = 0;
        fruitSound = new GreenfootSound[20];
        for(int i = 0; i < fruitSound.length; i++) {
            fruitSound[i] = new GreenfootSound("hippo1.mp3");
        }
    }
    
    public static void playHippoSound()
    {
        fruitSound[fruitSoundIndex].setVolume(80);
        fruitSound[fruitSoundIndex].setVolume(40);
        fruitSound[fruitSoundIndex].play();
        fruitSoundIndex++;
        if(fruitSoundIndex >= fruitSound.length) {
            fruitSoundIndex = 0;
        }
    }
    
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        MainScreen world = (MainScreen)getWorld();
        //Mouse cannot be null
        if (mouse != null){
            //if(Greenfoot.mouseClicked(this)) System.out.println("clicked: " + Greenfoot.mouseClicked(this) + ", isSeleciting: " + Selection.isSelecting());
            if(!Selection.isSelecting() && Greenfoot.mouseClicked(this)){
                //System.out.println("new");
                world.resetSelection();
                selectBox = new Selection(this, world.getTileSize(), world.getTileSize());
                getWorld().addObject(selectBox, getX(), getY());
            }
        }
    }
    
    /**
     * 
     * @param direction A number representing direction of selected tile(0-3)
     * @param boolean   Return outer (true) or inner (false) index of new
     * @return int      Outer/inner index of new position
     */
    public int getIndexOfSwap(int direction, boolean returnOuterIndex){
        int result = -1;
        int outerIndex, innerIndex;

        outerIndex = ((MainScreen)getWorld()).getIndex(this, true);
        innerIndex = ((MainScreen)getWorld()).getIndex(this, false);

        if(returnOuterIndex){
            switch(direction){
                case 0: //up
                    result = outerIndex-1;
                    break;
                case 1: //right
                    result = outerIndex;
                    break;
                case 2: //down
                    result = outerIndex+1;
                    break;
                case 3: //left
                    result = outerIndex;
                    break;
            }
        }
        else{
            switch(direction){
                case 0: //up
                    result = innerIndex;
                    break;
                case 1: //right
                    result = innerIndex+1;
                    break;
                case 2: //down
                    result = innerIndex;
                    break;
                case 3: //left
                    result = innerIndex-1;
                    break;
            }
        }
        return result;
        
    }
    
    public int getFruitNum(){
        return fruitNum;
    }
    
    public void resetImage(){
        setImage(image);
    }
    
    protected void pulseImage(){
        if(pulseCount % 20 == 0){
            if(getImage() == image){
                setImage(pulseImage);
            }
            else{
                setImage(image);
            }
        }
        pulseCount++;
    }
}
