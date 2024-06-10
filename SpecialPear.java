import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialPear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpecialPear extends Pear
{
    /**
     * Act - do whatever the SpecialPear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SpecialPear(){
        image = new GreenfootImage("button-green.png");
        image.scale(56,56);
        setImage(image);
    }
}
