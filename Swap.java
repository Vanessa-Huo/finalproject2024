import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A visual animation of two fruit swapping places.
 * If not successful, will swap twice to return to original positions. 
 * 
 * @author Megan Lee
 * @version June 2024
 */
public class Swap extends Effect{
    private boolean isSucessful; //fruit swap created a crush (true) or not (false)
    private Fruit fruit1, fruit2;
    private int displacement, direction,displacementFactor;
    
    public Swap(Fruit fruit1, Fruit fruit2, int direction, boolean isSucessful){
        this.fruit1 = fruit1;
        this.fruit2 = fruit2;
        this.isSucessful = isSucessful;
        this.direction = direction;
        setImage(new GreenfootImage(1,1));
        displacement = 0;
        displacementFactor=5;
    }
    
    public void act(){
        if(displacement < ((MainScreen) getWorld()).getTileSize()){
            swapAnimation();
        }
        else if(displacement >= ((MainScreen) getWorld()).getCellSize() && !isSucessful){
            displacement = 0;
            Fruit temp = fruit1;
            fruit1 = fruit2;
            fruit2 = temp;
            isSucessful = true;
            MainScreen world = (MainScreen) getWorld();
            //world.swapIndexes(world.getIndex(fruit1, true), world.getIndex(fruit1, false), world.getIndex(fruit2, true), world.getIndex(fruit2, false));
            //world.drawBoard(false);
        }
        else{
            MainScreen world = (MainScreen) getWorld();
            //world.swapIndexes(world.getIndex(fruit1, true), world.getIndex(fruit1, false), world.getIndex(fruit2, true), world.getIndex(fruit2, false));
            //world.drawBoard(false);
            //world.removeObject(this);
        }
    }
    
    public void swapAnimation(){
        switch(direction){
            case 0: //up
                fruit1.setLocation(fruit1.getX(), fruit1.getY() - displacementFactor);
                fruit2.setLocation(fruit2.getX(), fruit2.getY() + displacementFactor);
                break;
            case 1: //right
                fruit1.setLocation(fruit1.getX() + displacementFactor, fruit1.getY());
                fruit2.setLocation(fruit2.getX() - displacementFactor, fruit2.getY());
                break;
            case 2: //down
                fruit1.setLocation(fruit1.getX(), fruit1.getY() + displacementFactor);
                fruit2.setLocation(fruit2.getX(), fruit2.getY() - displacementFactor);
                break;
            case 3: //left
                fruit1.setLocation(fruit1.getX() - displacementFactor, fruit1.getY());
                fruit2.setLocation(fruit2.getX() + displacementFactor, fruit2.getY());
                break;
        }
        displacement+=displacementFactor;
    }
}
