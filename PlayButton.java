import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * StartButton is a button used by the user to return to switch to game screen.
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
