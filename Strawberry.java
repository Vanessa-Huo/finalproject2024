import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Strawberry is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Strawberry.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Strawberry extends Fruit{
    /**
     * A constructor to create a regular Strawberry.
     */
    public Strawberry(){
        image = new GreenfootImage("strawberry.png");
        image.scale(56,56);
        setImage(image);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("strawberry.png");
        pulseImage.scale(65,65);
        
        pulseCount = 0;
    }
}
