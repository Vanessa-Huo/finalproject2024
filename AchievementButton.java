import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementButton here.
 * 
 * @author Anya Shah
 * @version (a version number or a date)
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
