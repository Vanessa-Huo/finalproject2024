import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author Anya Shah
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private PlayButton play;
    private AchievementButton ach;
    private TutorialButton tut;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(1024, 720, 1); 
        setBackground("titlescreen.png");

        tut = new TutorialButton();
        addObject(tut, (getWidth() / 2) - 200, 420);
        play = new PlayButton();
        addObject(play, getWidth() / 2, 420);
        ach = new AchievementButton();
        addObject(ach, (getWidth() / 2) + 200, 420);
    }

    public void act() {
    }
}