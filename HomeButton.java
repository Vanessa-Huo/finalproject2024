import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HomeButton is a button used by the user to return to home screen. 
 * 
 * @author Anya Shah
 * @version June 2024
 */
public class HomeButton extends Icon
{
    /**
     * Constructor to create a home button.
     */
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
