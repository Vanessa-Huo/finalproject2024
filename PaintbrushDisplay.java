import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An icon for paintbrush booster.
 * 
 * @author Rick Li
 * @version June 2024
 */
public class PaintbrushDisplay extends Icon
{
    /**
     * Constructor for paintbrush icon
     */
    public PaintbrushDisplay(){
        GreenfootImage pb = new GreenfootImage("paintbrush.png");
        pb.scale(56, 56);
        setImage(pb);
    }
    
    /**
     * A variation of a constructor for paintbrush icon.
     * Used on achievement screen.
     */
    public PaintbrushDisplay(boolean achievement){
        GreenfootImage pb = new GreenfootImage("paintbrush.png");
        pb.scale(120, 120);
        setImage(pb); 
    }
}
