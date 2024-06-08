import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BackButton is a button used by the user to navigate instruction screen. 
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class BackButton extends Icon
{
    public BackButton(){
        setImage("backButton.png");
    }
    public void act()
    {
         if (Greenfoot.mouseClicked(this))
        {
            TutorialScreen.switchNext(); //Switches back to previous screen
        }
    }
}
