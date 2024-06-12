import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BPineapple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BPineapple extends Pineapple
{
    /**
     * Act - do whatever the BPineapple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BPineapple(){
        image = new GreenfootImage("button-yellow.png");
        image.scale(56,56);
        pulseImage = new GreenfootImage("button-yellow.png");
        pulseImage.scale(65,65);
        fruitNum = 2;
        setImage(image);
    }

}
