import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A SBlueberry is a striped blueberry.
 * It can be matched in a row/column of 3,4, or 5 with other Blueberrries.
 * When matched, it will clear all fruits in its row & column.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class SBlueberry extends Blueberry{
    /**
     * A constructor to create a striped Blueberry.
     */
    public SBlueberry(){
        image = new GreenfootImage("blueberryStripe.png");
        image.scale(56,56);

        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("blueberryStripe.png");
        pulseImage.scale(65,65);

        fruitNum = 1;
        setImage(image);
    }
}
