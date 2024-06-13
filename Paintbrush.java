import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When Paintbrush is used, the user can select three fruits to paint stripes on.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paintbrush extends Boosters
{
    private MouseInfo mouse;
    private boolean isActive, onBoard;
    public Paintbrush(boolean onBoard){
        this.onBoard = onBoard;
        
        if(onBoard){
            image = new GreenfootImage("paintbrush.png");
            image.scale(56,56);
            pulseImage = new GreenfootImage("paintbrush.png");
            pulseImage.scale(65,65);
        }
        else{
            image = new GreenfootImage("paintbrush.png");
            image.scale(90,90);
        }
        setImage(image);
        mouse = Greenfoot.getMouseInfo();
        isActive = false;
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            isActive = true;
        }
    }
}
