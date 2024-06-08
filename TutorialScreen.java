import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialScreen here.
 * 
 * @author Anya Shah
 * @version (a version number or a date)
 */
public class TutorialScreen extends World
{
    private Instruction ins;
    private HomeButton home;
    
    /**
     * Constructor for objects of class TutorialScreen.
     * 
     */
    public TutorialScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1);
        setBackground("plainBG.png");
        
        //ins = new Instruction();
        //addObject(ins, getWidth() / 2, getHeight() / 2);
        home = new HomeButton();
        addObject(home, 100, getHeight() - 50);
    }
    public void act() {
        if(Greenfoot.mouseClicked(home)) {
            TitleScreen title = new TitleScreen();
            Greenfoot.setWorld(title);
        }
    }
}
