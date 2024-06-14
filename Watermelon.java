import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When a Watermelon is added, it clears all the fruits within a 3x3 range around it.
 * 
 * @author Vanessa Huo
 * @version June 2024
 */
public class Watermelon extends Booster
{
    private boolean dragging = false;

    public Watermelon(boolean onBoard){
        super(onBoard,"watermelonBomb.png","watermelonGrey.png");
    }
    
    public void act(){
        if(unLock() && Greenfoot.mouseClicked(this) && !onBoard && getNumB()>0){
            dragging = true;
        }
        if(dragging){
            if (Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() instanceof Fruit) {
                replaceFruit();
            }
        }
    }
    
    /**
     * Replaces selected fruit with watermelon bomb
     */
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
