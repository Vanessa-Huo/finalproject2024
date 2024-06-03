import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementButton extends Icon
{
    /**
     * Act - do whatever the AchievementButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage("ach.png");
        GreenfootImage image = getImage();
        image.scale((int) (image.getWidth()*0.7), (int) (image.getHeight()*0.7));
    }
}
