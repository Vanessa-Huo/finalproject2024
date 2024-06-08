import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * NextButton is a button used by the user to navigate instruction screen. 
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class NextButton extends Icon
{
    public NextButton(){
        setImage("nextButton.png");
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            TutorialScreen.switchNext(); //Switches forward to next screen
        }
    }
}
