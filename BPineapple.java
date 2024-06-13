import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A BPineapple is a dotted pineapple.
 * It can be matched in a row/column of 3,4, or 5 with other Pineapples.
 * When matched, it will clear all Pineapples on the board.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BPineapple extends Pineapple{
    /**
     * A constructor to create a dotted Pineapple.
     */
    public BPineapple(){
        image = new GreenfootImage("polkadotPineapple.png");
        image.scale(56,56);
        
        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("polkadotPineapple.png");
        pulseImage.scale(65,65);
        
        fruitNum = 2;
        setImage(image);
    }
}
