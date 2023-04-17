import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * use this class as a starting point for your Lode Runner. Transfer any extra methods or fields you need from your original class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyLevelWorld extends LevelWorld {
    
    // add instance variables to represent the lives and score
    Text txtScore;
    Text txtMen;
    Text txtLevel;
    int txtHeight;
    int scoreCur;
    int levelCur;
    int livesCur;
    static final int DEFAULT_LIVES = 5;
    
    // static initialization code - runs when the class is loaded, before the main method is called.
    // You can only call static methods and only access static fields.
    // In this case, we need to initialize the margins BEFORE the world is created
    static {
        // set the margins (open space) on the left, right, top and bottom sides of the world
        // The level will be drawn with the given spaces open on each side
        // Set the bottom margin to the height of your HUD
        LevelWorld.setMargins(0, 0, 0, 50);
    }
    
    // Default Constructor (loads the first txt in the levels folder)
    public MyLevelWorld() {
        this(1, 0, DEFAULT_LIVES); // load level 1  
    }
    
    // loads the level given. For example, if level was 3, it would load the third txt file in the levels folder
    // You can add parameters to this constructor for lives and score. If you do, you need to pass default lives
    // and score values when you call this(1) in the default constructor.
    public MyLevelWorld(int level, int score, int lives) {
        super(level);
        levelCur = level;
        scoreCur = score;
        livesCur = lives;
        txtScore = new Text("SCORE " + scoreCur);
        txtHeight = txtScore.getImage().getHeight();
        addObject(txtScore, txtScore.getImage().getWidth()/2, getHeight() - txtHeight/2);
        txtMen = new Text("MEN " + livesCur);
        addObject(txtMen, getWidth()/2, getHeight() - txtHeight/2);
        txtLevel = new Text("LEVEL " + levelCur);
        addObject(txtLevel, getWidth() - txtLevel.getImage().getWidth()/2, getHeight() - txtHeight/2);        
    }
    
    @Override
    public void defineClassTypes() {
        // define which classes represent walls, ladders, bars, players, and enemies
        // TODO: REPLACE WITH YOUR CLASSES
        getLoader().setWallClass(Wall.class);
        getLoader().setPlayerClass(Player.class);
        getLoader().setLadderClass(Ladder.class); // you can remove this if you have no ladders in your game
        getLoader().setBarClass(Bar.class); // you can remove this if you have no bars in your game
        getLoader().setEnemyClass(Enemy.class); // you can remove this if you have no enemies in your game
        getLoader().setGoldClass(Gold.class); // you can remove this if you have no gold in your game
    }
}
