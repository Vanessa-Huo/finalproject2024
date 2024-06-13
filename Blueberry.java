import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Blueberry is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Blueberrries.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Blueberry extends Fruit{
    /**
     * A constructor to create a regular Blueberry.
     */
    public Blueberry(){
        image = new GreenfootImage("blueberry.png");
        image.scale(56,56);
        setImage(image);

        //scaled larger than regular image to simulate a pulse
        pulseImage = new GreenfootImage("blueberry.png");
        pulseImage.scale(65,65);

        pulseCount = 0;
    }
}
