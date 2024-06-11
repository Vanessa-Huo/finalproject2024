import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides user with a "slideshow" of how to play the game.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class TutorialScreen extends World
{
    //GreenfootImage[] instructions = new GreenfootImage[3]; //Images of different instruction panels 
    public static int currentScreen = 0; //Track current screen displayed
    private PlayButton play; // TEMP
    private HomeButton home; // TEMP
    public TutorialScreen(){
        super(1024, 720, 1); 
        //prepare();
        /**
         * TEMP
         */
        setBackground("tempInstructions.png");
        play = new PlayButton();
        addObject(play, 330, 600);
        home = new HomeButton();
        addObject(home, 330, 700);
    }
    
    /**
     * Add buttons, assign instructon panel images to array.
     */
    public void prepare() {
        for(int i=0; i<3;i++) {
            //instructions[i] = new GreenfootImage("images/howtoplay" + (i+1) + ".jpg");
            //instructions[i].scale(600,400);
        }
        
        BackButton backBtn = new BackButton();
        addObject(backBtn, getWidth()/2 - 100, getHeight()-100);
        
        NextButton nextBtn = new NextButton();
        addObject(nextBtn, getWidth()/2 + 100, getHeight()-100);
        
        HomeButton homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
        //setBackground(instructions[currentScreen]);
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
        //setBackground(instructions[currentScreen]);
        
        /**
         * TEMP
         */
        if(Greenfoot.mouseClicked(play)) {
            MainScreen main = new MainScreen();
            Greenfoot.setWorld(main);
        }
        if(Greenfoot.mouseClicked(home)) {
            TitleScreen title = new TitleScreen();
            Greenfoot.setWorld(title);
        }
    }
}
