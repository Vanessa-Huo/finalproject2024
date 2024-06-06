import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Megan Lee
 */
public class Fruit extends Actor
{
    private MouseInfo mouse;
    protected GreenfootImage image;
    private Selection selectBox;

    public void act(){
        mouse = Greenfoot.getMouseInfo();
        MainScreen world = (MainScreen)getWorld();
        //Mouse cannot be null
        if (mouse != null){
            if(Greenfoot.mouseClicked(this)){
                ((MainScreen) getWorld()).resetSelection();
                selectBox = new Selection(this, world.getTileWidth(), world.getTileHeight());
                getWorld().addObject(selectBox, getX(), getY());
            }
        }
    }

    public int getIndexOfSwap(int direction, boolean outerIndexofOther){
        int result = -1;
        int outerIndex, innerIndex;

        outerIndex = ((MainScreen)getWorld()).getIndex(this, true);
        innerIndex = ((MainScreen)getWorld()).getIndex(this, false);

        System.out.println("Direction: "+ direction);
        if(outerIndexofOther){
            switch(direction){
                case 0: //up
                    result = outerIndex-1;
                    break;
                case 1: //right
                    result = outerIndex;
                    break;
                case 2: //left
                    result = outerIndex;
                    break;
                case 3: //down
                    result = outerIndex+1;
                    break;
            }
        }
        else{
            switch(direction){
                case 0: //up
                    result = innerIndex;
                    break;
                case 1: //right
                    result = innerIndex+1;
                    break;
                case 2: //left
                    result = innerIndex -1;
                    break;
                case 3: //down
                    result = innerIndex;
                    break;
            }
        }
        
        return result;
    }
}
