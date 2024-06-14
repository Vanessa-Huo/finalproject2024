import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An icon for watermelon bomb booster.
 * 
 * @author Rick Li
 * @version June 2024
 */
public class WatermelonDisplay extends Icon
{
    /**
     * Constructor for watermelon bomb icon
     */
    public WatermelonDisplay(){
        GreenfootImage wt = new GreenfootImage("watermelonBomb.png");
        wt.scale(56, 56);
        setImage(wt);
    }
    
    /**
     * Constructor for watermelon bomb icon
     */
    public WatermelonDisplay(boolean achievement){
        GreenfootImage wt = new GreenfootImage("watermelonBomb.png");
        wt.scale(135, 135);
        setImage(wt);   
    }
}
