import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BackButton is a button used by the user to navigate instruction screen. 
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BackButton extends Icon
{
    /**
     * Constructor to create a back button.
     */
    public BackButton(){
        setImage("backButton.png");
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            TutorialScreen.switchBack(); //Switches back to previous screen
            Greenfoot.playSound("mouseclick.mp3");
        }
    }
}
