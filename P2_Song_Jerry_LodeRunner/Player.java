import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author Jerry
 * @version 4/4/23
 */
public class Player extends Person
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String getCommand() {
        if (Greenfoot.isKeyDown("left")) {
            return "left";
        }
        if (Greenfoot.isKeyDown("right")) {
            return "right";
        }
        if (Greenfoot.isKeyDown("down")) {
            return "down";
        }
        if (Greenfoot.isKeyDown("up")) {
            return "up";
        }
        return "";
    }
}