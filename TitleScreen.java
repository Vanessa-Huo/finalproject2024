import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private PlayButton play;
    private AchievementButton ach;
    private SaveButton save;
    private TutorialButton tut;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 
        setBackground("titleBg.jpg");
        
        play = new PlayButton();
        addObject(play, getWidth() /  2, getHeight() / 2);
        ach = new AchievementButton();
        addObject(ach, getWidth() / 2, (getHeight() / 2) + 100);
        save = new SaveButton();
        addObject(save, getWidth() / 2, (getHeight() / 2) + 200);
        tut = new TutorialButton();
        addObject(tut, getWidth() / 2, (getHeight() / 2) + 300);
        
    }
    
    public void act() {
        if(Greenfoot.mouseClicked(play)) {
            MainScreen game = new MainScreen();
            Greenfoot.setWorld(game);
        }
    }
}
