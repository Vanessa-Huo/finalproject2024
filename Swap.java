import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Swap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Swap extends Effect
{
    private boolean isSucessful; //fruit swap created a crush (true) or not (false)
    private Fruit fruit1, fruit2;
    private int displacement, direction;
    
    public Swap(Fruit fruit1, Fruit fruit2, int direction, boolean isSucessful){
        this.fruit1 = fruit1;
        this.fruit2 = fruit2;
        this.isSucessful = isSucessful;
        displacement = 0;
    }
    
    public void act(){
        if(displacement < ((MainScreen) getWorld()).getCellSize()){
            swapAnimation();
        }
    }
    
    public void swapAnimation(){
        switch(direction){
            case 0: //up
                fruit1.setLocation(getX(), getY() + 2);
                fruit2.setLocation(getX(), getY() - 2);
                break;
            case 1: //right
                fruit1.setLocation(getX() + 2, getY());
                fruit2.setLocation(getX() - 2, getY());
                break;
            case 2: //down
                fruit1.setLocation(getX(), getY() - 2);
                fruit2.setLocation(getX(), getY() + 2);
                break;
            case 3: //left
                fruit1.setLocation(getX() - 2, getY());
                fruit2.setLocation(getX() + 2, getY());
                break;
        }
        displacement+=2;
    }
}
