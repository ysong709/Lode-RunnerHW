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

    public void movementPlayer(){
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();        
        if(!isFalling || isTouching(Ladder.class)){
            if(mouseInfo != null && mouseInfo.getX() < getX() - getImage().getWidth()/2 && leftIsClear() && mouseInfo.getY() > getY() - getImage().getHeight()/2 && 
            mouseInfo.getY() < getY() + getImage().getHeight()/2){
                setLocation(getX() - speed, getY());
                if(num == 3){
                    GreenfootImage run0 = new GreenfootImage("player_run_00.png");
                    run0.mirrorHorizontally();
                    setImage(run0);
                }
                if(num == 6){
                    GreenfootImage run1 = new GreenfootImage("player_run_01.png");
                    run1.mirrorHorizontally();
                    setImage(run1);
                }
                if(num == 9){
                    GreenfootImage run2 = new GreenfootImage("player_run_02.png");
                    run2.mirrorHorizontally();
                    setImage(run2);
                }
                if(num == 12){
                    GreenfootImage run3 = new GreenfootImage("player_run_03.png");
                    run3.mirrorHorizontally();
                    setImage(run3);
                    num = 0;
                }
                num++;
            }else if((mouseInfo != null&& mouseInfo.getX() > getX() + getImage().getWidth()/2 && getX() + speed < getWorld().getWidth()) && 
            mouseInfo.getY() > getY() - getImage().getHeight()/2 && mouseInfo.getY() < getY() + getImage().getHeight()/2){
                setLocation(getX() + speed, getY()); 
                if(num == 3){
                    setImage("player_run_00.png");
                }
                if(num == 6){
                    setImage("player_run_01.png");
                }
                if(num == 9){
                    setImage("player_run_02.png");
                }
                if(num == 12){
                    setImage("player_run_03.png");
                    num = 0;
                }
                num++;
            }
        }
        if(getX() - getImage().getWidth()/2 < 0){
            setLocation(getImage().getWidth()/2, getY());
        }
        if(getX() + getImage().getWidth()/2 > getWorld().getWidth()){
            setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
        }
    }
    public void ladderBarMovement(){
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();        
        if(isTouching(Ladder.class) && !onLadder){
            Actor curLadder = getOneIntersectingObject(Ladder.class);
            if(!curOn){
                setLocation(curLadder.getX(), getY());
                curOn = true;
            }            
            fallSpeed = 0;
            onLadder = true;
        }
        if(onLadder){
            Actor curLadder = getOneIntersectingObject(Ladder.class);
            if(mouseInfo != null && mouseInfo.getY() > getY() + getImage().getHeight()/2 && downIsClear() && mouseInfo.getX() > getImage().getWidth()/2 - getX()
            && mouseInfo.getX() < getImage().getWidth()/2 + getX()){
                setLocation(getX(), getY() + speed);
                if(ladderNum == 3){
                    setImage("player_climb_ladder.png"); 
                }else if(ladderNum == 6){
                    GreenfootImage reverseClimb = new GreenfootImage("player_climb_ladder.png");
                    reverseClimb.mirrorHorizontally();
                    setImage(reverseClimb);
                    ladderNum = 0;
                }
                ladderNum++;
                setLocation(curLadder.getX(), getY());
            }else if(mouseInfo != null && mouseInfo.getY() < getY() - getImage().getHeight()/2 ){
                if(ladderNum == 3){
                    setImage("player_climb_ladder.png"); 
                }else if(ladderNum == 6){
                    GreenfootImage reverseClimb = new GreenfootImage("player_climb_ladder.png");
                    reverseClimb.mirrorHorizontally();
                    setImage(reverseClimb);
                    ladderNum = 0;
                }
                ladderNum++;
                setLocation(getX(), getY() - speed);
                setLocation(curLadder.getX(), getY());
            }
        }
        if((isTouching(Ladder.class) && isTouching(Wall.class)) || (!isTouching(Ladder.class) && isTouching(Wall.class) || isTouching(Bar.class)) || 
        (!isTouching(Wall.class) && !isTouching(Ladder.class) || ! isTouching(Bar.class))){
            onLadder = false;
        }
        if(isTouching(Bar.class)){
            Actor barLoc = getOneIntersectingObject(Bar.class);
            setLocation(getX(), barLoc.getY() + getImage().getHeight()/3 + 1);
            fallSpeed = 0;
            if(mouseInfo != null && mouseInfo.getX() < getX() - getImage().getWidth()/2 && mouseInfo.getY() > getY() - getImage().getHeight()/2 && 
            mouseInfo.getY() < getY() + getImage().getHeight()/2 && leftIsClear()){
                setLocation(getX() - speed, getY());
                setImage("player_bar_hang_00.png");
            }else if((mouseInfo != null&& mouseInfo.getX() > getX() + getImage().getWidth()/2 && getX() + speed < getWorld().getWidth()) && 
            mouseInfo.getY() > getY() - getImage().getHeight()/2 && mouseInfo.getY() < getY() + getImage().getHeight()/2 && rightIsClear()){
                setLocation(getX() + speed, getY());
                setImage("player_bar_hang_01.png");
            }else if(mouseInfo != null && mouseInfo.getY() > getY() + getImage().getHeight()/2 && mouseInfo.getX() > getImage().getWidth()/2 - getX()
            && mouseInfo.getX() < getImage().getWidth()/2 + getX()){
                setLocation(getX(), getY() + speed);
                fallSpeed = 3;
            }
        }
    }


}
