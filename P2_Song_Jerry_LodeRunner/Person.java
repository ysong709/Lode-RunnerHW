import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor
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
    String run0;
    String run1;
    String run2;
    String run3;
    String barHang0;
    String barHang1;
    String Ladder;
    String stand;
    String fall;
    public void setImages(){
        run0 = "player_run_00.png";
        run1 = "player_run_01.png";
        run2 = "player_run_02.png";
        run3 = "player_run_03.png";
        barHang0 = "player_bar_hang_00.png";
        barHang1 = "player_bar_hang_01.png";
        Ladder = "player_climb_ladder.png";
        stand = "player_stand.png";
        fall = "player_fall.png";
    }
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
    
    public void fallCheck(){
        if(!isTouching(Wall.class) && !isTouching(Ladder.class) && downIsClear()){
            isFalling = true;
            standChanged = false;
            fallSpeed = 3;
            setLocation(getX(), getY() + fallSpeed);
            setImage(fall);
        }else if(isTouching(Wall.class) || !downIsClear()){
            isFalling = false;
            fallSpeed = 0;            
            while(isTouching(Wall.class)){
                setLocation(getX(), getY() - 1);
            }
            if(!standChanged){
                setImage(stand);
                standChanged = true;
            }            
        }
    }

    public String getCommand() {
        return "";
    }

    
    public void act()
    {
        setImages();
        fallCheck();
        movementPlayer();
        ladderBarMovement();  
    }
    
    public void ladderBarMovement(){
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
            String direction = getCommand();
            Actor curLadder = getOneIntersectingObject(Ladder.class);
            if(direction.equals("down") && downIsClear()){
                setLocation(getX(), getY() + speed);
                if(ladderNum == 3){
                    setImage(Ladder); 
                }else if(ladderNum == 6){
                    GreenfootImage reverseClimb = new GreenfootImage(Ladder);
                    reverseClimb.mirrorHorizontally();
                    setImage(reverseClimb);
                    ladderNum = 0;
                }
                ladderNum++;
                setLocation(curLadder.getX(), getY());
            }else if(direction.equals("up")){
                if(ladderNum == 3){
                    setImage(Ladder); 
                }else if(ladderNum == 6){
                    GreenfootImage reverseClimb = new GreenfootImage(Ladder);
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
            String direction = getCommand();
            if(direction.equals("left") && leftIsClear()){
                setLocation(getX() - speed, getY());
                setImage(barHang0);
            }else if(direction.equals("right") && rightIsClear()){
                setLocation(getX() + speed, getY());
                setImage(barHang1);
            }else if(direction.equals("down")){
                setLocation(getX(), getY() + speed);
                fallSpeed = 3;
            }
        }
    }
    
    public void movementPlayer(){
        if(!isFalling || isTouching(Ladder.class)){
            String direction = getCommand();
            if(direction.equals("left") && leftIsClear()){
                setLocation(getX() - speed, getY());
                if(num == 3){
                    GreenfootImage run00 = new GreenfootImage(run0);
                    run00.mirrorHorizontally();
                    setImage(run00);
                }
                if(num == 6){
                    GreenfootImage run11 = new GreenfootImage(run1);
                    run11.mirrorHorizontally();
                    setImage(run11);
                }
                if(num == 9){
                    GreenfootImage run22 = new GreenfootImage(run2);
                    run22.mirrorHorizontally();
                    setImage(run22);
                }
                if(num == 12){
                    GreenfootImage run33 = new GreenfootImage(run3);
                    run33.mirrorHorizontally();
                    setImage(run33);
                    num = 0;
                }
                num++;
            }else if(direction.equals("right") && rightIsClear() && getX() + speed < getWorld().getWidth()){
                setLocation(getX() + speed, getY());
                if(num == 1){
                    setImage(run0);
                }
                if(num == 6){
                    setImage(run1);
                }
                if(num == 9){
                    setImage(run2);
                }
                if(num == 12){
                    setImage(run3);
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

    
}
