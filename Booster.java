import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A booster is a special item that cannot be formed during the game. 
 * It helps players clear the board by providing various advantages.
 * Boosters are only activated when game level is greater than 0. 
 * 
 * @author Vanessa Huo
 * @version June 2024
 */
public abstract class Booster extends Fruit
{
    protected GreenfootImage image;
    protected boolean onBoard;
    protected static int numMelon = 0; //Number of Watermelon bomb the user can use
    protected static int numBrush = 0; //Number of Paint Brush the user can use
    public static int addMelon, addBrush;
    
    public Booster(boolean onBoard, String imageName, String imageNameGrey){
        this.onBoard = onBoard; 
        //Works only when booster is activated 
        if(unLock()){
            //If the booster is on the board, it has pulseImage feature.
            if(onBoard){
                image = new GreenfootImage(imageName);
                image.scale(56,56);
                pulseImage = new GreenfootImage(imageName);
                pulseImage.scale(65,65);
            }
            //The booster is simply displayed on screen at lower left corner
            else{
                image = new GreenfootImage(imageName);
                image.scale(100,100);
                }
        }else{
            //When booster is not activated, keep its image grey
            image = new GreenfootImage(imageNameGrey);
            image.scale(100,100);
            numMelon = 0;
        }
        setImage(image);
        pulseCount = 0;
    }
    
    /**
     * Checks current game level. If greater than 0, the boosters are activated.
     */
    public boolean unLock(){
        boolean result = false;
        MainScreen world = (MainScreen) getWorld();
        if(world.LEVEL>0){
            result = true;
        }
        return result;
    }
    
    /**
     * Returns the number of boosters player can use.
     * @return int The number of boosters player can use
     */
    public int getNumB(){
        if(this instanceof Watermelon)return numMelon;
        if(this instanceof Paintbrush)return numBrush;
        return 0;
    }
    
    /**
     * Increases the number of boosters player can use.
     * 
     * @param addMelon   The number of watermelon bomb increased
     * @param addBrush   The number of paint brush increased
     */
    public static void setNumB(int addMelon, int addBrush){
        numMelon+=addMelon;
        numBrush+=addBrush;
    }
}
