import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpecialFruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpecialFruit extends Fruit
{
    /**
     * Act - do whatever the SpecialFruit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public SpecialFruit(int fruitNum){
        this.fruitNum = fruitNum;
        switch(fruitNum){
            case 0: image = new GreenfootImage("button-blue.png"); break;
            case 1: image = new GreenfootImage("button-purple.png"); break;
            case 2: image = new GreenfootImage("button-green.png"); break; 
            case 3: image = new GreenfootImage("button-yellow.png"); break;
            case 4: image = new GreenfootImage("button-red.png"); break;
        }
        image.scale(56,56);
        setImage(image);
        pulseCount = 0;
    }
    
    protected void pulseImage(){
        
        if(pulseCount == 0){
            pulseImage = new GreenfootImage(getImage());
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
