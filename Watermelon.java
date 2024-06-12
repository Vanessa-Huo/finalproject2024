import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Watermelon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Watermelon extends Fruit
{
    /**
     * Act - do whatever the Watermelon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean dragging = false;
    private Watermelon temp;
    private boolean first;
    
    public Watermelon(boolean first){
        image = new GreenfootImage("watermelonBomb.png");
        image.scale(56,56);
        setImage(image);
        this.first=first;
        
        pulseImage = new GreenfootImage("watermelonBomb.png");
        pulseImage.scale(65,65);
        
        pulseCount = 0;
    }
    
    public void act(){
        super.act();
        if(Greenfoot.mouseClicked(this) && first){
            //Selection.setSelecting();
            dragging = true;
        }
        if(dragging){
            followMouse();
            if (Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() instanceof Fruit) {
                replaceFruit();
            }
        }
    }
    
    private void replaceFruit(){
        Fruit clickedFruit = (Fruit) Greenfoot.getMouseInfo().getActor();
        if (clickedFruit != this) {
            MainScreen world = (MainScreen) getWorld();
            world.replace(clickedFruit, new Watermelon(false));
            //getWorld().removeObject(temp);
            dragging = false;
        }
    }
    
    private void followMouse() {
        temp = new Watermelon(false);
        if(Greenfoot.mouseMoved(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            temp.setLocation(mouse.getX(),mouse.getY());
        }
    }
}
