import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PaintbrushDisplay here.
 * 
 * @author (your name) 
 * @version June 2024
 */
public class PaintbrushDisplay extends BoosterDisplay
{
    public PaintbrushDisplay(){
        GreenfootImage pb = new GreenfootImage("paintbrush.png");
        pb.scale(56, 56);
        setImage(pb);
    }
    public PaintbrushDisplay(boolean achievement){
        GreenfootImage pb = new GreenfootImage("paintbrush.png");
        pb.scale(120, 120);
        setImage(pb); 
    }
    /**
     * Act - do whatever the PaintbrushDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
