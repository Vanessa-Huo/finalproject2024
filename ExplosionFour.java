import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExplosionFour here.
 * 
 * @author Luke Xiao 
 * @version (06.12.2024)
 * Image: https://besthvet.best/product_details/31457511.html
 */
public class ExplosionFour extends Actor
{
    private GreenfootImage[] images;
    private int currentImage;
    private double delay;
    private int delayCounter;

    public ExplosionFour() {
        images = new GreenfootImage[10]; // Adjust the number of images accordingly
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("images/Explosion/Cut4/" + i + ".png");
        }
        currentImage = 0;
        delay = 2.5; // Reduced delay to speed up animation
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
