import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int wallWidth;
    int wallHeight;
    int numWallsInRow;
    int ladderHeight;
    int ladderWidth;
    int barHeight;
    int barWidth;
    int smallGap = 3;
    Text txtScore;
    Text txtMen;
    Text txtLevel;
    int txtHeight;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 480, 1); 
        GreenfootImage background = new GreenfootImage(600, 480);
        background.setColor(Color.BLACK);
        background.fillRect(0,0,600,480);
        setBackground(background);

        txtScore = new Text("SCORE 0");
        txtHeight = txtScore.getImage().getHeight();
        addObject(txtScore, txtScore.getImage().getWidth()/2, getHeight() - txtHeight/2);
        txtMen = new Text("MEN 5");
        addObject(txtMen, getWidth()/2, getHeight() - txtHeight/2);
        txtLevel = new Text("LEVEL 1");
        addObject(txtLevel, getWidth() - txtLevel.getImage().getWidth()/2, getHeight() - txtHeight/2);

        Wall wall = new Wall();
        wallWidth = wall.getImage().getWidth();
        wallHeight = wall.getImage().getHeight();    
        numWallsInRow = getWidth()/wallWidth;       
        drawWalls();
        Player player = new Player();
        addObject(player,player.getImage().getWidth(), player.getImage().getHeight());
        Ladder ladder = new Ladder();
        ladderHeight = ladder.getImage().getHeight();
        ladderWidth = ladder.getImage().getWidth();
        drawLadders();
        Bar bars = new Bar();
        barHeight = bars.getImage().getHeight();
        barWidth = bars.getImage().getWidth();
        drawBars();
        MousePlayer player2 = new MousePlayer();
        addObject(player2, player2.getImage().getWidth()*3, player2.getImage().getHeight());
        Enemy enemy = new Enemy();
        addObject(enemy, getWidth() - wallWidth, getHeight() - wallHeight*4 - enemy.getImage().getHeight() - txtHeight);
        spawnGold();
    }

    public void drawWalls(){
        //drawRow(1, 0, 0);
        //drawGrid(2, 2, 100, 100);
        drawRow(numWallsInRow, 0, getHeight() - wallHeight);        
        drawGrid(3, 3, wallWidth+wallWidth/2, getHeight() - wallHeight*3 + wallWidth/10);
        drawRow(12, 0, getHeight() - wallHeight*5 - wallWidth/2 + wallWidth/10); 
        drawRow(3, wallWidth * 6 , getHeight() - wallHeight*10);  
        drawRow(6, 0, getHeight() - wallHeight*17); 
        drawGrid(3, 3, getWidth() - wallWidth - wallWidth/2, getHeight() - wallHeight*3 + wallWidth/10);
        drawRow(8, wallWidth*14, getHeight() - wallHeight*9);
        drawRow(10, getWidth() - wallWidth*13, wallHeight*7);
        drawGrid(2, 8, getWidth() - wallWidth, getHeight() - wallHeight * 12);
    }

    public void drawRow(int numWalls, int rowX, int rowY){ 
        rowY -= txtHeight;
        for(int i = 0; i < numWalls; i++){
            Wall wall = new Wall();
            addObject(wall, rowX + i*wallWidth +  wallWidth/2, rowY + wallWidth/2);
        }
    }

    public void drawGrid(int numWalls,int numRows, int gridX, int gridY){   
        int startX = gridX - numWalls * wallWidth/2;
        int startY = gridY - numRows * wallHeight/2;
        for (int i = 0; i < numRows; i++) {
            drawRow(numWalls, startX, startY + i * wallHeight + smallGap * i);
        }

    }

    public void ladders(int num, int x, int y){
        y -= txtHeight;
        for(int i = 0; i < num; i++){
            Ladder ladder = new Ladder();
            addObject(ladder, x, y + (ladder.getImage().getHeight()*i));
        }
    }

    public void drawLadders(){
        ladders(4, wallWidth*12 + ladderWidth/2, getHeight() - wallHeight*5 + smallGap) ;
        ladders(3, getWidth() - wallWidth*3 - ladderWidth/2, getHeight() - 4*wallHeight + smallGap*2);
        //ladders(6, wallWidth*6 + ladderWidth/2, getHeight() - wallHeight*16 - ladderWidth/2);
        ladders(5, wallWidth*2 + ladderWidth/2, getHeight() - wallHeight*10 - ladderWidth);
        ladders(7, getWidth() - wallWidth*2 - ladderWidth, wallHeight*7);
        ladders(3, wallWidth*8, wallHeight*3);
        ladders(2, wallWidth*9, wallHeight*5);
        ladders(2, wallWidth*10, wallHeight*6);
        ladders(2, wallWidth*11, wallHeight*6);
        ladders(2, wallWidth*7, wallHeight*3);
    }

    public void bars(int num, int x, int y){
        for(int i = 0; i < num; i++){
            Bar bars = new Bar();
            addObject(bars, x + (bars.getImage().getWidth()*i), y - txtHeight);
        }
    }

    public void drawBars(){
        bars(9/2, wallWidth * 3 - ladderWidth , getHeight() - wallHeight*12 + wallHeight/2);
        bars(12, wallWidth * 9 , getHeight() - wallHeight*11 + barHeight);
        bars(3, wallWidth*4 + ladderWidth, wallHeight*2);
    }

    public void drawHUD() {
        

    }

    public void spawnGold(){
        Gold gold = new Gold();
        addObject(gold, wallWidth*7, wallHeight*11+ gold.getImage().getHeight()*4/3);
    }
}
