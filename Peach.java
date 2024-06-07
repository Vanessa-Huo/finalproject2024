import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Peach extends Fruit
{
    public Peach(){
        image = new GreenfootImage("peach.png");
        image.scale(56,56);
        
        setImage(image);
    }
    
     protected void pulseImage(){
        if(pulseCount == 0){
            pulseImage = new GreenfootImage("peach.png");
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
