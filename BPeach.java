import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A BPeach is a striped peach.
 * It can be matched in a row/column of 3,4, or 5 with other Peaches.
 * When matched, it will clear all Peaches on the board
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BPeach extends Peach{
    /**
     * A constructor to create a dotted Peach.
     */
    public BPeach(){
        image = new GreenfootImage("polkadotPeach.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("polkadotPeach.png");
        pulseImage.scale(65,65);
        
        fruitNum = 2;
        setImage(image);
    }
}
