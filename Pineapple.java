import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pineapple is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Pineapples.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Pineapple extends Fruit{
    /**
     * A constructor create a regular Pineapple.
     */
    public Pineapple(){
        image = new GreenfootImage("pineapple.png");
        image.scale(56,56);
        setImage(image);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("pineapple.png");
        pulseImage.scale(65,65);
        
        pulseCount = 0;
    }
}
