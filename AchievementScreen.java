import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementScreen here.
 * 
 * @author Megan Lee 
 * @version (a version number or a date)
 */
public class AchievementScreen extends World
{
    
    public AchievementScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1);
        setBackground("achievementScreen.png");
        
        HomeButton homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
    }
}
