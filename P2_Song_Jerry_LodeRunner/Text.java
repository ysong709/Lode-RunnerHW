import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private String text;
    private int fontSize = 25;
    private Color foreground = Color.CYAN;
    private Color background = Color.BLACK;
    private Color outline = Color.BLUE;
    public Text(String txt){
        text = txt;
        UpdateImage();        
    }

    public void UpdateImage() {
        GreenfootImage image = new GreenfootImage(text, fontSize, foreground, background, outline);
        setImage(image);        
    }

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        text = txt;
        UpdateImage();   
    }

    public int getSize() {
        return fontSize;
    }

    public void setSize(int size) {
        fontSize = size;
        UpdateImage();   
    }

    public void setColor(Color foregroundC, Color backgroundC, Color outlineC){
        GreenfootImage image = new GreenfootImage(text, fontSize, foregroundC, backgroundC, outlineC);
        setImage(image);
    }

    public void act()
    {

    }
}
