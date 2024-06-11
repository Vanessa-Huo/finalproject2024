import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialPeach here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpecialPeach extends Peach
{
    /**
     * Act - do whatever the SpecialPeach wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SpecialPeach(){
        image = new GreenfootImage("button-purple.png");
        image.scale(56,56);
        setImage(image);
    }
}
