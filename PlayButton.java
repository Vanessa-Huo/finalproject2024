import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author Anya Shah, Gennie Won
 * @version June 2024
 */
public class PlayButton extends Icon
{
    public PlayButton(){
        setImage("playButton.png");
    }
    
    public void act(){
        if (Greenfoot.mouseClicked(this))
        {
            MainScreen world = new MainScreen();
            Greenfoot.setWorld(world);
            Greenfoot.playSound("mouseclick2.mp3");
        }
    }
}
