import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndingScreen extends World
{
    private HomeButton home;
    private AchievementButton ach;
    private double halfWidth = getWidth() / 2;
    private double halfHeight = getHeight() / 2;
    /**
     * Constructor for objects of class EndingScreen.
     * 
     */
    public EndingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1);
        
        setBackground("plainBG.png");
        home = new HomeButton();
        addObject(home, ((int) halfWidth / 2) + 80, 570);
        ach = new AchievementButton();
        addObject(ach, ((getWidth() / 2) + (int) halfWidth / 2) - 80, 570);
    }
    public void act() {
        if(Greenfoot.mouseClicked(home)) {
            TitleScreen title = new TitleScreen();
            Greenfoot.setWorld(title);
        }
        if(Greenfoot.mouseClicked(ach)) {
            AchievementScreen achieve = new AchievementScreen();
            Greenfoot.setWorld(achieve);
        }
    }
}
