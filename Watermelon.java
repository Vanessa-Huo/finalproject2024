import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Watermelon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Watermelon extends Boosters
{
    /**
     * THIS IS VANESSA.
     * I'M STILL FIXING THE BUGS HERE...PLEASE DONT MOVE ANYTHING THANKS!!!
     */
    private boolean dragging = false;
    private Watermelon temp;
    private int numberUse;
    private boolean onBoard;
    
    public Watermelon(boolean onBoard){
        this.onBoard = onBoard;
        //The booster is activated only if the player had already played the game once
        if(unLock()){
            if(onBoard){
                image = new GreenfootImage("watermelonBomb.png");
                image.scale(56,56);
                pulseImage = new GreenfootImage("watermelonBomb.png");
                pulseImage.scale(65,65);
            }
            else{
                image = new GreenfootImage("watermelonBomb.png");
                image.scale(100,100);
            }
        }else{
            image = new GreenfootImage("watermelonGrey.png");
            image.scale(100,100);
        }
        setImage(image);
        pulseCount = 0;
    }
    
    public void act(){
        super.act();
        if(unLock() && Greenfoot.mouseClicked(this) && !onBoard){
            dragging = true;
        }
        if(dragging){
            if (Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() instanceof Fruit) {
                replaceFruit();
            }
        }
    }
    
    private void replaceFruit(){
        Fruit clickedFruit = (Fruit) Greenfoot.getMouseInfo().getActor();
        if (clickedFruit != this) {
            MainScreen world = (MainScreen) getWorld();
            world.replace(clickedFruit, new Watermelon(true));
            dragging = false;
        }
    }
}
