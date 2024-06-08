import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Strawberry is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Strawberry.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Strawberry extends Fruit
{
    public Strawberry(){
        image = new GreenfootImage("strawberry.png");
        image.scale(56,56);
        setImage(image);
        
        pulseCount = 0;
    }
    
     protected void pulseImage(){
        if(pulseCount == 0){
            pulseImage = new GreenfootImage("strawberry.png");
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
