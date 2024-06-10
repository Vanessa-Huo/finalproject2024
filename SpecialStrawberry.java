import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialStrawberry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpecialStrawberry extends Strawberry
{
    /**
     * Act - do whatever the SpecialStrawberry wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SpecialStrawberry(){
        image = new GreenfootImage("button-red.png");
        image.scale(56,56);
        setImage(image);
    }
}
