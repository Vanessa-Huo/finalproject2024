import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialPear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SPear extends Pear
{
    /**
     * Act - do whatever the SpecialPear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SPear(){
        image = new GreenfootImage("button-green.png");
        image.scale(56,56);
        fruitNum = 1;
        setImage(image);
    }
    
    protected void pulseImage(){
        if(pulseCount == 0){
            pulseImage = new GreenfootImage("button-green.png");
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
