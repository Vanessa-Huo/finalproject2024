import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
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
    
    private GreenfootSound music; //sound
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
        music = new GreenfootSound("backgroundMusic3.mp3");
        music.setVolume(70);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(play)){
            music.stop();
            MainScreen game = new MainScreen();
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseclick.mp3");
        }
    }
    
    /**
     * Called when world is ran, controls longer sounds
     */
    public void stopped() {
        music.pause();
    }
    
    /**
     * Called when world is ran, controls longer sounds
     */
    public void started() {
        music.playLoop();
    }
}