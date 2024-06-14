import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * NextButton is a button used by the user to navigate instruction screen. 
 * 
 * @author Megan Lee, Gennie Won
 * @version June 2024
 */
public class NextButton extends Icon
{
    /**
     * Constructor to create a next button.
     */
    public NextButton(){
        setImage("nextButton.png");
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            TutorialScreen.switchNext(); //Switches forward to next screen
            Greenfoot.playSound("mouseclick.mp3");
        }
    }
}
