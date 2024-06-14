import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides user with a "slideshow" of how to play the game.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class TutorialScreen extends World
{
    GreenfootImage[] instructions = new GreenfootImage[3]; //Images of different instruction panels 
    public static int currentScreen; //Track current screen displayed
    
    /**
     * Constructor for Tutorial Screen.
     */
    public TutorialScreen(){
        super(1024, 720, 1); 
        prepare();
    }
    
    /**
     * Add buttons, assign instructon panel images to array.
     */
    public void prepare() {
        for(int i=0; i<3;i++) {
            instructions[i] = new GreenfootImage("howToPlay" + (i+1) + ".png");
        }

        BackButton backBtn = new BackButton();
        addObject(backBtn, getWidth()/2 - 100, getHeight()-50);
        
        NextButton nextBtn = new NextButton();
        addObject(nextBtn, getWidth()/2 + 100, getHeight()-50);
        
        HomeButton homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
        
        PlayButton playBtn = new PlayButton();
        addObject(playBtn, getWidth() - 100, getHeight() - 50);
        
        setBackground(instructions[currentScreen]);
        
        currentScreen = 0;
    }
    
    /**
     * Switch back to previous screen.
     */
    public static void switchBack(){
        if(currentScreen>0){
            currentScreen--;
        }
    }
    
    /**
     * Switch forward to next screen.
     */
    public static void switchNext(){
        if(currentScreen<2) {
            currentScreen++;
        }
    }
    
    public void act(){
        setBackground(instructions[currentScreen]);
    }
}
