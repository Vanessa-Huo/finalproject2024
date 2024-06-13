import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * When Paintbrush is used, the user can select three fruits to paint stripes on.
 * 
 * 
 * @author Vanessa Huo
 * @version June 2024
 */
public class Paintbrush extends Booster
{
    private boolean paint = false;
    //private boolean onBoard;
    //private static int numBooster;
    
    public Paintbrush(boolean onBoard){
        super(onBoard,"paintbrush.png","paintbrushGrey.png");
    }
    
    public void act(){
        if(unLock() && Greenfoot.mouseClicked(this) && !onBoard && getNumB()>0){
            paint = true;
        }
        if(paint){
            if (Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() instanceof Fruit) {
                paintFruit();
            }
        }
    }
    
    private void paintFruit(){
        Fruit clickedFruit = (Fruit) Greenfoot.getMouseInfo().getActor();
        if (clickedFruit != this) {
            MainScreen world = (MainScreen) getWorld();
            world.paintStripes(clickedFruit);
            numBrush--;
            paint = false;
        }
    }
}
