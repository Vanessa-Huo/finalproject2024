import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An explosion effect for fruits that have been cleared.
 * 
 * @author Luke Xiao 
 * @version (06.10.2024)
 * Image: https://www.vectorstock.com/royalty-free-vector/cartoon-smoke-explosion-game-sprite-animate-effect-vector-42343324
 */
public class Explosion extends Actor
{
    private GreenfootImage[] images;
    private int currentImage;
    private double delay;
    private int delayCounter;
    private static GreenfootSound[] explosionSound;
    private static int explosionSoundIndex;
    public Explosion() {
        images = new GreenfootImage[9]; // Adjust the number of images accordingly
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("images/Explosion/Cut/" + i + ".png");
        }
        currentImage = 0;
        delay = 2.5; // Reduced delay to speed up animation
        delayCounter = 0;
        setImage(images[currentImage]);
        
    }

    public static void init() {
        explosionSoundIndex = 0;
        explosionSound = new GreenfootSound[30];
        for(int i = 0; i < explosionSound.length; i++) {
            explosionSound[i] = new GreenfootSound("explosion.mp3");
        }
    }
    
    public static void playExplosionSound() {
        explosionSound[explosionSoundIndex].setVolume(50);
        explosionSound[explosionSoundIndex].play();
        explosionSoundIndex++;
        if(explosionSoundIndex >= explosionSound.length) {
            explosionSoundIndex = 0;
        }
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
