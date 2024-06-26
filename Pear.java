import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Pear is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Pears.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Pear extends Fruit{
    /**
     * A constructor create a regular Pear.
     */
    public Pear(){
        image = new GreenfootImage("pear.png");
        image.scale(56,56);    
        setImage(image);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("pear.png");
        pulseImage.scale(65,65);
            
        pulseCount = 0;
    }
}
