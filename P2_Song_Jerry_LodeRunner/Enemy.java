import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Person
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Player getClosePlayer() {
        List<Player> players = getWorld().getObjects(Player.class);  
        Player closestPlayer = null;
        int distanceCloset = -1;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int distance = (player.getX() - getX())*(player.getX() - getX()) + (player.getY() - getY())*(player.getY() - getY());            
            if (distanceCloset == -1 || distanceCloset > distance) {
                closestPlayer = player;
                distanceCloset = distance;
            }
        }
        return closestPlayer;
    }
    
    @Override
    public String getCommand() {
        String ret = "";
        Player closestPlayer = getClosePlayer();
        if (isTouching(Ladder.class)) {
            if(closestPlayer.getY() > getY() && downIsClear()) {
                ret = "down";
            }
            if(closestPlayer.getY() < getY()){
                ret = "up";
            }
        }
        if (closestPlayer.getX() < getX()){
            ret = "left";
        }
        if (closestPlayer.getX() > getX()){
            ret = "right";
        }
                        
        return ret;
    }
    @Override
    public void setImages(){
        run0 = "enemy_run_00.png";
        run1 = "enemy_run_01.png";
        run2 = "enemy_run_02.png";
        run3 = "enemy_run_03.png";
        barHang0 = "enemy_bar_hang_00.png";
        barHang1 = "enemy_bar_hang_01.png";
        Ladder = "enemy_climb_ladder.png";
        stand = "enemy_stand.png";
        fall = "enemy_fall.png";
    }
    
}
