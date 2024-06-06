import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Se
 */
public class Selection extends Actor
{
    private MouseInfo mouse;
    public static final Color TRANSPARENT_WHITE = new Color (255, 255, 255, 80);
    private GreenfootImage image;
    private int width, height;
    private Actor actor;
    private int initialPosX, initialPosY;
    private int actCount;
    /**
     * width = smallest side
     */
    public Selection(Actor actor, int width, int height){
        this.actor = actor;
        this.width = width;
        this.height = height;

        image = new GreenfootImage (width, height);
        //draws selection 
        image.setColor(TRANSPARENT_WHITE);
        image.fillRect(0, 0, width-1, height - 1);

        int cornerLength = width/6;
        image.setColor(Color.WHITE);

        //top left corner
        image.drawLine(0, 0, cornerLength, 0);
        image.drawLine(0,0,0,cornerLength);

        //top right corner
        image.drawLine(width-cornerLength, 0, width, 0);
        image.drawLine(width-1, 0, width-1, cornerLength);

        //bottom left corner
        image.drawLine(0, height, 0, height-cornerLength);
        image.drawLine(0, height-1, cornerLength, height-1);

        //bottom right corner
        image.drawLine(width-cornerLength, height-1, width, height-1);
        image.drawLine(width-1, height-1, width-1, height-cornerLength-1);

        setImage(image);

        actCount = 0;
    }

    public void act(){
        if(actCount == 1){
            initialPosX = getX();
            initialPosY = getY(); 
        }
        followMouse();
        actCount++;
    }

    private void followMouse () {
        mouse = Greenfoot.getMouseInfo();
        int xCoord, yCoord;
        //Mouse cannot be null
        if (mouse != null){
            // Check for a click on selection box
            if(Greenfoot.mouseDragged(this)){
                xCoord = mouse.getX();
                yCoord = mouse.getY();
                System.out.println(directionToSwitch(xCoord, yCoord));
                switch(directionToSwitch(xCoord, yCoord)){
                    case 0:
                        setLocation(initialPosX, initialPosY-65);
                        break;
                    case 1:
                        setLocation(initialPosX+65, initialPosY);
                        break;
                    case 2:
                        setLocation(initialPosX, initialPosY+65);
                        break;
                    case 3:
                        setLocation(initialPosX-65, initialPosY);
                        break;
                }
                System.out.println(getX() + ", " + getY());
            }
        }   
    }

    /**
     * 0: switch up
     * 1: switch right
     * 2: switch down
     * 3: switch left
     */
    private int directionToSwitch(int xCoord, int yCoord){
        int xDisplacement = xCoord - initialPosX;
        int yDisplacement = yCoord - initialPosY;
        //left or right switch
        if (Math.abs(xDisplacement) > Math.abs(yDisplacement)){
            //left
            if(xDisplacement < 0){
                return 3;
            }
            else{//right
                return 1;
            }
        }
        //up or down switch
        else{
            //up
            if(yDisplacement < 0){
                return 0;
            }
            else{//down
                return 2;
            }
        }
    }
}
