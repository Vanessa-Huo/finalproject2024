import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Peach is a type of fruit.
 * It can be matched in a row/column of 3,4, or 5 with other Peach.
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Peach extends Fruit
{
    public Peach(){
        image = new GreenfootImage("peach.png");
        image.scale(56,56);
        setImage(image);

        pulseImage = new GreenfootImage("peach.png");
        pulseImage.scale(65,65);

        pulseCount = 0;
    }

    private void specialFruit1(){
        image = new GreenfootImage("homeButton.png"); //replace with special fruit image
        image.scale(56,56);
        setImage(image);
        specialCount = 1;
    }

    private void specialFruit2(){
        image = new GreenfootImage("backButton.png"); //replace with special fruit image
        image.scale(56,56);
        setImage(image);
        specialCount = 2;
    }
}
