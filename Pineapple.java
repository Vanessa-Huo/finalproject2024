import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pineapple is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Pineapples.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Pineapple extends Fruit
{
    public Pineapple(){
        image = new GreenfootImage("pineapple.png");
        image.scale(56,56);
        setImage(image);
        pulseCount = 0;
    }
    
     protected void pulseImage(){
        if(pulseCount == 0){
            pulseImage = new GreenfootImage("pineapple.png");
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
