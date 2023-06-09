import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author Jerry
 * @version 4/29
 */
public class Start extends Actor
{
    /**
     * Act - do whatever the Start wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void scale(){
        GreenfootImage image = getImage();
        image.scale(180, 100);
        setImage(image); 
    }

    public void act()
    {

        if(Greenfoot.mouseClicked(this)){
            MyLevelWorld game = new MyLevelWorld();
            Greenfoot.setWorld(game); 
        }
    }
}
