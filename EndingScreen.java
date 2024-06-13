import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndingScreen extends World
{
    private HomeButton homeBtn;
    private AchievementButton achButton;
    private double halfWidth = getWidth() / 2;
    private double halfHeight = getHeight() / 2;
    
    /**
     * Constructor for objects of class EndingScreen.
     * 
     */
    public EndingScreen(int score)
    {    
        super(1024, 720, 1);
        
        setBackground("gameOverScreen.png");
        
        homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
        
        achButton = new AchievementButton();
        addObject(achButton, getWidth() - 100, getHeight() - 50);
        
        Label endScore = new Label(score, 75);
        endScore.setFillColor(Color.BLACK);
        addObject(endScore, getWidth()/2 - 40, getHeight()/2 + 52);
    }
}
