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
    private int delayCounter;
    private static final int FRAME_DELAY = 2; // Controls the speed of the animation

    public ExplosionFour() {
        images = new GreenfootImage[10]; // Adjust the number of images accordingly
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("images/Explosion/Cut4/" + i + ".png");
        }
        currentImage = 0;
        delayCounter = 0;
        setImage(images[currentImage]);
    }

    public void act() 
    {
        delayCounter++;
        if (delayCounter >= FRAME_DELAY) {
            delayCounter = 0;
            currentImage++;
            if (currentImage < images.length) {
                setImage(images[currentImage]);
            } else {
                getWorld().removeObject(this);
            }
        }
    }
}
