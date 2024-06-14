import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *StartButton is a button used by the user to return to switch to instructions screen.
 * 
 * @author Anya Shah, Gennie Won
 * @version June 2024
 */
public class TutorialButton extends Icon
{
    /**
     * Constructor to create a tutorial button.
     */
    public TutorialButton(){
        setImage("tutorialButton.png");
    }
    
    public void act(){
        if (Greenfoot.mouseClicked(this))
        {
            TutorialScreen world = new TutorialScreen();
            Greenfoot.setWorld(world);
            Greenfoot.playSound("mouseclick.mp3");
        }
    }
}
