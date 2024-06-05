import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Se
 */
public class Selection extends Actor
{
    public static final Color TRANSPARENT_WHITE = new Color (255, 255, 255, 128);
    private GreenfootImage image;
    private int width, height;
    private Actor actor;
    /**
     * width = smallest side
     */
    public Selection(Actor actor, int width, int height){
        this.actor = actor;
        this.width = width;
        this.height = height;

        
        image = new GreenfootImage (width, height);

        //draws selection 
        image.setColor(TRANSPARENT_WHITE);
        image.fillRect(0, 0, width-1, height - 1);
        
        
        int cornerLength = width/6;
        image.setColor(Color.WHITE);
        
        //top left corner
        image.drawLine(0, 0, cornerLength, 0);
        image.drawLine(0,0,0,cornerLength);
        
        //top right corner
        image.drawLine(width-cornerLength, 0, width, 0);
        image.drawLine(width, 0, width, cornerLength);
        
        //top
        image.drawLine(0, height, cornerLength, 0);
        image.drawLine(width-cornerLength, height, width, 0);
        
        setImage(image);
    }

    public void act(){
        if(Greenfoot.mouseClicked(this)){

        }
    }
}
