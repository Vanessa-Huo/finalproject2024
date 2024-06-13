import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A BPear is a dotted pear.
 * It can be matched in a row/column of 3,4, or 5 with other Pears.
 * When matched, it will clear all Pears on the board.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BPear extends Pear{
    /**
     * A constructor to create a dotted Pear.
     */
    public BPear(){
        image = new GreenfootImage("polkadotPear.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("polkadotPear.png");
        pulseImage.scale(65,65);
        
        fruitNum = 2;
        setImage(image);
    }
}
