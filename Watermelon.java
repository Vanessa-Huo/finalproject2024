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
     * Act - do whatever the Watermelon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean dragging = false;
    private Watermelon temp;
    private boolean first, onBoard;
    
    public Watermelon(boolean first, boolean onBoard){
        
        
        this.first=first;
        this.onBoard = onBoard;
        
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
        setImage(image);
        
        pulseCount = 0;
    }
    
    public void act(){
        super.act();
        if(Greenfoot.mouseClicked(this) && first){
            //Selection.setSelecting();
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
            world.replace(clickedFruit, new Watermelon(false,true));
            //getWorld().removeObject(temp);
            dragging = false;
            setImage(image);
        }
    }
    
    private void followMouse() {
        temp = new Watermelon(false,true);
        if(Greenfoot.mouseMoved(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            temp.setLocation(mouse.getX(),mouse.getY());
        }
    }
}
