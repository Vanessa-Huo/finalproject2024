import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private GreenfootImage[] images;
    private int currentImage;
    private double delay;
    private int delayCounter;

    public Explosion() {
        images = new GreenfootImage[3]; // Adjust the number of images accordingly
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("images/Explosion/Cut/" + i + ".png");
        }
        currentImage = 0;
        delay = 1.5; // Reduced delay to speed up animation
        delayCounter = 0;
        setImage(images[currentImage]);
        
    }

    public void act() 
    {
        if (delayCounter >= delay) {
            currentImage++;
            if (currentImage < images.length) {
                setImage(images[currentImage]);
            } else {
                getWorld().removeObject(this);
            }
            delayCounter = 0;
        } else {
            delayCounter++;
        }
    }
}
