import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 2;
    int fallSpeed;
    int num = 0;
    int ladderNum = 0;
    boolean isFalling = false;
    boolean standChanged = false;
    boolean onLadder = false;
    boolean curOn = false;
    public boolean leftIsClear() {
        Actor touchWall = getOneObjectAtOffset(getImage().getHeight()/2 - 1, 0, Wall.class); 
        return touchWall == null && getX() - getImage().getWidth()/2 > 0;
    }

    public boolean rightIsClear() {
        Actor touchWall = getOneObjectAtOffset(1 + getImage().getWidth()/2, 0, Wall.class); 
        return touchWall == null && getX() + getImage().getWidth()/2 < getWorld().getWidth();
    }

    public boolean downIsClear() {
        Actor touchWall = getOneObjectAtOffset(0, getImage().getHeight()/2 + 1, Wall.class); 
        return touchWall == null;
    }
    public void movement(){
        if(!isFalling || isTouching(Ladder.class)){
            if(Greenfoot.isKeyDown("left") && leftIsClear()){
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
            }else if(Greenfoot.isKeyDown("right") && rightIsClear() && getX() + speed < getWorld().getWidth()){
                setLocation(getX() + speed, getY());
                if(num == 1){
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
    }
    public void checkForFall(){
        if(!isTouching(Wall.class) && !isTouching(Ladder.class) && downIsClear()){
            isFalling = true;
            standChanged = false;
            fallSpeed = 3;
            setLocation(getX(), getY() + fallSpeed);
            setImage("player_fall.png");
        }else if(isTouching(Wall.class) || !downIsClear()){
            isFalling = false;
            fallSpeed = 0;            
            while(isTouching(Wall.class)){
                setLocation(getX(), getY() - 1);
            }
            if(!standChanged){
                setImage("player_stand.png");
                standChanged = true;
            }            
        } 
    }
    public void actionsOnLadderBar(){
        if(isTouching(Bar.class)){
            Actor barLoc = getOneIntersectingObject(Bar.class);
            setLocation(getX(), barLoc.getY() + getImage().getHeight()/3 + 1);
            fallSpeed = 0;
            if(Greenfoot.isKeyDown("left") && leftIsClear()){
                setLocation(getX() - speed, getY());
                setImage("player_bar_hang_00.png");
            }else if(Greenfoot.isKeyDown("right") && rightIsClear()){
                setLocation(getX() + speed, getY());
                setImage("player_bar_hang_01.png");
            }else if(Greenfoot.isKeyDown("down")){
                setLocation(getX(), getY() + speed);
                fallSpeed = 3;
            }
        }
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
            if(Greenfoot.isKeyDown("down") && downIsClear()){
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
            }else if(Greenfoot.isKeyDown("up")){
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
    }
    public void movePlayer(){        
        checkForFall();
        movement();
        actionsOnLadderBar();
        if(getX() - getImage().getWidth()/2 < 0){
            setLocation(getImage().getWidth()/2, getY());
        }
        if(getX() + getImage().getWidth()/2 > getWorld().getWidth()){
            setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
        }      
        if((isTouching(Ladder.class) && isTouching(Wall.class)) || (!isTouching(Ladder.class) && isTouching(Wall.class) || isTouching(Bar.class)) || 
            (!isTouching(Wall.class) && !isTouching(Ladder.class) || ! isTouching(Bar.class))){
            onLadder = false;
        }
        
    }

    public void act()
    {
        movePlayer();
    }
}
