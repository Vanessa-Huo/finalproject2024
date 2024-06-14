import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HomeButton.
 * 
 * @author Anya Shah
 * @version June 2024
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
