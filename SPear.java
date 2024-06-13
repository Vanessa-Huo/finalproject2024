import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A SPear is a striped pear.
 * It can be matched in a row/column of 3,4, or 5 with other Peaches.
 * When matched, it will clear all fruits in its row & column.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class SPear extends Pear{
    /**
     * A constructor to create a striped Pear.
     */
    public SPear(){
        image = new GreenfootImage("pearStripe.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("pearStripe.png");
        pulseImage.scale(65,65);
        
        fruitNum = 1;
        setImage(image);
    }
}
