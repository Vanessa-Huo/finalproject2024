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
            //Greenfoot.stop();
            Greenfoot.delay(60);
            EndingScreen end = new EndingScreen();
            Greenfoot.setWorld(end);
        }
    }
     
    private void updateImage()
    {
        //Generates an image for the label, can be replaced with other images
        GreenfootImage label = new GreenfootImage(200, 100);
       
        label.setColor(Color.BLACK);
        label.setFont(new Font("SansSerif", true, false, 70));
        label.drawString(Integer.toString(timer/55), 60, 80);        
        setImage(label);
    }
}