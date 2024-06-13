import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Watermelon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Watermelon extends Booster
{
    private boolean dragging = false;
    //private boolean onBoard;
    //private static int numBooster;

    public Watermelon(boolean onBoard){
        super(onBoard,"watermelonBomb.png","watermelonGrey.png");
    }
    
    public void act(){
        //super.act();
        if(unLock() && Greenfoot.mouseClicked(this) && !onBoard && getNumB()>0){
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
            numMelon--;
            dragging = false;
        }
    }
}
