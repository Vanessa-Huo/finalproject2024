import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Paintbrush here.
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
