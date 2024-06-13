import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Booster superclass. 
 * 
 * @author Vanessa Huo
 * @version June 2024
 */
public abstract class Booster extends Fruit
{
    protected GreenfootImage image;
    protected boolean onBoard;
    protected static int numMelon = 0;
    protected static int numBrush = 0;
    public static int addMelon, addBrush;
    /**
     * A method that check if LEVEL is greater than 0, meaning the player had already played once. 
     * If so, unlock the booster.
     */
    public Booster(boolean onBoard, String imageName, String imageNameGrey){
        this.onBoard = onBoard;
        if(unLock()){
            if(onBoard){
                image = new GreenfootImage(imageName);
                image.scale(56,56);
                pulseImage = new GreenfootImage(imageName);
                pulseImage.scale(65,65);
            }
            else{
                //setNumB();
                image = new GreenfootImage(imageName);
                image.scale(100,100);
            }
        }else{
            image = new GreenfootImage(imageNameGrey);
            image.scale(100,100);
            numMelon = 0;
        }
        setImage(image);
        pulseCount = 0;
    }
    
    public boolean unLock(){
        boolean result = false;
        MainScreen world = (MainScreen) getWorld();
        if(world.LEVEL>0){
            result = true;
        }
        return result;
    }
    
    public int getNumB(){
        if(this instanceof Watermelon)return numMelon;
        if(this instanceof Paintbrush)return numBrush;
        return 0;
    }
    
    public static void setNumB(int addMelon, int addBrush){
        numMelon+=addMelon;
        numBrush+=addBrush;
    }
}
