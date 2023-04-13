import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage(600, 480);
        background.setColor(Color.BLACK);
        background.fillRect(0,0,600,480);
        setBackground(background);
        Start startButton = new Start();
        startButton.scale();
        addObject(startButton, getWidth()/2, getHeight()/2 + startButton.getImage().getHeight()/2);
        Text Txt = new Text("LODERUNNER");
        Txt.setSize(75);
        addObject(Txt, getWidth()/2 , getHeight()/2 - Txt.getImage().getHeight()*2);
        Text Txt2 = new Text("Click Button To Begin Game");
        Txt2.setSize(25);
        Txt2.setColor(Color.BLUE, Color.ORANGE, Color.GRAY);
        addObject(Txt2, getWidth()/2, getHeight()/2 - Txt2.getImage().getHeight());
    }
}
