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
    private int score;
    // private MainScreen main;
    // Label scoreLabel;
    /**
     * Constructor for objects of class EndingScreen.
     * 
     */
    public EndingScreen()
    {    
        super(1024, 720, 1);
        
        setBackground("gameOverScreen.png");
        
        // score = main.getScore();
        // scoreLabel = new Label(score, 80);
        // addObject(scoreLabel, getWidth()/2, getHeight()/2);
    }
    public void act() {
        homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
        
        achButton = new AchievementButton();
        addObject(achButton, getWidth() - 100, getHeight() - 50);
    }
}
