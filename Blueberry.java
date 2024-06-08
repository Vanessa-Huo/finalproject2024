import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Megan Lee
 */
public class Blueberry extends Fruit
{
    public Blueberry(){
        image = new GreenfootImage("blueberry.png");
        image.scale(56,56);
        setImage(image);
    }

    protected void pulseImage(){
        if(pulseCount == 0){
            pulseImage = new GreenfootImage("blueberry.png");
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
