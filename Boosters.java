import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Booster superclass. 
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Boosters extends Fruit
{
    protected GreenfootImage image;
    
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * A method that checks if LEVEL is greater than 0, meaning the player had already played once. 
     * If so, unlock the booster.
     */
    public boolean unLock(){
        boolean result = false;
        MainScreen world = (MainScreen) getWorld();
        if(world.LEVEL>0){
            result = true;
        }
        return result;
    }
}
