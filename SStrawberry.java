import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A SStrawberry is a striped strawberry.
 * It can be matched in a row/column of 3,4, or 5 with other Strawberries.
 * When matched, it will clear all fruits in its row & column.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class SStrawberry extends Strawberry{
    /**
     * A constructor to create a striped Strawberry.
     */
    public SStrawberry(){
        image = new GreenfootImage("strawberryStripe.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("strawberryStripe.png");
        pulseImage.scale(65,65);
        
        fruitNum = 1;
        setImage(image);
    }
}
