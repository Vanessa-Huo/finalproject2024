import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BPeach here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BPeach extends Peach
{
    /**
     * Act - do whatever the BPeach wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BPeach(){
        image = new GreenfootImage("button-purple.png");
        image.scale(56,56);
        fruitNum = 2;
        setImage(image);
    }
    
    protected void pulseImage(){
        if(pulseCount == 0){
            pulseImage = new GreenfootImage("button-purple.png");
            pulseImage.scale(65,65);
        }

        if(pulseCount % 20 == 0){
            if(getImage() == image){
                setImage(pulseImage);
            }
            else{
                setImage(image);
            }
        }
        
        pulseCount++;
    }
}
