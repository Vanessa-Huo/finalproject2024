import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WatermelonDisplay here.
 * 
 * @author (your name) 
 * @version June 2024
 */
public class WatermelonDisplay extends BoosterDisplay
{
    public WatermelonDisplay(){
        GreenfootImage wt = new GreenfootImage("watermelonBomb.png");
        wt.scale(56, 56);
        setImage(wt);
    }
    public WatermelonDisplay(boolean achievement){
        GreenfootImage wt = new GreenfootImage("watermelonBomb.png");
        wt.scale(135, 135);
        setImage(wt);   
    }
    /**
     * Act - do whatever the WatermelonDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
