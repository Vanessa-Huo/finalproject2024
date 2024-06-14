import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AchievementButton is a button used by the user to switch to achievements screen. 
 * 
 * @author Anya Shah
 * @version June 2024
 */
public class AchievementButton extends Icon
{
    /**
     * Constructor for Achievement Screen
     */
    public AchievementButton(){
        setImage("trophyButton.png");
    }
    
    public void act(){
        if (Greenfoot.mouseClicked(this))
        {
            AchievementScreen world = new AchievementScreen();
            Greenfoot.setWorld(world);
            Greenfoot.playSound("mouseclick.mp3");
        }
    }
}
