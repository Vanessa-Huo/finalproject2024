import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BStrawberry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BStrawberry extends Strawberry
{
    /**
     * Act - do whatever the BStrawberry wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BStrawberry(){
        image = new GreenfootImage("polkadotStrawberry.png");
        image.scale(56,56);
        pulseImage = new GreenfootImage("polkadotStrawberry.png");
        pulseImage.scale(65,65);
        fruitNum = 2;
        setImage(image);
    }
}
