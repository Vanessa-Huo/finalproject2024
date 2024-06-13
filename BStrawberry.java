import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A BStrawberry is a dotted strawberry.
 * It can be matched in a row/column of 3,4, or 5 with other Strawberries.
 * When matched, it will clear all Strawberries on the board.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BStrawberry extends Strawberry{
    /**
     * A constructor to create a dotted Strawberry.
     */
    public BStrawberry(){
        image = new GreenfootImage("polkadotStrawberry.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("polkadotStrawberry.png");
        pulseImage.scale(65,65);
        
        fruitNum = 2;
        setImage(image);
    }
}
