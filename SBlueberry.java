import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialBlue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SBlueberry extends Blueberry
{
    /**
     * Act - do whatever the SpecialBlue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SBlueberry(){
        image = new GreenfootImage("blueberryStripe.png");
        image.scale(56,56);
        
        pulseImage = new GreenfootImage("blueberryStripe.png");
        pulseImage.scale(65,65);
        
        fruitNum = 1;
        setImage(image);
    }
}
