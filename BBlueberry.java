import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A BBlueberry is a dotted blueberry.
 * It can be matched in a row/column of 3,4, or 5 with other Blueberries.
 * When matched, it will clear all Blueberries on the board.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BBlueberry extends Blueberry{
    /**
     * A constructor to create a dotted Blueberry.
     */
    public BBlueberry(){
        image = new GreenfootImage("polkadotBlueberry.png");
        image.scale(56,56);

        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("polkadotBlueberry.png");
        pulseImage.scale(65,65);

        fruitNum = 2;
        setImage(image);
    }
}
