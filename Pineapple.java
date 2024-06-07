import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pineapple extends Fruit
{
    public Pineapple(){
        image = new GreenfootImage("pineapple.png");
        image.scale(56,56);
        setImage(image);
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
