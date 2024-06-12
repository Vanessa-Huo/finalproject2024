import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialButton here.
 * 
 * @author Anya Shah
 * @version June 2024
 */
public class TutorialButton extends Icon
{
    public TutorialButton(){
        setImage("tutorialButton.png");
    }
    public void act(){
        if (Greenfoot.mouseClicked(this))
        {
            TutorialScreen world = new TutorialScreen();
            Greenfoot.setWorld(world);
        }
    }
}
