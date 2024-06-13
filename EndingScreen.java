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
    private boolean playOnce = true;
    private int score;
    /**
     * Constructor for objects of class EndingScreen.
     */
    public EndingScreen(int score)
    {    
        super(1024, 720, 1);
        this.score = score;
        
        setBackground("gameOverScreen.png");
        
        homeBtn = new HomeButton();
        addObject(homeBtn, 100, getHeight() - 50);
        
        achButton = new AchievementButton();
        addObject(achButton, getWidth() - 100, getHeight() - 50);
        
        Label endScore = new Label(score, 75);
        endScore.setFillColor(Color.BLACK);
        addObject(endScore, getWidth()/2 + 15, getHeight()/2 + 52);
        
        WatermelonDisplay booster1 = new WatermelonDisplay();
        addObject(booster1, getWidth()/2 + 65, getHeight()/2 + 50);
        
        PaintbrushDisplay booster2 = new PaintbrushDisplay();
        addObject(booster2, getWidth()/2 + 175, getHeight()/2 + 53);
    }
    
    public void act() {
        if(playOnce){
            Label endScore = new Label(score, 75);
            endScore.setFillColor(Color.BLACK);
            addObject(endScore, getWidth()/2 - 35, getHeight()/2 + 52);
            
            Label bst1 = new Label("x" + numMelonNextRound(), 50);
            addObject(bst1, getWidth()/2 + 85, getHeight()/2 + 60);
            
            Label bst2 = new Label("x" + numBrushNextRound(), 50);
            addObject(bst2, getWidth()/2 + 195, getHeight()/2 + 60);
            Booster.setNumB(numMelonNextRound(), numBrushNextRound());
            
            playOnce=false;
        }
    }
    
    /**
     * Returns Number of watermelon bombs player can use for next round
     * @return int Number of watermelon bombs for next round
     */
    private int numMelonNextRound(){
        if(score>120){
            return 3;
        }else if(score>70){
            return 2;
        }else{
            return 1;
        }
    }
    
    /**
     * Returns Number of brushes player can use for next round
     * @return int Number of brushes for next round
     */
    private int numBrushNextRound(){
        if(score>180){
            return 3;
        }else if(score>140){
            return 2;
        }else if(score>100){
            return 1;
        }else{
            return 0;
        }
    }
}
