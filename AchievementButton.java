import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AchievementButton.
 * 
 * @author Anya Shah
 * @version June 2024
 */
public class AchievementButton extends Icon
{
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
