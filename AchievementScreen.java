import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Achievement Screen will display a user's all-time 
 * highscore, along with the number of boosters used
 * to achieve that highscore.
 * 
 * @author Megan Lee 
 * @version June 2024
 */
public class AchievementScreen extends World
{
    /**
     * A constructor to create achievement screen.
     */
    public AchievementScreen(){    
        super(1024, 720, 1);
        setBackground("achievementScreen.png");
        
        HomeButton homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
    }
}
