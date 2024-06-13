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
        addObject(endScore, getWidth()/2 - 60, getHeight()/2 + 52);
        
        WatermelonDisplay booster1 = new WatermelonDisplay();
        PaintbrushDisplay booster2 = new PaintbrushDisplay();
        Label bst1 = new Label("x" + "2", 50);
        Label bst2 = new Label("x" + "1", 50);
        addObject(booster1, getWidth()/2 + 65, getHeight()/2 + 50);
        addObject(booster2, getWidth()/2 + 175, getHeight()/2 + 53);
        addObject(bst1, getWidth()/2 + 85, getHeight()/2 + 60);
        addObject(bst2, getWidth()/2 + 195, getHeight()/2 + 60);
    }
}
