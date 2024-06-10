import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialPineapple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpecialPineapple extends Pineapple
{
    /**
     * Act - do whatever the SpecialPineapple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SpecialPineapple(){
        image = new GreenfootImage("button-yellow.png");
        image.scale(56,56);
        setImage(image);
    }
}
