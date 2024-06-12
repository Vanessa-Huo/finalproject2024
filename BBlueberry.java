import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BBlueberry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BBlueberry extends Blueberry
{
    /**
     * Act - do whatever the BBlueberry wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BBlueberry(){
        image = new GreenfootImage("polkadotBlueberry.png");
        image.scale(56,56);
        
        pulseImage = new GreenfootImage("polkadotBlueberry.png");
        pulseImage.scale(65,65);
        
        fruitNum = 2;
        setImage(image);
    }
}
