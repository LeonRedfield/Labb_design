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
public class Playfield {
    private int[][] playfield;
    private int rows;
    private int columns;
    
    public Playfield(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        playfield = initPlayfield(rows, columns);    
    }
    
    private int[][] initPlayfield(int rows, int columns) {
        int[][] newPlayfield = new int[rows][columns];
        for(int i = 0; i < newPlayfield.length; i++) {
            for(int j = 0; j < newPlayfield[0].length; j++) {
                newPlayfield[i][j] = 0;
            }
        }
        
        return newPlayfield;
    }
    
    public int getColumns() {
        return columns;
    }
    public int getRows() {
        return rows;
    }
    
    public int [][] getPlayfield() {
        return playfield;
    }
    
    public void updateGrid(int[][] newgrid)
    { 
        for(int i=0; i<playfield.length;i++)
        {
            for(int j=0; j<playfield[0].length;j++)
            {
                if(newgrid[i][j] ==1 && playfield[i][j] == 0)
                {
                    playfield[i][j] = newgrid[i][j];
                }
            }           
        }
        
    }
    
    public void resetPlayGrid()
    {
        
    }
}
