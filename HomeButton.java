import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HomeButton here.
 * 
 * @author Anya Shah
 * @version (a version number or a date)
 */
public class HomeButton extends Icon
{
    public HomeButton(){
        setImage("homeButton.png");
    }
    
    public void act(){
        if (Greenfoot.mouseClicked(this))
        {
            TitleScreen world = new TitleScreen();
            Greenfoot.setWorld(world);
            Greenfoot.playSound("mouseclick.mp3");
        }
    }
}
