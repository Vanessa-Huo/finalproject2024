import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A SPineapple is a striped pineapple.
 * It can be matched in a row/column of 3,4, or 5 with other Pineapples.
 * When matched, it will clear all fruits in its row & column.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class SPineapple extends Pineapple{
    /**
     * A constructor to create a striped Pineapple.
     */
    public SPineapple(){
        image = new GreenfootImage("pineappleStripe.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("pineappleStripe.png");
        pulseImage.scale(65,65);
        
        fruitNum = 1;
        setImage(image);
    }
}
