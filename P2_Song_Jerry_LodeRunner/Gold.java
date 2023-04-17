import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gold here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gold extends Actor
{
    /**
     * Act - do whatever the Gold wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(isTouching(Player.class) || isTouching(MousePlayer.class)){
            ((MyLevelWorld)getWorld()).scoreCur += 250;
            Text txtScore = ((MyLevelWorld)getWorld()).txtScore;
            txtScore.setText("SCORE " + ((MyLevelWorld)getWorld()).scoreCur);
            txtScore.setLocation(txtScore.getImage().getWidth()/2, getWorld().getHeight() - txtScore.getImage().getHeight()/2);
            getWorld().removeObject(this);
        }
    }
}
