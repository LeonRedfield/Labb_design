/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Model;


/**
 *
 * @author Santos
 */
/**
 * Model is responsible for all logic in game.
 * @author Santos
 */
public class Model {
    private Playfield playfield;
    private Shapes currentBlock, nextBlock;
    private int lines, score;
    private long softDropTime;
    private long hardDropTime;
    private long rotateTime;
    private long timeSinceLastMove;
    private long gravitationSpeed, gravitationTime;
    private int rows, columns;
    private boolean isGameOver;
    
       /**
     * Standard rows and columns are used
     */
    public Model() {
        rows = 22;
        columns = 10;
        initModel();
        
    }
    
    /**
     * Custom row and column size is used
     * @param columns 
     * @param rows 
     */
    public Model(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        initModel();
    }
    
    private void initModel() {
        playfield = new Playfield(rows, columns);
        nextBlock = generateNextBlock();
        currentBlock = generateNextBlock();
        softDropTime = 0;
        hardDropTime = 0;
        timeSinceLastMove = 0;
        lines = 0;
        score = 0;
        gravitationSpeed = 1000;
        gravitationTime = 0;
        rotateTime = 0;
        isGameOver = false;
    }
    
    /**
     * Returns rows used in gamefield
     * @return
     */
    public int getRows()
    {
        return rows;
    }
    
    /**
     * Returns columns used in gamefield
     * @return 
     */
    public int getColumns()
    {
        return columns;
    }
    
    
    /**
     * Returns gamefield in 2d array containing the current state of gamefield
     * and currently played block. Empty blocks are represented by 0 and blocks 
     * by 1.
     * @return The array
     */
    public int[][] pixelGridToBeDrawn()
    {        
        int[][] tempgrid = new int[rows][columns];
        
        for(int i=0; i<currentBlock.pixelgrid.length; i++)
        {
            for(int j=0; j<currentBlock.pixelgrid[0].length; j++)
            {
                if(currentBlock.pixelgrid[i][j] == 1)
                { 
                    tempgrid[i+currentBlock.getY()][j+currentBlock.getX()] = 1; 
                }            
            }
        }
        
        
        for(int i=0; i<playfield.getPlayfield().length; i++)
        {
            for(int j=0; j<playfield.getPlayfield()[0].length; j++)
            {
                if(playfield.getPlayfield()[i][j] == 1 && tempgrid[i][j] ==0)
                {
                    tempgrid[i][j] = 1; 
                }            
            }
        }            
        return tempgrid;
    }
    
    /**
     * Updates the game. Gravitation, calculate scores ect. This is meant to be
     * called by view-side.
     */
    public void modelUpdate()
    {   
        gravitation();
        if(collision(currentBlock, playfield.getPlayfield()))
        {
            playfield.updateGrid(pixelGridToBeDrawn());
            currentBlock = getNextBlock();                
        }
        checkLine();
        
        
    }

    /**
     *  Rotates current block clockwise
     */
    public void rotateRight()
    {
        long tmpTime = System.currentTimeMillis();
        if(tmpTime - rotateTime > 150) {
            currentBlock.rotateRight();
            rotateTime = System.currentTimeMillis();
        }
        while(currentBlock.getX() + currentBlock.getPixelGrid()[0].length > playfield.getColumns()) {
            currentBlock.setX(currentBlock.getX()-1);
        }
        
    }
    
    /**
     * Rotates current block counter-clockwise
     */
    public void rotateLeft()
    {
        long tmpTime = System.currentTimeMillis();
        if(tmpTime - rotateTime > 150) {
            currentBlock.rotateLeft();
            rotateTime = System.currentTimeMillis();
        }
        while(currentBlock.getX() + currentBlock.getPixelGrid()[0].length > playfield.getColumns()) {
            currentBlock.setX(currentBlock.getX()-1);
        }
    }
    
    /**
     * Makes a soft drop if it was more than 500ms since last soft drop
     */
    public void softDrop() {
        long tmpTime = System.currentTimeMillis();
        if(tmpTime - softDropTime > 150) {
            currentBlock.setY( currentBlock.getY()+1 );
            softDropTime = tmpTime;
        }
    }
    /**
     * Makes harddrop is it was more than 100ms since last harddrop
     */        
    public void hardDrop() {
        long tmpTime = System.currentTimeMillis();
        if(tmpTime - hardDropTime > 1) {
            currentBlock.setY( currentBlock.getY()+1 );
            hardDropTime = tmpTime;
        }
    }
    
    /**
     * 
     * @return isGameOver
     */
    public boolean isGameOver()
    {
        
        for(int i=0; i<2; i++ )
        {
            for(int j=0 ; j< playfield.getColumns(); j++)
            {
                if(playfield.getPlayfield()[i][j] == 1)
                {
                    isGameOver = true;
                }
            }
        }

        return isGameOver;
    }
    
    /**
     * Makes left-move if it was more than 500 ms since last move
     */
    public void moveLeft() {
        if(System.currentTimeMillis() - timeSinceLastMove > 100) {

            if(currentBlock.getX() > 0){
                    if(!moveLeftCollision()) {       
                    
                        currentBlock.setX(currentBlock.getX()-1);
                    }   
                }
                      
            timeSinceLastMove = System.currentTimeMillis();
        }
    }
    
    private boolean moveLeftCollision() {
        
        boolean collision = false;
        for(int w = 0; w < currentBlock.pixelgrid.length; w++) {
            for(int z = 0; z < currentBlock.pixelgrid[0].length; z++) {
                if(currentBlock.pixelgrid[w][z] == 1) {
                    if(playfield.getPlayfield()[w+currentBlock.getY()][z+currentBlock.getX()-1] == 1) {
                        collision = true;
                    }
                }
            }
        }
        return collision;
    }
    
    /**
     * Makes right-move if it was more than 500 ms since last move
     */
    public void moveRight() {
        if(System.currentTimeMillis() - timeSinceLastMove> 100) {
                if(currentBlock.getX() < (playfield.getColumns() ) - currentBlock.getPixelGrid()[0].length) {
                    if(!moveRightCollision() ) {
                        currentBlock.setX(currentBlock.getX()+1);
                    }
                }
                
            
            timeSinceLastMove = System.currentTimeMillis();
        }
    }
    
    private boolean moveRightCollision() {
        boolean collision = false;
        for(int w = 0; w < currentBlock.pixelgrid.length; w++) {
            for(int z = 0; z < currentBlock.pixelgrid[0].length; z++) {
                if(currentBlock.pixelgrid[w][z] == 1) {
                    if(playfield.getPlayfield()[w+currentBlock.getY()][z+currentBlock.getX()+1] == 1) {
                        collision = true;
                    }
                }
            }
        }
        return collision;
    }
    
    /**
     * Reinitiates all values
     */
    public void reset() {
        initModel();
    }
    
    /**
     * Returns lines-status
     * @return 
     */
    public int getLines() {
        return lines;
    }
    
    /**
     * Returns score status
     * @return 
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Returns playfield represented by 2d array. Currently played block is not
     * in here. 
     * @return The gamefield array[y][x]
     */
    public int[][] getPlayField()
    {
        return playfield.getPlayfield();
    }
    
    /**
     * Returns array representing the next-block grid in 2d array. 0 represents
     * empty space and 1 represents filled square. can be 2x2, 2x3, 3x2 or 4x4.
     * @return array[y][x]
     */
    public int[][] getNextBlockPixelGrid()
    {
        return nextBlock.pixelgrid;
    }
    
    

    
    private Shapes generateNextBlock() {
        Shapes newShape;
        
        int randomNum = (int)(Math.random()* 7);
        switch(randomNum) {
            case 0:
               newShape = new IBlock();
               break;
            case 1:
               newShape = new JBlock();
               break;
            case 2:
               newShape = new LBlock();
               break;
            case 3:
               newShape = new OBlock();
               break;
            case 4:
               newShape = new SBlock();
               break;
            case 5:
               newShape = new TBlock();
               break;
            case 6:
               newShape = new ZBlock();
               break;
            default:
                newShape = new OBlock();
        }
        
        return newShape;
    }
    
    private Shapes getNextBlock() {
        Shapes returnBlock = nextBlock;
        
        nextBlock = generateNextBlock();
        
        return returnBlock;
    }
    
    private void gravitation() {
        
        if(System.currentTimeMillis() - gravitationTime > gravitationSpeed) {
            currentBlock.setY(currentBlock.getY()+1);
            gravitationTime = System.currentTimeMillis();
            
        }
    }
    
    private boolean collision(Shapes currentBlock, int[][] playfield) {
        boolean collision = false;
        for(int i = 0; i < currentBlock.getPixelGrid().length; i++) 
        {
            for(int j = 0; j < currentBlock.getPixelGrid()[0].length; j++) 
            {
                if(currentBlock.getPixelGrid()[i][j] == 1) 
                {
                    int y = currentBlock.getY();
                    int x = currentBlock.getX();
                    if(/*playfield[y+i+1][x+i] == 1 ||*/ currentBlock.hitBottom(playfield.length)) 
                    {
                        collision = true;
                        
                    } else {
                        for(int w = 0; w < currentBlock.pixelgrid.length; w++) {
                            for(int z = 0; z < currentBlock.pixelgrid[0].length; z++) {
                                if(currentBlock.pixelgrid[w][z] == 1) {
                                    if(playfield[w+currentBlock.getY()+1][z+currentBlock.positionX] == 1) {
                                        collision = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return collision;
    }
  
    private void updateScore(int addScore, int addLine) {
        score += addScore;
        lines += addLine;
    }
    
    private int checkLine() {
        
        int line = playfield.getColumns();
        int linesInField = 0;
        int linesInRow = 0;
        
        for(int i = 0; i < playfield.getRows(); i++) {
            for(int j = 0; j < playfield.getColumns(); j++) {
                if(playfield.getPlayfield()[i][j]== 1) {
                    linesInRow++;
                    
                }
                
            }
            if(linesInRow >= playfield.getColumns()) {
                linesInField++;
                clearLine(i);
                pullLineDown(i);
            }
            
            linesInRow = 0;
        }
        
        updateScore(calculateScore(linesInField), linesInField);
        
        
        return 0;
    }
    
    private int calculateScore(int lines) {
        
        
        return lines*40;
    }
    
    private void clearLine(int y) {
        int[][] tempField = playfield.getPlayfield().clone();
        for(int x = 0; x < playfield.getColumns()-1; x++) {
            tempField[y][x] = 0;
        }
        playfield.updateGrid(tempField);
        
    }
    
    private void pullLineDown(int y) {
        int [][] tempField = playfield.getPlayfield().clone();
        for(int i = y; i > 0; i--) {
            System.arraycopy(playfield.getPlayfield()[i-1], 0, tempField[i], 0, playfield.getColumns());
        }
        //Clear top line
        for(int w = 0; w < tempField[0].length; w++) {
            tempField[0][w] = 0;
        }
        playfield.updateGrid(tempField);
        
    }
    

    
}