import greenfoot.*;

public class Timer extends Actor
{
    private int timer = 55*60; //about 60 seconds
 
    public Timer()
    {
        updateImage();
    }
     
    public void act()
    {
        timer--;
        if (timer % 55 == 0) updateImage();
        if (timer < 1) {
            Greenfoot.stop();
            Greenfoot.delay(15);
            EndingScreen end = new EndingScreen();
            Greenfoot.setWorld(end);
        }
    }
     
    private void updateImage()
    {
        //Generates an image for the label, can be replaced with other images
        setImage(new GreenfootImage("" + timer/55, 80, Color.BLACK, null));
    }
}