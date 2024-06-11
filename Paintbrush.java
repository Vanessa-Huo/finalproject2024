import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When Paintbrush is used, the user can select three fruits to paint stripes on.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paintbrush extends Effect
{
    private MouseInfo mouse;
    private boolean isActive;
    public Paintbrush(){
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
