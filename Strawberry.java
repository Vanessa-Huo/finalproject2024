import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Strawberry extends Fruit
{
    public Strawberry(){
        image = new GreenfootImage("strawberry.png");
        image.scale(56,56);
        setImage(image);
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
