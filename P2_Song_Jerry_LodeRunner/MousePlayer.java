import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Player moved around the world
 * 
 * @author Jerry
 * @version 3/28/23
 */
public class MousePlayer extends Player
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public String getCommand() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (onLadder) {
            if(mouseInfo != null && mouseInfo.getY() > getY() + getImage().getHeight()/2 && downIsClear()) {
                return "down";
            }
            if(mouseInfo != null && mouseInfo.getX() < getX() - getImage().getWidth()/2){
                return "up";
            }
        }
        if (mouseInfo != null && mouseInfo.getX() < getX() - getImage().getWidth()/2 && leftIsClear()){
            return "left";
        }
        if (mouseInfo != null && mouseInfo.getX() > getX() + getImage().getWidth()/2 && rightIsClear()){
            return "right";
        }        
        return "";
    }
}
